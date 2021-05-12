package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/cart")
public class CartController {

    private final CartDbService service;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    @PostMapping(value = "createCart")
    public void createCart() {
        Cart cart = new Cart();
        service.save(cart);
        System.out.println(cart.getCartId());
    }

    @GetMapping(value = "getCartProducts")
    public List<ProductDto> getCartProducts (@RequestParam Long cartId){
        try {
            List<Product> products = service.getCartProducts(cartId);
            return products.stream()
                    .map(productMapper::mapToProductDto)
                    .collect(Collectors.toList());
        } catch (CartNotFoundException e){
            return new ArrayList<>();
        }
    }

    @PostMapping(value = "addProducts")
    public void addProducts(@RequestBody List<ProductDto> productDtos){

    }

    @DeleteMapping(value = "removeProduct")
    public void removeProduct(@RequestParam Long
                                      productId){

    }

    @PostMapping(value = "closeCart")
    public void closeCart(@RequestParam Long cartId){

    }

}
