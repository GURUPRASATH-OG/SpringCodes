package com.sudo.Blog.repository;

import com.sudo.Blog.domain.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>
{
    //this query will solve the n+1
    // when findall() will give list<categories>
    // so hibenate will go one by category to find the post associated with it with each category.;
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts")
    List<Category> findALlWithPostCount();

    boolean existsByNameIgnoreCase(String name);
}
