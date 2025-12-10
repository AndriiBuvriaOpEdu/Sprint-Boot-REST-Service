package marketplace.security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("securityManager")
public class SecurityManager {
    
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }
    
    public boolean isUser() {
        return hasRole("ROLE_USER");
    }
    
    public boolean isManager() {
        return hasRole("ROLE_MANAGER");
    }
    
    public boolean isAdminOrManager() {
        return hasRole("ROLE_ADMIN") || hasRole("ROLE_MANAGER");
    }
    
    public boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(role));
    }
    
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        
        return authentication.getName();
    }
}

