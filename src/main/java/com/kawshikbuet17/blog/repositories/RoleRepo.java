package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
}
