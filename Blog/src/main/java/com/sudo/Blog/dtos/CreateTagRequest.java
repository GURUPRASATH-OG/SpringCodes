package com.sudo.Blog.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTagRequest
{
    @NotEmpty(message = "At least one tag is required")
    @Size(max=10,message = "Maximum {max} tags are allowed")
    private Set<
            @Size(min=2,max=30,message ="Tag name must be between {min} and {max} characters.")
            @Pattern(regexp="^[\\w\\s-]+$",message="Tag name can contain only letters, numbers, spaces, and hyphens")
                    String> names;
}
