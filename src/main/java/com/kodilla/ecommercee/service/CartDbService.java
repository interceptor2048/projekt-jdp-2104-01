package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartDao cartDao;
    private final ProductDao productDao;

    public Cart save(Cart cart){
        return cartDao.save(cart);
    }

    public List<Product> getCartProducts(Long cartId) throws CartNotFoundException {
        Optional<Cart> cart = getCart(cartId);
        return cart.map(Cart::getListOfProducts).orElseThrow(CartNotFoundException::new);
    }

    public void removeProduct (Long cartId, Long productId) throws CartNotFoundException{
        Optional<Cart> optCart = getCart(cartId);
        Optional<Product> optProduct = productDao.findById(productId);
        if (optCart.isPresent() && optProduct.isPresent()){
            Cart cart = optCart.get();
            cart.getListOfProducts().remove(optProduct.get());
            cartDao.save(cart);
        } else {
            throw new CartNotFoundException();
        }
    }

    public Cart addProducts (Long cartId, List<Product> products) throws CartNotFoundException {
        Optional<Cart> optCart = getCart(cartId);
        if (optCart.isPresent()){
            Cart cart = optCart.get();
            cart.getListOfProducts().addAll(products);
            return cartDao.save(cart);
        }
        throw new CartNotFoundException();
    }

    public Optional<Cart> getCart(Long cartId){
        return cartDao.findById(cartId);
    }
}
