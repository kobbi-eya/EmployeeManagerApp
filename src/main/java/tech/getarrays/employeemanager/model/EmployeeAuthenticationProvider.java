package tech.getarrays.employeemanager.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private EmployeeRepo employeeRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Employee employee = employeeRepository.findByEmail(email);
        if (employee == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!employee.getPassword().equals(password)) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(employee, password, getAuthorities(employee.getRole()));
    }

    private List<GrantedAuthority> getAuthorities(Employee.Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}