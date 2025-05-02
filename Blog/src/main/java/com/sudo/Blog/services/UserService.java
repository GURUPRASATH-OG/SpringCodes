package com.sudo.Blog.services;

import com.sudo.Blog.domain.entites.User;

import java.util.UUID;

public interface UserService
{
    User getUserById(UUID id);
}
