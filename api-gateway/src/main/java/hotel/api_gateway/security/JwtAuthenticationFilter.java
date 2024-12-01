package hotel.api_gateway.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Get the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // Check if the header is valid and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Remove "Bearer " prefix

            try {
                // Validate the token
                if (jwtUtil.validate(token)) {
                    // Extract email
                    String email = jwtUtil.extractEmail(token);

                    // Create authentication object
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, null, null);

                    // Set authentication in the reactive security context
                    return chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authToken));
                }
            } catch (Exception e) {
                // Respond with invalid token message
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        // If no token is present, proceed with the chain
        return chain.filter(exchange);
    }
}
