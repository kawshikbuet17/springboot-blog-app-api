package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
