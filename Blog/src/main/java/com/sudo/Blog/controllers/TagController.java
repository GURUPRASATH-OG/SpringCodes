package com.sudo.Blog.controllers;

import com.sudo.Blog.domain.entites.Tag;
import com.sudo.Blog.dtos.CreateTagRequest;
import com.sudo.Blog.dtos.TagResponse;
import com.sudo.Blog.mappers.TagMapper;
import com.sudo.Blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController
{
    private final TagService tagService;
    private final TagMapper tagMapper;
    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags()
    {
        List<Tag> tags = tagService.listAllTage();
        return new ResponseEntity<>( tags.stream().map(tagMapper::toDto).toList(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<List<TagResponse>>createTag(@RequestBody CreateTagRequest tag)
    {
       List<Tag> savedTags= tagService.createTag(tag.getNames());
       List<TagResponse> createdTags = savedTags.stream().map(tagMapper::toDto).toList();
       return new ResponseEntity<>(createdTags,HttpStatus.CREATED);
    }
    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deleteTag(@PathVariable UUID id)
    {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
