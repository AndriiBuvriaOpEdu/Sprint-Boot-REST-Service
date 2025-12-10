package marketplace.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface JwtService {
    String generateToken(String username, List<String> roles);
    String extractUsername(String token);
    List<String> extractRoles(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
}

