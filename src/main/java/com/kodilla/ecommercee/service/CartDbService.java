package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartDao cartDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;

    public Cart createCart(Long userId) throws UserNotFoundException {
        User user = userDao.findById(userId).orElseThrow(UserNotFoundException::new);
        //tutaj sprawdzic klucz
        //authenticatorService -> przekazywac id uzytkownika i sprawdzac czy klucz jest poprawny i aktywny
        Cart cart = new Cart(user);
        return cartDao.save(cart);
    }

    public Cart save(Cart cart){
        return cartDao.save(cart);
    }

    public List<Product> getCartProducts(Long cartId) throws CartNotFoundException {
        Cart cart = getCart(cartId);
        return cart.getListOfProducts();
    }

    public void removeProduct (Long cartId, Long productId) throws CartNotFoundException,
            ProductNotFoundException {
        Cart cart = getCart(cartId);
        Product product = productDao.findById(productId).orElseThrow(ProductNotFoundException::new);
        cart.getListOfProducts().remove(product);
        cartDao.save(cart);
    }

    public Cart addProducts (Long cartId, List<Product> products) throws CartNotFoundException {
        Cart cart = getCart(cartId);
        cart.getListOfProducts().addAll(products);
        return cartDao.save(cart);
    }

    public Order closeCart(Long cartId) throws CartNotFoundException {
        Cart cart = getCart(cartId);
        User cartUser = cart.getUser();
        return null;

    }

    public Cart getCart(Long cartId) throws CartNotFoundException {
        Optional<Cart> optCart = cartDao.findById(cartId);
        return optCart.orElseThrow(CartNotFoundException::new);
    }
}
