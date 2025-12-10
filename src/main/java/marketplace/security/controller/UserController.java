package marketplace.security.controller;

import marketplace.security.model.JwtRequest;
import marketplace.security.model.JwtResponse;
import marketplace.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = userService.generateToken(request.getUsername());
        
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

