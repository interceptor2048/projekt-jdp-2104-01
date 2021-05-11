package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OrderTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testCreate(){
        //given
        User user = new User("create", 1,
                "createKey", LocalDateTime.now());
        userDao.save(user);
        Order order = new Order(user);
        //when
        orderDao.save(order);
        long orderId = order.getId();

        //then
        assertNotEquals(0L, orderId);
    }
    @Test
    public void testRead(){
        //given
        User user = new User("readUser", 1,
                "readKey", LocalDateTime.now());
        userDao.save(user);
        Order orderToProcess = new Order(user);
        orderDao.save(orderToProcess);
        //when
        long orderId = orderToProcess.getId();
        Optional<Order> order = orderDao.findById(orderId);
        //then
        assertTrue(order.isPresent());
        assertNotEquals(0L, orderId);

    }


    @Test
    public void testUpdate(){
        //given
        User user = new User("updateUser", 1,
                "updateKey", LocalDateTime.now());
        userDao.save(user);
        User newUser = new User("newUpdateUser", 1,
                "newUpdateKey", LocalDateTime.now());
        userDao.save(newUser);
        //when
        Order order = new Order(user);
        orderDao.save(order);
        long firstId = order.getId();
        order.setOrderUser(newUser);
        orderDao.save(order);
        long secondId = order.getId();

        //then
        assertEquals(firstId, secondId);
    }

    @Test
    public void testDelete(){
        User user = new User("deleteUser", 1,
                "deleteKey", LocalDateTime.now());
        userDao.save(user);
        Order order = new Order(user);
        orderDao.save(order);
        long orderId = order.getId();
        //when
        orderDao.deleteById(orderId);
        Optional<Order> deletedOrder = orderDao.findById(orderId);
        //then
        assertFalse(deletedOrder.isPresent());
    }

}