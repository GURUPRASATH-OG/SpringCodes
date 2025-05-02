package com.sudo.Blog.mappers;

import com.sudo.Blog.domain.PostStatus;
import com.sudo.Blog.domain.entites.Category;
import com.sudo.Blog.domain.entites.Post;
import com.sudo.Blog.dtos.CategoryDto;
import com.sudo.Blog.dtos.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import java.util.List;
//we are using mapstruct to write mapper for us it will take of mapping.
//@mapper will specify mapper properties like componentModel which tells which type of application
//unmappedTargetPolicy it will ignore properties which not able map rather than throwing a error.
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper
{
    //here @Mapping we define target to which need to set value by manual like for postCount we need to set value manually
    //we are specifying the method name will return postCount value so we can populate while mapping to our Dto
    @Mapping(target="postCount",source="posts",qualifiedByName="calculatePostCount")
    CategoryDto toDto(Category category);
    Category toEntity(CreateCategoryRequest createCategoryRequest);
    @Named("calculatePostCount")// we are setting named for our method so that it can become bean
    default long calculatePostCount(List<Post> posts)
    {
        if(null == posts)
        {
            return 0;
        }
        return posts.stream()
                .filter(post-> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }
}
