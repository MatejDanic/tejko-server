package matej.tejkogames.security;

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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import matej.tejkogames.api.services.UserDetailsServiceImpl;
// import matej.tejkogames.constants.TejkoGamesConstants;
import matej.tejkogames.utils.JwtUtil;
import java.util.Arrays;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = parseJwt(request);
		if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
			String username = jwtUtil.getUsernameFromJwtToken(jwt);

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		String rangeHeader = request.getHeader("Range");

		// if there is no range request in the header than do the "normal" filtering
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

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}