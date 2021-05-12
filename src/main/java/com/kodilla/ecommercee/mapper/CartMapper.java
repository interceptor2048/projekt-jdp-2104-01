package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.CartDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartMapper {

    private final ProductMapper productMapper;

    public CartDto mapToCartDto(Cart cart){
        return new CartDto(cart.getCartId(),
                cart.getListOfProducts().stream()
                        .map(productMapper::mapToProductDto)
                        .collect(Collectors.toList()));
    }

    public Cart mapToCart(CartDto cartDto){
        return new Cart(cartDto.getCartId(),
                cartDto.getListOfProducts().stream()
                    .map(productMapper::mapToProduct)
                    .collect(Collectors.toList()));
    }


}
