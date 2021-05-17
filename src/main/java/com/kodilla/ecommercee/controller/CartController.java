package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotAuthenticatedException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.DbService;
import com.kodilla.ecommercee.service.UserAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {

    private final CartDbService cartService;
    private final DbService userService;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @PostMapping(value = "createCart")
    public void createCart(@RequestParam Long userId, @RequestParam String userKey) throws UserNotFoundException,
            UserNotAuthenticatedException {
         cartService.createCart(userId, userKey);
    }

    @GetMapping(value = "getCartProducts")
    public List<ProductDto> getCartProducts (@RequestParam Long cartId, @RequestParam String userKey){
        try {
            List<Product> products = cartService.getCartProducts(cartId, userKey);
            return products.stream()
                    .map(productMapper::mapToProductDto)
                    .collect(Collectors.toList());
        } catch (CartNotFoundException | UserNotAuthenticatedException e){
            return new ArrayList<>();
        }
    }

    @PutMapping(value = "addProducts")
    public void addProducts(@RequestParam Long cartId, @RequestParam String userKey,
                            @RequestBody List<ProductDto> productDtos)
            throws CartNotFoundException, UserNotAuthenticatedException {
        List<Product> products = productMapper.mapToProductList(productDtos);
        cartService.addProducts(cartId, products, userKey);
    }

    @DeleteMapping(value = "removeProduct")
    public void removeProduct(@RequestParam Long cartId, @RequestParam Long productId,
                              @RequestParam String userKey)
            throws CartNotFoundException, ProductNotFoundException, UserNotAuthenticatedException {
        cartService.removeProduct(cartId, productId, userKey);
    }

    @PostMapping(value = "closeCart")
    public void closeCart(@RequestParam Long cartId, @RequestParam String userKey){

    }

}
