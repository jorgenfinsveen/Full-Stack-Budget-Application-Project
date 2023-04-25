package no.idata1002.group19;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import no.idata1002.group19.service.UserDetailsServiceImpl;
/**
 * Represents security configuration class for Spring Security,
 * the class is responsible for configuring the security settings
 * and the components of the application.
 *
 * @author Group19
 * @since 16.04.2023
 * @version 25.04.2023
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Autowired
    private AuthEntryPoint exceptionHandler;

    /** Represents the environment in which the application is running. */
    @Autowired
    private Environment environment;

    /** Encrypts the passwords which are stored in the database. */
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();



    /**
     * This method will be called automatically by the framework to find out what
     * authentication to use.
     * Here we tell that we want to load users from a database.
     *
     * @param auth Authentication builder used for authenticating users.
     * @throws Exception upon failed authentication of user details.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    /**
     * Configures the AuthenticationManager instance and returns it.
     * 
     * @param http An HttpSecurity instance to configure the AuthenticationManager
     *             instance.
     * @return An AuthenticationManager instance.
     */
    @Bean
    public AuthenticationManager getAuthenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    /**
     * This method will be called automatically by the framework to find out what
     * authentication to use.
     *
     * @param http HttpSecurity setting builder.
     * @throws Exception if an error occurs upon configuring the server security profile.
     */
    @Bean
    @SuppressWarnings(value = { "all" })
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String debugging_mode = (environment.getProperty("debugging_mode")).trim();
        boolean debug = "DEBUG".equalsIgnoreCase(debugging_mode);

        /* Active if application is running in DEBUG mode. */
        if (debug) {
            http.csrf(csrf -> csrf.disable())
                    .cors(withDefaults())
                    .authorizeHttpRequests()
                    .anyRequest()
                    .permitAll();
        /* Active if application is running in default mode. */
        } else {
            http.cors(withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(management -> management
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests()
                    .requestMatchers(HttpMethod.POST, "/login", "/users")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .exceptionHandling(handling -> handling
                            .authenticationEntryPoint(exceptionHandler))
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }

        return http.build();
    }

    
    /**
     * Returns a CorsConfigurationSource object that is used to configure
     * Cross-Origin Resource Sharing (CORS) for the application.
     * The configuration sets allowed origins, methods, and headers and
     * specifies whether or not to allow credentials.
     * 
     * @return a CorsConfigurationSource object with CORS configuration settings
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(false);
        config.applyPermitDefaultValues();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
