package hotel.api_gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final JwtTokenValidationFilter jwtTokenValidationFilter;

    public SecurityConfig(JwtTokenValidationFilter jwtTokenValidationFilter) {
        this.jwtTokenValidationFilter = jwtTokenValidationFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        logger.info("Configuring SecurityWebFilterChain...");
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF
                .cors(corsSpec -> {})
                .authorizeExchange(auth -> auth
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/api/auth/login").permitAll() // Allow login endpoint
                        .pathMatchers("/api/users/**").authenticated() // Protect specific endpoints
                        .anyExchange().permitAll() // Allow all other requests
                )
                .addFilterAt(jwtTokenValidationFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Add JWT filter
                .build();
    }
}
