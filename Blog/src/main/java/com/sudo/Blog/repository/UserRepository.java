package com.sudo.Blog.repository;

import com.sudo.Blog.domain.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID>
{
    Optional<User> findByEmail(String email);
}
