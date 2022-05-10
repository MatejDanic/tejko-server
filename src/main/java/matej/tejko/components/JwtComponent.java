package matej.tejko.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import matej.tejko.api.repositories.UserRepository;
import matej.tejko.models.general.UserDetailsImpl;

@Component
public class JwtComponent {

	@Value("${matej.tejko.jwtSecret}")
	private String jwtSecret;

	@Value("${matej.tejko.jwtExpirationMs}")
	private int jwtExpirationMs;

	@Autowired
	UserRepository userRepository;

	public String generateJwt(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				// .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.setExpiration(null)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUsernameFromHeader(String headerAuth) {
		return getUsernameFromJwt(parseToken(headerAuth));
	}

	public UUID getUserIdFromHeader(String headerAuth) {
		return userRepository.findByUsername(getUsernameFromHeader(headerAuth)).get().getId();
	}

	public String getUsernameFromJwt(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public String parseToken(String headerAuth) {
		return StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")
				? headerAuth.substring(7, headerAuth.length())
				: null;
	}

	public boolean validateJwt(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

}