package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.CartDao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartDao cartRepository;


    public Cart createCart(final Cart cart) {
        return cartRepository.save(cart);

    }
}
