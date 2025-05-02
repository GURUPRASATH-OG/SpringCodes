package com.sudo.Blog.services;

import com.sudo.Blog.domain.entites.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService
{
    List<Category> listCategories();
    Category createCategory(Category Category);
    void deleteCategory(UUID id);
    Category getCategoryById(UUID id);
}
