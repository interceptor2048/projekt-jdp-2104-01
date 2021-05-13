package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    //@Autowired
    private UserDbService userDao;
    //@Autowired
    private CartDao cartDao;
    @RequestMapping(method = RequestMethod.GET, value = "getUsers")
    public List<UserDto> getUsers() {


        User user = new User("Wojtek", 1, "random", LocalDateTime.now());
        Cart cart = new Cart();
        cart.setCartId(1L);
        System.out.println(cart.getCartId());
        user.setCart(cart);

        service.saveUser(user);
        //userDao.saveUser(user);
        //cart.setUser(user);
        //cartDao.save(cart);
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
    public UserDto generateUserKey(@RequestParam Long userId) {
        try {
            UserDto userToChangeKey = getUser(userId);
            int key = (int)(Math.random()*20000);
            userToChangeKey.setUserKey(String.valueOf(key));
            userToChangeKey.setExpirationTime(LocalDateTime.now().plusHours(1L));
            User savedUser = service.saveUser(userMapper.mapToUser(userToChangeKey));
            return userMapper.mapToUserDto(savedUser);
        } catch (UserNotFoundException e) {
            System.out.println("User not found");
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.createUser(user);
    }
}
