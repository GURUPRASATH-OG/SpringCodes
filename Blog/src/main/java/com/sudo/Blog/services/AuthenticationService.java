package com.sudo.Blog.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService
{

    UserDetails authenticateUser(String email, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);

}
