package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.DbService;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user")
public class UserController {
    private final UserMapper userMapper;
    private final UserDbService service;

    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {
        List<User> users = service.getAllUsers();
        return userMapper.mapToUserDtoList(users);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getUser")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(service.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteUser")
    public void deleteUser(@RequestParam Long userId) {
            service.delete(userId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        User savedUser = service.saveUser(user);
        return userMapper.mapToUserDto(savedUser);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "generateUserKey")
    public void generateUserKey(@RequestParam Long userId) {
        service.generateKey(userId);
    }


    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.createUser(user);
    }
}
