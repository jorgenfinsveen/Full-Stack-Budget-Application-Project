package no.idata1002.group19.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Represents an JSON Web Token (JWT) service which generates
 * JWT Tokens upon user authentication, and to validate JWTs
 * provided by the client to ensure the credibility of the
 * given requests.
 *
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Component
public class JwtService {

	/** Expiration time specifynig how long a JWT is valid. */
    static final long EXPIRATIONTIME = 86400000; // 1 day in ms

	static final String PREFIX = "Bearer";


	static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	
	/**
	 * Generates a JWT Token with the given username and expiration time
	 * which are to be returned to the client upon authorization.
	 * 
	 * @param username The username of the authenticated user.
	 * @return Generated JSON Web Token as a String.
	 */
	public String getToken(String username) {
		return Jwts.builder()
			  .setSubject(username)
			  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
			  .signWith(key)
			  .compact();
  	}


	/** 
	 * Validates a JWT which a client has provided in its request header.
	 * 
	 * @param request The HTTP request which the client forwarded to the server.
	 * @return String username of the user which the JWT was generated to.
	 */
	public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String user = null;

        if (token != null) {
            user = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token.replace(PREFIX + " ", ""))
                .getBody()
                .getSubject();
        }
        return user;
	}
}
