package hotel.user_service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

//    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("mySecretKeyShouldBeAtLeast32Characters".getBytes());
    private final SecretKey secretKey;
    private static final long EXPIRATION_TIME = 60 * 60 * 1000; //1 hour

    // Constructor to initialize the secret key read from application.properties
    public JwtUtil(@Value("${jwt.secret}") String secret){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate JWT token
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256) // Sign with secret key
                .compact();
    }

    // Extract email (subject) from the token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token) // Parse the token to extract claims
                .getBody()
                .getSubject(); // Retrieve the subject (email)
    }

}
