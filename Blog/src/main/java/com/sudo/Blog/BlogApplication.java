package com.sudo.Blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.*","com.sudo.Blog.mappers"})
public class BlogApplication
{
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
