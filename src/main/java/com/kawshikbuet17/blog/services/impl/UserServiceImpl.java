package com.kawshikbuet17.blog.services.impl;

import com.kawshikbuet17.blog.entities.User;
import com.kawshikbuet17.blog.exceptions.ResourceNotFoundException;
import com.kawshikbuet17.blog.payloads.UserDto;
import com.kawshikbuet17.blog.repositories.UserRepo;
import com.kawshikbuet17.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo
                    .findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        user.setName(userDto.getName());
        user.setEmail(user.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);
        return userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos =  users.stream().map(user->this.userToUserDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo
                .findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        this.userRepo.delete(user);
    }

    public User userDtoToUser(UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToUserDto(User user){
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
