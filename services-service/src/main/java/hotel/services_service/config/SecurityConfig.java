package hotel.services_service.config;

import hotel.services_service.security.JwtMicroserviceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtMicroserviceFilter jwtMicroserviceFilter;

    public SecurityConfig(JwtMicroserviceFilter jwtMicroserviceFilter) {
        this.jwtMicroserviceFilter = jwtMicroserviceFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .addFilterBefore(jwtMicroserviceFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT filter

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return authentication -> {
            // Skip revalidating JWT token, assume it is handled by JwtAuthenticationFilter
            return authentication;
        };
    }
}
