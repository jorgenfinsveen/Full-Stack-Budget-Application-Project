package no.idata1002.group19;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import no.idata1002.group19.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Represents an filter class to authenticate all other
 * incoming requests.The AuthencticationFilter class
 * extends Spring Security's OncePerRequestFilter
 * interface that provides doFilterInternal method where
 * the authentication is implemented.
 *
 * @author  Group19
 * @since   16.04.2023
 * @version 16.04.2023
 */
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
           if (jws != null) {
            String user = jwtService.getAuthUser(request);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                java.util.Collections.emptyList()
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
