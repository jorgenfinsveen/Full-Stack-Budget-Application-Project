package no.idata1002.group19.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import no.idata1002.group19.domain.entity.AccountCredentials;
import no.idata1002.group19.domain.repository.UserRepository;
import no.idata1002.group19.service.JwtService;


@RestController
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

    @PostMapping(value = "/login")
	public ResponseEntity<?> getToken(@RequestBody AccountCredentials credentials) {

		UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(
			credentials.getUsername(),
			credentials.getPassword()
		);
		Authentication auth = authenticationManager.authenticate(creds);

		// Generate token
		String jwts = jwtService.getToken(auth.getName());

		Optional<String> budget = userRepository.getBudgetByUsername(credentials.getUsername());

		if (!budget.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		// Build response with the generated token
		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
			.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
			.header(HttpHeaders.AUTHORIZATION, "BudgetId " + budget.get())
			.build();
	}


	public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
}
