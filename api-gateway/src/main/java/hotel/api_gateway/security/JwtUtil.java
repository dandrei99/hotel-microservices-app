package hotel.api_gateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    //    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mySecretKeyShouldBeAtLeast32Characters".getBytes());
    private final SecretKey secretKey;
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; //1 hour

    // Constructor to initialize the secret key read from application.properties
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
        logger.info("JwtUtil initialized with secret key.");
    }

    // Validate the token
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token); // Parse and validate the token
            logger.info("Token is valid.");
            return true;
        } catch (Exception e) {
            logger.info("Token is NOT valid: {}", e.getMessage());
        }
        return false;
    }

    // Extract email (subject) from the token
    public String extractEmail(String token) {
        try {
            String email = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            logger.info("Extracted email: {}", email);
            return email;
        } catch (Exception e) {
            logger.info("Error extracting email from token: {}", e.getMessage());
            throw e;
        }
    }
}
