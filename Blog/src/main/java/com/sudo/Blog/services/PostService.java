package com.sudo.Blog.services;

import com.sudo.Blog.domain.CreatePostRequest;
import com.sudo.Blog.domain.UpdatePostRequest;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.User;

import java.util.List;
import java.util.UUID;

public interface PostService
{
    List<Post> getAllPosts(UUID categoryId,UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    Post getPostById(UUID id);
    void deletePost(UUID id);
}
