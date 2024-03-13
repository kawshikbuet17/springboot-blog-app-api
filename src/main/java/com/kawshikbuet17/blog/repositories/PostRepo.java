package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.Category;
import com.kawshikbuet17.blog.entities.Post;
import com.kawshikbuet17.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}
