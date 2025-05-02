package com.sudo.Blog.mappers;

import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.dtos.CategoryDto;
import com.sudo.Blog.dtos.CreateCategoryRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Builder
@Getter
@Setter
public class CategoryMapperImplementation
{
    public CategoryDto toDto(Category category)
    {
        return new CategoryDto(category.getId(),category.getName(),category.getPosts().size());
    }
   public  Category toEntity(CreateCategoryRequest categoryRequest)
    {
        Category entity = new Category();
        entity.setName(categoryRequest.getName());
        return entity;
    }
}
