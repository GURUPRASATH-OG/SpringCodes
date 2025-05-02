package com.sudo.Blog.services;

import com.sudo.Blog.domain.entites.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService
{
    List<Tag> listAllTage();
    List<Tag> createTag(Set<String> tagName);
    void deleteTag(UUID id);
    Tag getTagById(UUID id);
    List<Tag> getTagByIds(Set<UUID>uuids);

}
