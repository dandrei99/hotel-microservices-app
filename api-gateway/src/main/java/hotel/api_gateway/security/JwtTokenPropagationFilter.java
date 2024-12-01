package hotel.api_gateway.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JwtTokenPropagationFilter implements WebFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenPropagationFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        logger.info("Starting JwtTokenPropagationFilter...");

        // Extract the JWT token from the existing request
        String token = extractToken(exchange);

        if (token != null) {
            logger.info("Extracted token: {}", token);

            try {


                // Create a new ServerHttpRequestDecorator with updated headers
                ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders headers = new HttpHeaders();
                        headers.putAll(super.getHeaders()); // Copy existing headers
                        headers.set("Authorization", "Bearer " + token); // Add Authorization header
                        return headers;
                    }
                };

                logger.info("Modified request headers: {}", mutatedRequest.getHeaders());
                // Create a new mutated exchange with the modified request
                ServerWebExchange mutatedExchange = exchange.mutate()
                        .request(mutatedRequest)
                        .build();

                logger.info("Proceeding with the mutated exchange...");
                // Proceed with the mutated exchange
                return chain.filter(mutatedExchange);

            } catch (Exception e) {
                logger.info("Error in JwtTokenPropagationFilter while mutating request: ", e);
                throw e;
            }
        }

        logger.info("No token found in the request headers.");
        // If no token, proceed with the original exchange
        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        logger.info("Authorization header is missing or does not start with 'Bearer '.");
        return null;
    }
}

