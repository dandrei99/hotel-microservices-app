package hotel.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // use BCrypt for hashing passwords
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing purposes
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").authenticated() // Protect specific endpoints
                        .anyRequest().permitAll() // Allow all other requests
                )
                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication

        return http.build();

        //test without any security
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
//        return http.build();
    }
}
