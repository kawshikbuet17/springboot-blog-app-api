package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
