package com.SpringBootJwt.controller;

import com.SpringBootJwt.dto.RegisterRequest;
import com.SpringBootJwt.entity.Role;
import com.SpringBootJwt.entity.User;
import com.SpringBootJwt.respository.RoleRepository;
import com.SpringBootJwt.respository.UserRepository;
import com.SpringBootJwt.security.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthorizationController
{
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthorizationController(JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager)
    {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest)
    {
        if(userRepository.findUserByName(registerRequest.getName()).isPresent())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User name is already taken");
        }
        User user = new User();
        String encodepaswd = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
        user.setName(registerRequest.getName());
        user.setPassword(encodepaswd);
        //convert role name to role entities and assign to user
        Set<Role> roles = new HashSet<>();
        for(String roleName: registerRequest.getRoles())
        {
            Role role = roleRepository.findByName(roleName).orElseThrow(()->new RuntimeException("Role not found "+ roleName));
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok("User Registered Sucessfully");

    }

    //Login Api
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        } catch (Exception e)
        {
            System.out.println("Exception"+e);
        }
        String token = jwtUtils.generateToke(loginRequest.getName());
        return ResponseEntity.ok(token);
    }
}
