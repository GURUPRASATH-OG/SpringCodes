package com.sudo.Blog.repository;

import com.sudo.Blog.domain.PostStatus;
import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.domain.entites.Tag;
import com.sudo.Blog.domain.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostRepository extends JpaRepository<Post, UUID>
{
    List<Post> findAllByStatusAndCategoryAndTagsContaining(PostStatus status, Category category, Tag tag);
    List<Post>  findAllByStatusAndCategory(PostStatus status,Category category);
    List<Post> findAllByStatusAndTagsContaining(PostStatus postStatus,Tag tag);
    List<Post> findAllByStatus(PostStatus status);
    List<Post> findAllByAuthorAndStatus(User author, PostStatus status);

}
