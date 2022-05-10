package matej.tejko.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import matej.tejko.api.services.UserDetailsServiceImpl;
import matej.tejko.components.JwtComponent;

import java.util.Arrays;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	JwtComponent jwtComponent;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = jwtComponent.parseToken(request.getHeader("Authorization"));
		if (jwt != null && jwtComponent.validateJwt(jwt)) {
			String username = jwtComponent.getUsernameFromJwt(jwt);
			try {
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails,
						null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (UsernameNotFoundException exception) {
				System.out.println(exception.getMessage());
			}
		}

		String rangeHeader = request.getHeader("Range");

		// if there is no range request in the header than provide default filtering
		if (rangeHeader == null) {
			filterChain.doFilter(request, response);
			return;
		}

		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
				(HttpServletResponse) response);

		HttpRange range = HttpRange.parseRanges(rangeHeader).get(0);

		try {
			filterChain.doFilter(request, responseWrapper);
		} finally {
			byte[] copy = responseWrapper.getContentAsByteArray();
			int size = responseWrapper.getContentSize();
			int lower = (int) range.getRangeStart(size);
			int upper = (int) range.getRangeEnd(size);
			if (lower <= size) {
				responseWrapper.setStatus(SC_PARTIAL_CONTENT);
				byte[] subArray = Arrays.copyOfRange(copy, lower, upper + 1);
				String newContent = new String(subArray, UTF_8);
				responseWrapper.reset();
				responseWrapper.setHeader("Content-Range", String.format("bytes %d-%d/%d", lower, upper, size));
				responseWrapper.setContentLength(newContent.length());
				responseWrapper.getWriter().write(newContent);
				responseWrapper.getWriter().flush();
				responseWrapper.flushBuffer();
				responseWrapper.copyBodyToResponse();
			} else {
				responseWrapper.setStatus(SC_REQUESTED_RANGE_NOT_SATISFIABLE);
			}
		}
	}

}