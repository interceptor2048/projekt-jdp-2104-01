package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.*;

import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.CartDbService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {
    private final CartDbService cartService;
    private final CartMapper cartMapper;
    private final UserController userController;
    private final UserMapper userMapper;


    @RequestMapping(method = RequestMethod.POST, value = "createCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        Cart cart = cartMapper.mapToCart(cartDto);
       /* Cart cart = new Cart(user);
        user.setCart(cart);*/
        cartService.createCart(cart);
    }

    @RequestMapping(method = RequestMethod.GET, value = "upCarts")
    //@RequestMapping(method = RequestMethod.PUT, value = "upCarts")
    public CartDto upCarts() {

        try {
            System.out.println("proba");
            UserDto userTemp = userController.getUser(1L);

            User temp = userMapper.mapToUser(userTemp);
            CartDto nowyCart = new CartDto();
            Cart zapisanyCart = cartService.createCart(cartMapper.mapToCart(nowyCart));
            temp.setCart(zapisanyCart);
            System.out.println(temp.getCart().getCartId());
            UserDto poZmianie = userController.updateUser(temp);
           // System.out.println(poZmianie.get);

        } catch (UserNotFoundException e) {

        }
    return new CartDto();
    }




    }