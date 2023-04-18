package no.idata1002.group19.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.BudgetingApp;
import no.idata1002.group19.domain.entity.AccountCredentials;
import no.idata1002.group19.service.JwtService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {

		BudgetingApp.alert();

		UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
			credentials.getUsername(),
			credentials.getPassword()
		);
		Authentication auth = authenticationManager.authenticate(creds);

		// Generate token
		String jwts = jwtService.getToken(auth.getName());

		// Build response with the generated token
		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
			.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
			.build();
	}


	public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
}
