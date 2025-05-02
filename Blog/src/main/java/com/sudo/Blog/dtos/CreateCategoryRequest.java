package com.sudo.Blog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryRequest
{
    @NotBlank(message="Name is required")
    @Size(min=3,max=50,message = "Size must be between {min} and {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$",message="Category name can contain only letters, numbers, spaces and hyphens")
    private String name;

}
