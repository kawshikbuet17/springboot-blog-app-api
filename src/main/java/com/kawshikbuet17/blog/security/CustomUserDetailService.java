package com.kawshikbuet17.blog.security;

import com.kawshikbuet17.blog.entities.User;
import com.kawshikbuet17.blog.exceptions.ResourceNotFoundException;
import com.kawshikbuet17.blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username (using email as username)
        User user = this.userRepo.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("User", "Email: "+username, 0));
        return user;
    }
}
