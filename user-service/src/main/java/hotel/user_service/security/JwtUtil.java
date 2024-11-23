package hotel.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mySecretKeyShouldBeAtLeast32Characters".getBytes());
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; //1 hour

    // Generate JWT token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256) // Sign with secret key
                .compact();
    }

    // Validate the token
    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token); // Parse and validate the token
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract email (subject) from the token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token) // Parse the token to extract claims
                .getBody()
                .getSubject(); // Retrieve the subject (email)
    }

    // Extract role from the token
    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class); // Retrieve the "role" claim
    }
}
