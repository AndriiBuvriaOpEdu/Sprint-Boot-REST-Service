package marketplace.security.repository;

import marketplace.security.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    
    private final Map<String, User> users = new HashMap<>();
    
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
    
    public void save(User user) {
        users.put(user.getUsername(), user);
    }
    
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }
}

