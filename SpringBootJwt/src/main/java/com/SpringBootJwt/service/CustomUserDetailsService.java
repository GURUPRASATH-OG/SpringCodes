package com.SpringBootJwt.service;

import com.SpringBootJwt.entity.User;
import com.SpringBootJwt.respository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByName(username).orElseThrow(()->new UsernameNotFoundException("user not found:"+username));

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword()
               ,user.getRoles().stream()
                       .map(role->new SimpleGrantedAuthority(role.getName()))
                       .collect(Collectors.toList()));

    }
}
