package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.service.CartDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDbService cartDao;

    @Test
    public void testCreate(){
        //given
        User user = new User("Wojtek", 1, "random", LocalDateTime.now());
        Cart cart = new Cart();
        cart = cartDao.createCart(cart);
        user.setCart(cart);

        //when
        User testowy = userDao.save(user);

        //then
        assertNotNull(testowy);
        userDao.delete(testowy);
    }

    @Test
    public void testIfUserHasCart(){
        //given
        User user = new User("Wojtek", 1, "random", LocalDateTime.now());
        Cart cart = new Cart();
        cart = cartDao.createCart(cart);
        user.setCart(cart);

        //when
        User testowy = userDao.save(user);

        //then
        assertNotNull(testowy.getCart());
        userDao.delete(testowy);
    }

    @Test
    public void testIfUserIdOk(){

        //given
        User user = new User("Wojtek", 1, "random", LocalDateTime.now());
        Cart cart = new Cart();
         cart = cartDao.createCart(cart);

        user.setCart(cart);
        cart.setUser(user);

        //when
        User testowy = userDao.save(user);

        //then
        assertEquals(testowy.getCart().getUser(), testowy);
        userDao.delete(testowy);
    }

}
