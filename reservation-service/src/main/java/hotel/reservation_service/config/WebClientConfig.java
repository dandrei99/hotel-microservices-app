package hotel.reservation_service.config;

import hotel.reservation_service.service.impl.ReservationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);

    @Bean
    public WebClient.Builder webClientBuilder() {
        log.info("Creating WebClient.Builder with propagateAuthorizationHeader");
        return WebClient.builder()
                .filter(logRequest()) // Add logging filter
                .filter(propagateAuthorizationHeader()); // Add filter for token propagation
    }

    @Bean
    @Qualifier("unsecuredWebClientBuilder")
    public WebClient.Builder unsecuredWebClientBuilder() {
        return WebClient.builder();
    }

    private ExchangeFilterFunction propagateAuthorizationHeader() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Propagating Authorization Header...");
            return ReactiveSecurityContextHolder.getContext()
                    .doOnNext(securityContext -> {
                        log.info("SecurityContext found: {}", securityContext);
                        log.info("Authentication: {}", securityContext.getAuthentication());
                    })
                    .map(securityContext -> {
                        String authHeader = (String) securityContext.getAuthentication().getCredentials();
                        log.info("Setting Authorization Header: {}", authHeader);
                        return ClientRequest.from(clientRequest)
                                .header(HttpHeaders.AUTHORIZATION, "Bearer " + authHeader)
                                .build();
                    })
                    .defaultIfEmpty(clientRequest); // Return the original clientRequest if no security context is available
        });
    }



    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request in webClientBuilder: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> {
                log.info("{}: {}", name, values);
            });
            return Mono.just(clientRequest);
        });
    }

}
