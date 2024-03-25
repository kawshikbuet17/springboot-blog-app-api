package com.kawshikbuet17.blog.controllers;

import com.kawshikbuet17.blog.payloads.ApiResponse;
import com.kawshikbuet17.blog.payloads.UserDto;
import com.kawshikbuet17.blog.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //POST - create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //PUT - update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE - delete user
    //Only Admin can do this
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    //GET - get all users
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    //GET - get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
