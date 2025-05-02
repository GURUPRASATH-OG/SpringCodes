package com.sudo.Blog.dtos;

import lombok.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto
{
    private UUID id;
    private String name;
}
