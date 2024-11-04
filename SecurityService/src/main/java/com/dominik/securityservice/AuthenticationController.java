package com.dominik.securityservice;

import com.dominik.securityservice.security.AuthRequest;
import com.dominik.securityservice.security.AuthenticateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.apache.naming.ResourceRef.AUTH;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(AUTH)
public class AuthenticationController {
    private static final String AUTH = "/auth";

    private AuthenticateService authenticateService;


    // Inside your controller class
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticateService.register(request));
        } catch (BadRegisterData exc) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        try {
            return ResponseEntity.ok(authenticateService.authenticate(request));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkAuthentication(HttpServletRequest request) {
        if ("OPTIONS".equals(request.getMethod())) {
            return ResponseEntity.ok().build();
        }
        logger.info("Received request {}", SecurityContextHolder.getContext().getAuthentication());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()&& !(authentication instanceof AnonymousAuthenticationToken)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/user/{userId}")
    public UserDataDto getUserById(@PathVariable Integer userId) {
        return authenticateService.getUserById(userId);
    }

    @PostMapping("/userData")
    public ResponseEntity<UserDataDto> getUserData(@RequestBody String token) {
        try {
            UserEntity userData = authenticateService.getUserData(token);
            return ResponseEntity.ok(new UserDataDto(userData.getUserId(), userData.getEmail()));
        } catch (RuntimeException exc) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
