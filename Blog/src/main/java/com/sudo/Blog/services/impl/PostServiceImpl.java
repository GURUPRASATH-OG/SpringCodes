package com.sudo.Blog.services.impl;

import com.sudo.Blog.domain.CreatePostRequest;
import com.sudo.Blog.domain.PostStatus;
import com.sudo.Blog.domain.UpdatePostRequest;
import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.Tag;
import com.sudo.Blog.domain.entites.User;
import com.sudo.Blog.repository.PostRepository;
import com.sudo.Blog.services.CategoryService;
import com.sudo.Blog.services.PostService;
import com.sudo.Blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService
{

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    private static final int WORDS_PER_MINUTE=200;
    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts(UUID categoryId, UUID tagId)
    {
        if(categoryId !=null && tagId !=null)
        {
            Category category = categoryService.getCategoryById(categoryId);
            Tag tag = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(
                    PostStatus.PUBLISHED,
                    category,
                    tag);

        }
        if(categoryId!=null)
        {
            Category category = categoryService.getCategoryById(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED,category);
        }
        if(tagId!=null)
        {
            Tag tags = tagService.getTagById(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED,tags);
        }
        return postRepository.findAllByStatus(PostStatus.PUBLISHED);
    }

    @Override
    public List<Post> getDraftPosts(User user)
    {
        return  postRepository.findAllByAuthorAndStatus(user,PostStatus.DRAFT);
    }

    @Override
    public Post createPost(User user, CreatePostRequest createPostRequest)
    {
        Post newPost = new Post();
        newPost.setTitle(createPostRequest.getTitle());
        newPost.setContent(createPostRequest.getContent());
        newPost.setStatus(createPostRequest.getStatus());
        newPost.setAuthor(user);
        newPost.setReadingTime(calculateReadingTime(createPostRequest.getContent()));
        Category category = categoryService.getCategoryById(createPostRequest.getCategoryId());
        newPost.setCategory(category);
        Set<UUID> tagIds = createPostRequest.getTagIds();
        List<Tag> tags = tagService.getTagByIds(tagIds);
        newPost.setTags(new HashSet<>(tags));
        return postRepository.save(newPost);
    }

    @Override
    @Transactional
    public Post updatePost(UUID id, UpdatePostRequest updatePostRequest)
    {
        Post existingPost =postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Post not exist with id"+id));
        existingPost.setTitle(updatePostRequest.getTitle());
        existingPost.setContent(updatePostRequest.getContent());
        existingPost.setStatus(updatePostRequest.getStatus());
        existingPost.setReadingTime(calculateReadingTime(updatePostRequest.getContent()));

        UUID updatePostRequestCategoryId = updatePostRequest.getCategoryId();
        if(!existingPost.getCategory().getId().equals(updatePostRequestCategoryId))
        {
            Category newCategory=categoryService.getCategoryById(updatePostRequestCategoryId);
            existingPost.setCategory(newCategory);
        }
        Set<UUID> existingTagIds =existingPost.getTags().stream().map(Tag::getId).collect(Collectors.toSet());
        Set<UUID> updatePostRequestTagIds = updatePostRequest.getTagIds();
        if(!existingTagIds.equals(updatePostRequestTagIds))
        {
            List<Tag> newTags =tagService.getTagByIds(updatePostRequestTagIds);
            existingPost.setTags(new HashSet<>(newTags));
        }

        return postRepository.save(existingPost);
    }

    @Override
    public Post getPostById(UUID id)
    {
        return postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No post found with Id:"+id));
    }

    @Override
    public void deletePost(UUID id)
    {
        Post postToDelete = getPostById(id);
        postRepository.delete(postToDelete);
    }

    public Integer calculateReadingTime(String Content)
    {
        if(Content ==null)
        {
            return 0;
        }
        int wordCount =Content.trim().split("\\s+").length;
        return (int) Math.ceil((double) wordCount/WORDS_PER_MINUTE);
    }
}
