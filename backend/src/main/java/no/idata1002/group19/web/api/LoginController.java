package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.User;
import no.idata1002.group19.service.JwtService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LoginController {
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<?> getToken(@RequestBody User user) {
		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						user.getUsername(), 
						user.getPass());	

		Authentication auth = authenticationManager.authenticate(creds);

		// Generate token
		String jwts = jwtService.getToken(auth.getName());

		// Build response with the generated token
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();

	}

}
