package com.sudo.Blog.mappers;

import com.sudo.Blog.domain.PostStatus;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.Tag;
import com.sudo.Blog.dtos.TagResponse;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TagMapper
{

    public TagResponse toDto(Tag tag)
    {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName()).
                postCount((calculatePostCount(tag.getPosts())))
                .build();
    }
    private Integer calculatePostCount(Set<Post> posts)
    {
        if(posts==null)
        {
            return 0;
        }
        return (int) posts.stream().filter(post->PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
