package marketplace.security.service;

import marketplace.security.jwt.JwtService;
import marketplace.security.model.User;
import marketplace.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    public String generateToken(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        return jwtService.generateToken(user.getUsername(), user.getRoles());
    }
}

