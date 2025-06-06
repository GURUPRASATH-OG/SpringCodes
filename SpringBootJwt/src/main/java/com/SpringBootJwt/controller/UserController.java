package com.SpringBootJwt.controller;

import com.SpringBootJwt.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private JwtUtils jwtUtil;

    @Value("${role.admin}")
    private String roleAdmin;

    @Value("${role.user}")
    private String roleUser;

    //api to access user protected data.
    @GetMapping("/protected-data")
    public ResponseEntity<String> getProtectedResource(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            try {
                if (jwtUtil.isValidToken(jwtToken)) {
                    String username = jwtUtil.extractUsername(jwtToken);
                    Set<String> roles = jwtUtil.extractRoles(jwtToken);
                    if (roles.contains(roleAdmin)) {
                        return ResponseEntity.ok("Weclome" + username + "your roles" +roles+ "here is your Protected Resource");

                    } else if (roles.contains(roleUser)) {
                        return ResponseEntity.ok("Weclome " + username + "your roles" + roles+"you spefic data");
                    } else {
                        return ResponseEntity.status(403).body("Acesss Denied Mf your not a user");
                    }
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid token");
            }

        }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization header missing or invalid ");

    }
}
