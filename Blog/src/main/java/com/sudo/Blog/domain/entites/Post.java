package com.sudo.Blog.domain.entites;

import com.sudo.Blog.domain.PostStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="posts")
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PostStatus status;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="author_id",nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id",nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(name="post_tags",
            joinColumns = @JoinColumn(name="post_id"),
    inverseJoinColumns = @JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @Column(nullable = false)
    private Integer readingTime;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updateAt;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(readingTime,post.readingTime)&& Objects.equals(content, post.content) && status == post.status && Objects.equals(createdAt, post.createdAt) && Objects.equals(updateAt, post.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, status, createdAt, updateAt);
    }

    @PrePersist
    protected void onCreated()
    {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt=now;
        this.updateAt=now;
    }

    @PreUpdate
    protected  void onUpdate()
    {
        this.updateAt=LocalDateTime.now();
    }
}
