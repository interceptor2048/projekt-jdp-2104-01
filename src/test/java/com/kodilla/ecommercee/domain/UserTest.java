package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserTest {
    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;
    @Test
    public void testCreate(){
        //given
        User user = new User("Wojtek", 1, "random", LocalDateTime.now());
        Cart cart = new Cart();
        userDao.save(user);
        cart.setUser(user);
        cartDao.save(cart);
        //user.setCart(cart);
        //cart.setUser(user);
        //cartDao.save(cart);
        //System.out.println(cart.getCartId());
        System.out.println(user.getUserKey());
        System.out.println(cart.getUser().getUsername());
        System.out.println(user.getCart().getCartId());

        //System.out.println(user.getCart().getCartId());
        //Order order = new Order(user, LocalDateTime.now());
        //when
        //orderDao.save(order);
        //long orderId = order.getId();

        //then
        //assertNotEquals(0L, orderId);
    }
}
