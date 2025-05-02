package com.sudo.Blog.controllers;

import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.dtos.CategoryDto;
import com.sudo.Blog.dtos.CreateCategoryRequest;
import com.sudo.Blog.mappers.CategoryMapper;
import com.sudo.Blog.mappers.CategoryMapperImplementation;
import com.sudo.Blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="/api/v1/categories")
@RequiredArgsConstructor //for initializing the dependencies
public class CategoryController
{
    private final CategoryService categoryService;
    private final CategoryMapperImplementation mapper;
    @GetMapping
    public ResponseEntity <List<CategoryDto>> listCategories()
    {
        List<CategoryDto> categories = categoryService.listCategories().stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(categories);
    }
    @PostMapping()
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest)
    {
        Category categoryToCreate = mapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(categoryToCreate);
        return new ResponseEntity<>(mapper.toDto(savedCategory),HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMapping(@PathVariable UUID id)
    {
        categoryService.deleteCategory(id);
       return new ResponseEntity<>("Category Deleted Successfully", HttpStatus.NO_CONTENT);
    }
}
