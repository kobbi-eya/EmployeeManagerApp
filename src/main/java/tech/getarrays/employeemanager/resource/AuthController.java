package tech.getarrays.employeemanager.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import tech.getarrays.employeemanager.model.JwtResponse;
import tech.getarrays.employeemanager.model.LoginRequest;
import tech.getarrays.employeemanager.config.JwtUtil;
import tech.getarrays.employeemanager.service.EmployeeService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.debug("Login attempt for user: {}", loginRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.error("Invalid credentials for user: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        // Use the instance of EmployeeService here
        String role = employeeService.getEmployeeRole(loginRequest.getEmail());

        String jwt = jwtUtil.generateToken(loginRequest.getEmail());
        log.debug("JWT generated for user: {}", loginRequest.getEmail());
        return ResponseEntity.ok(new JwtResponse(jwt, role));
    }

}
