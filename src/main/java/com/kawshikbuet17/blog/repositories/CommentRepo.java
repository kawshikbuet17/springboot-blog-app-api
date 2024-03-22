package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
}
