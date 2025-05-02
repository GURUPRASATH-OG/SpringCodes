package com.sudo.Blog.services.impl;

import com.sudo.Blog.domain.entites.Tag;
import com.sudo.Blog.repository.TagRepository;
import com.sudo.Blog.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService
{
    private final TagRepository tagRepository;
    @Override
    public List<Tag> listAllTage()
    {
        return tagRepository.findALlWithPostCount();
    }

    @Transactional
    @Override
    public List<Tag> createTag(Set<String> tagName)
    {
        List<Tag> existingTags = tagRepository.findByNameIn(tagName);
        existingTags.stream().
                map(Tag::getName).
                collect(Collectors.toSet());
       List<Tag> newTags= tagName.stream().
                filter(name->!existingTags.contains(name)).
                 map(name->Tag.builder().name(name).build())
                .toList();
       List<Tag> savedTags = new ArrayList<>();
       if(!newTags.isEmpty())
       {
           tagRepository.saveAll(newTags);
       }
       savedTags.addAll(existingTags);
        return savedTags;
    }

    @Transactional
    @Override
    public void deleteTag(UUID id)
    {
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty())
            {
                throw new IllegalStateException("Cannot delete tag with Posts");
            }
            tagRepository.deleteById(id);
        });

    }

    @Override
    public Tag getTagById(UUID id)
    {
        return tagRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Tag not found with Id:"+id));
    }

    @Override
    public List<Tag> getTagByIds(Set<UUID> uuids)
    {
        List<Tag> foundTags = tagRepository.findAllById(uuids);
        if(foundTags.size()!=uuids.size())
        {
            throw new EntityNotFoundException("Not all Specified Tags Exist");
        }
        return foundTags;
    }

}
