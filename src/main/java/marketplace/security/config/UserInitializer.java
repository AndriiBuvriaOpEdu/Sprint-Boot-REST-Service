package marketplace.security.config;

import marketplace.security.model.User;
import marketplace.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserInitializer implements ApplicationListener<ContextRefreshedEvent> {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initializeUsers();
    }
    
    private void initializeUsers() {
        userRepository.save(new User(
            1L,
            "user",
            passwordEncoder.encode("password"),
            Arrays.asList("ROLE_USER")
        ));
        
        userRepository.save(new User(
            2L,
            "admin",
            passwordEncoder.encode("admin123"),
            Arrays.asList("ROLE_USER", "ROLE_ADMIN")
        ));
        
        userRepository.save(new User(
            3L,
            "manager",
            passwordEncoder.encode("manager123"),
            Arrays.asList("ROLE_USER", "ROLE_MANAGER")
        ));
    }
}

