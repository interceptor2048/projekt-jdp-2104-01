package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public Cart mapToCart(CartDto cartDto){
        return new Cart(
                cartDto.getCartId(),
                cartDto.getUser());
    }
    public CartDto mapToUserDto(Cart cart){
        return new CartDto(
                cart.getCartId(),
                cart.getUser());
    }

}
