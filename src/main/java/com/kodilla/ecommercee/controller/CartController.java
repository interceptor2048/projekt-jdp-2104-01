package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    @PostMapping(value = "createCart")
    public void createCart() {

    }

    @GetMapping(value = "getCartProducts")
    public List<ProductDto> getCartProducts
            (@RequestParam Long cartId){

        return new ArrayList<>();
    }

    @PostMapping(value = "addProducts")
    public void addProducts(@RequestBody
                                    List<ProductDto> productDtos){

    }

    @DeleteMapping(value = "removeProduct")
    public void removeProduct(@RequestParam Long
                                      productId){

    }

    @PostMapping(value = "closeCart")
    public void closeCart(@RequestParam Long cartId){

    }

}
