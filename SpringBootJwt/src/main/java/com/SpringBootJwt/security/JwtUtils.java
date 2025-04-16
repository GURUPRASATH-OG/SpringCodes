package com.SpringBootJwt.security;

import com.SpringBootJwt.entity.Role;
import com.SpringBootJwt.entity.User;
import com.SpringBootJwt.respository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Component
public class JwtUtils
{
    private UserRepository userRepository;
    public JwtUtils(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    //secret key
    private static final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    //setting expiration time
    private final int JwtExpiration = 86400000;
    //generate token
    public String generateToke(String username)
    {
        Optional<User> user = userRepository.findUserByName(username);
        Set<Role> roles = user.get().getRoles();
        //add roles to token
        return Jwts.builder().setSubject(username)
                .claim("roles",roles.stream().map(role->role.getName()).collect(Collectors.joining(",")))
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+JwtExpiration))
                .signWith(secretKey).compact();
    }

    //Extract Username from token
    public String extractUsername(String token)
    {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    //Extract Roles
    public Set<String> extractRoles(String token)
    {
        String roleString =Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().get("roles",String.class);
        return Set.of(roleString);
    }
    //validate the token
    public Boolean isValidToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
