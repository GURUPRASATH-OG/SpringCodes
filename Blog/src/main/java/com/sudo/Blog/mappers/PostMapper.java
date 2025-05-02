package com.sudo.Blog.mappers;

import com.sudo.Blog.domain.CreatePostRequest;
import com.sudo.Blog.domain.UpdatePostRequest;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.User;
import com.sudo.Blog.dtos.*;
import com.sudo.Blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper
{


    public PostDto toDto(Post post)
    {

        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .author(AuthorDto.builder().name(post.getAuthor().getName()).id(post.getAuthor().getId()).build())
                .category(CategoryMapperImplementation.builder().build().toDto(post.getCategory()))
                .tagResponses(post.getTags().stream().map(tag->new TagResponse(
                            tag.getId(),
                            tag.getName(),
                            tag.getPosts().size()))
                            .collect(Collectors.toSet()))
                .readingTime((Integer)4)
                .content(post.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(post.getStatus())
                .build();

    }
    public CreatePostRequest toCreatePostRequest(CreatePostDto createPostDto)
    {
        return CreatePostRequest.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .categoryId(createPostDto.getCategoryId())
                .tagIds(createPostDto.getTagIds())
                .status(createPostDto.getStatus())
                .build();
    }
    public UpdatePostRequest toUpdatePostRequest(UpdatePostDto request)
    {
        return UpdatePostRequest.builder()
                .id(request.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategoryId())
                .tagIds(request.getTagIds())
                .status(request.getStatus())
                .build();
    }

}
