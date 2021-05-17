package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.*;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import com.kodilla.ecommercee.service.DbService;
import com.kodilla.ecommercee.service.UserAuthenticator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {

    private final CartDbService cartService;
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

    @PutMapping(value = "addProducts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProducts(@RequestParam Long cartId, @RequestParam String userKey,
                            @RequestBody List<ProductDto> productDtos)
            throws CartNotFoundException, UserNotAuthenticatedException, GroupNotFoundException {
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
    public void closeCart(@RequestParam Long cartId, @RequestParam String userKey) throws UserNotAuthenticatedException, CartNotFoundException {
        cartService.closeCart(cartId, userKey);
    }

}
