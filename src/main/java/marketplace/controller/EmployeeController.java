package marketplace.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    @GetMapping
    public String getAllEmployees() {
        return "List of all employees";
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("@securityManager.isUser() or @securityManager.isAdmin()")
    public String getEmployeeById(@PathVariable Long id) {
        return "Employee with id: " + id;
    }
    
    @PostMapping
    @PreAuthorize("@securityManager.isAdmin()")
    public String createEmployee(@RequestBody String employee) {
        return "Employee created";
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("@securityManager.isAdmin()")
    public String deleteEmployee(@PathVariable Long id) {
        return "Employee deleted: " + id;
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("@securityManager.isAdminOrManager()")
    public String updateEmployee(@PathVariable Long id, @RequestBody String employee) {
        return "Employee updated: " + id;
    }
}

