package hotel.user_service.controller;

import hotel.user_service.dto.AuthResponse;
import hotel.user_service.dto.LoginRequest;
import hotel.user_service.exception.exceptions.LoginFailedException;
import hotel.user_service.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            //Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            //Generate JWT token
            String token = jwtUtil.generateToken(
                    loginRequest.getEmail(),
                    authentication.getAuthorities().iterator().next().getAuthority()
            );

            return ResponseEntity.ok(new AuthResponse(token));

        } catch (AuthenticationException e){
            throw new LoginFailedException();
        }
    }
}
