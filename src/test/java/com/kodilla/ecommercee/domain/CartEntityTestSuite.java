package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class CartEntityTestSuite {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductsGroupDao productsGroupDao;

    @Test
    public void testSaveCart() {
        //given
        User user = new User("username1", 1, "kj7dai");
        userDao.save(user);
        Cart cart = new Cart(user);

        //when
        cartDao.save(cart);
        Long cartId = cart.getCartId();

        //then
        assertNotEquals(0, cartId);
    }

    @Test
    public void testReadDataFromCart() {
        //given
        User user = new User("username2", 1, "87921m");
        userDao.save(user);
        Cart cart = new Cart(user);
        cartDao.save(cart);

        //when
        Long id = cart.getCartId();
        Optional<Cart> cartFromDb = cartDao.findById(id);

        //then
        assertTrue(cartFromDb.isPresent());
    }

    @Test
    public void testUpdateDataOnCart() {
        //given
        User user = new User("username3", 1, "hgdasa");
        userDao.save(user);
        Cart cart = new Cart();
        cartDao.save(cart);

        //when
        ProductsGroup newGroup = new ProductsGroup("test group");
        productsGroupDao.save(newGroup);
        Product testProduct = new Product("test product", "test description", new BigDecimal(300), newGroup);
        productDao.save(testProduct);
        cart.getListOfProducts().add(testProduct);
        cartDao.save(cart);

        //then
        assertEquals(1, cart.getListOfProducts().size());
    }

    @Test
    public void testDeleteCart() {
        //given
        User user = new User("username4", 1, "sah98aa");
        userDao.save(user);
        Cart cart = new Cart();
        cart.setUser(user);
        cartDao.save(cart);

        //when
        Long id = cart.getCartId();
        cartDao.deleteById(id);
        Optional<Cart> deletedCart = cartDao.findById(id);

        //then
        assertFalse(deletedCart.isPresent());
    }
}
