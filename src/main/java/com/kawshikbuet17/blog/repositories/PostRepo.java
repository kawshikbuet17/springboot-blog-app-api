package com.kawshikbuet17.blog.repositories;

import com.kawshikbuet17.blog.entities.Category;
import com.kawshikbuet17.blog.entities.Post;
import com.kawshikbuet17.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
    /*
        Alternative of List<Post> findByTitleContaining(String title);
        Because it didn't work in the tutorial, but worked in my code

        @Query("select p from Post p where p.title like :key")
        List<Post> searchByTitle(@Param("key") String title);

        And in the PostServiceImpl,
        this.postRepo.searchByTitle("%" + keyword + "%");
    */
}
