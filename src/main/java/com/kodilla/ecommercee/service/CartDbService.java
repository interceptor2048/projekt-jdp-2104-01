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
import com.kodilla.ecommercee.exception.UserNotAuthenticatedException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartDao cartDao;
    private final ProductDao productDao;
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final UserAuthenticator authenticator;

    public void createCart(Long userId, String userKeyToCheck) throws UserNotFoundException,
            UserNotAuthenticatedException {
        User user = userDao.findById(userId).orElseThrow(UserNotFoundException::new);
        if (authenticator.isAuthenticated(user, userKeyToCheck)) {
            if (user.getCart() == null) {
                Cart cart = new Cart(user);
                save(cart);
                user.setCart(cart);
                userDao.save(user);
            }
        }
    }

    public Cart save(Cart cart){
        return cartDao.save(cart);
    }

    public List<Product> getCartProducts(Long cartId, String userKeyToCheck) throws CartNotFoundException,
            UserNotAuthenticatedException {
        Cart cart = getCart(cartId);
        if (authenticator.isAuthenticated(cart.getUser(), userKeyToCheck)){
            return cart.getListOfProducts();
        } else {
            return new ArrayList<>();
        }
    }

    public void removeProduct (Long cartId, Long productId, String userKeyToCheck) throws CartNotFoundException,
            ProductNotFoundException, UserNotAuthenticatedException {
        Cart cart = getCart(cartId);
        if (authenticator.isAuthenticated(cart.getUser(), userKeyToCheck)){
            Product product = productDao.findById(productId).orElseThrow(ProductNotFoundException::new);
            cart.getListOfProducts().remove(product);
            cartDao.save(cart);
            product.getCartList().remove(cart);
            productDao.save(product);
        }
    }

    public void addProducts (Long cartId, List<Product> products, String userKeyToCheck)
            throws CartNotFoundException, UserNotAuthenticatedException {
        Cart cart = getCart(cartId);
        if (authenticator.isAuthenticated(cart.getUser(), userKeyToCheck)){
            cart.getListOfProducts().addAll(products);
            cartDao.save(cart);
            for (Product product : products){
                product.getCartList().add(cart);
                productDao.save(product);
            }
        }
    }

    public void closeCart(Long cartId, String userKeyToCheck) throws CartNotFoundException,
            UserNotAuthenticatedException {
        Cart cart = getCart(cartId);
        User user = cart.getUser();
        if (authenticator.isAuthenticated(user, userKeyToCheck)){
            List<Product> products = new ArrayList<>(cart.getListOfProducts());
            cart.getListOfProducts().clear();
            removeCartFromProducts(cart, products);
            cartDao.save(cart);
            Order order = new Order(user, LocalDateTime.now(), products);
            orderDao.save(order);
        }
    }

    private void removeCartFromProducts(Cart cart, List<Product> products){
        for (Product product : products){
            product.getCartList().remove(cart);
        }
    }

    public Cart getCart(Long cartId) throws CartNotFoundException {
        Optional<Cart> optCart = cartDao.findById(cartId);
        return optCart.orElseThrow(CartNotFoundException::new);
    }

}
