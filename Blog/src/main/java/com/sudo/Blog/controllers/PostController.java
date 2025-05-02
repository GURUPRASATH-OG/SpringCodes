package com.sudo.Blog.controllers;

import com.sudo.Blog.domain.CreatePostRequest;
import com.sudo.Blog.domain.UpdatePostRequest;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.User;
import com.sudo.Blog.dtos.CreatePostDto;
import com.sudo.Blog.dtos.PostDto;
import com.sudo.Blog.dtos.UpdatePostDto;
import com.sudo.Blog.mappers.PostMapper;
import com.sudo.Blog.services.PostService;
import com.sudo.Blog.services.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/posts")
@RequiredArgsConstructor
public class PostController
{
    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> listAllPost(@RequestParam(required = false)UUID categoryId,
                                                            @RequestParam(required = false)UUID tagId)
    {
        List<Post> posts= postService.getAllPosts(categoryId,tagId);
       List<PostDto> postDtos= posts.stream().map(postMapper::toDto).toList();
       return  ResponseEntity.ok(postDtos);
    }
    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> listAllDrafts(@RequestAttribute UUID userId)
    {
        User loggedInUser=userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> draftPostDto = draftPosts.stream().map(postMapper::toDto).toList();
        return  ResponseEntity.ok(draftPostDto);

    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostDto createPostDto,
                                              @RequestAttribute UUID userId)
    {
        User loggedInUser = userService.getUserById(userId);
        CreatePostRequest createPostRequest = postMapper.toCreatePostRequest(createPostDto);
        PostDto defaultpostDto = new PostDto();
        if(createPostRequest.getStatus()!=null) {
            Post createdPost = postService.createPost(loggedInUser, createPostRequest);
            PostDto postDto = postMapper.toDto(createdPost);
            return new ResponseEntity<>(postDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(defaultpostDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(path="/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID id, @Valid @RequestBody UpdatePostDto postDto)
    {
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(postDto);
        Post updatedPost = postService.updatePost(id,updatePostRequest);
        PostDto updatedPostDto =postMapper.toDto(updatedPost);
        return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable UUID id)
    {
        Post getPost = postService.getPostById(id);
        PostDto getPostDto = postMapper.toDto(getPost);
        return ResponseEntity.ok((getPostDto));
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void>deletePost(@PathVariable UUID id)
    {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
