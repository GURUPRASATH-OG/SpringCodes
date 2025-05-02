package com.sudo.Blog.services.impl;

import com.sudo.Blog.domain.entites.User;
import com.sudo.Blog.repository.UserRepository;
import com.sudo.Blog.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
    private final UserRepository userRepository;

    @Override
    public User getUserById(UUID id)
    {
        return userRepository.
                findById(id)
                .orElseThrow(()->new EntityNotFoundException("No user Found on Id:"+id));
    }
}
