package com.sudo.Blog.services.impl;

import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.repository.CategoryRepository;
import com.sudo.Blog.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService
{
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> listCategories()
    {
        return categoryRepository.findALlWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        String categoryName = category.getName();
        if(categoryRepository.existsByNameIgnoreCase(categoryName))
        {
            throw new IllegalArgumentException("Category already exits");
        }

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id)
    {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.get().getPosts().size()>0)
        {
            throw new IllegalStateException("Category has Posts Associated with it");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Category getCategoryById(UUID id)
    {
        return categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Category not found with id"+id));
    }
}
