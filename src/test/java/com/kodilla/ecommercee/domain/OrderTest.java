package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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

    @Autowired
    private ProductsGroupDao productsGroupDao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void testCreate(){
        //given
        User user = new User("create", 1,
                "createKey");
        userDao.save(user);
        Order order = new Order(user, LocalDateTime.now());
        //when
        orderDao.save(order);
        long orderId = order.getId();

        //then
        assertNotEquals(0L, orderId);

        //clean
        orderDao.deleteById(orderId);
    }
    @Test
    public void testRead(){
        //given
        User user = new User("readUser", 1,
                "readKey");
        userDao.save(user);
        Order orderToProcess = new Order(user, LocalDateTime.now());
        orderDao.save(orderToProcess);
        //when
        long orderId = orderToProcess.getId();
        Optional<Order> order = orderDao.findById(orderId);
        //then
        assertTrue(order.isPresent());
        assertNotEquals(0L, orderId);

        //clean
        orderDao.deleteById(orderId);
        Long userId = user.getId();
        userDao.deleteById(userId);

    }

    @Test
    public void testUpdate(){
        //given
        User user = new User("updateUser", 1,
                "updateKey");
        userDao.save(user);
        User newUser = new User("newUpdateUser", 1,
                "newUpdateKey");
        userDao.save(newUser);
        //when
        Order order = new Order(user, LocalDateTime.now());
        orderDao.save(order);
        long firstId = order.getId();
        order.setUser(newUser);
        orderDao.save(order);
        long secondId = order.getId();

        //then
        assertEquals(firstId, secondId);
        assertEquals("newUpdateUser", order.getUser().getUsername());

        //clean
        orderDao.deleteById(secondId);
        Long userId = user.getId();
        userDao.deleteById(userId);
        Long newUserId = newUser.getId();
        userDao.deleteById(newUserId);
    }

    @Test
    public void testDelete(){
        User user = new User("deleteUser", 1,
                "deleteKey");
        userDao.save(user);
        Order order = new Order(user, LocalDateTime.now());
        orderDao.save(order);
        long orderId = order.getId();
        //when
        orderDao.deleteById(orderId);
        Optional<Order> deletedOrder = orderDao.findById(orderId);
        //then
        assertFalse(deletedOrder.isPresent());

        //clean
        Long userId = user.getId();
        userDao.deleteById(userId);
    }

    @Test
    public void testUserOderRelationship(){
        //given
        User user = new User("userOrderRelationshipUser", 1,
                "userOrderRelationshipUserKey");
        userDao.save(user);
        Order order = new Order(user, LocalDateTime.now());
        orderDao.save(order);
        //when&then
        long userId = user.getId();
        long orderUserId = order.getUser().getId();
        assertEquals(userId, orderUserId);

        //clean
        Long orderId = order.getId();
        orderDao.deleteById(orderId);
        userDao.deleteById(userId);
    }

    @Test
    public void testProductOrderRelationship(){
        User user = new User("productOrderRelationshipUser", 1,
                "productOrderRelationshipUser");
        userDao.save(user);
        ProductsGroup group = new ProductsGroup("productOrderTestGroup");
        productsGroupDao.save(group);
        Product product = new Product("productOrderTestProduct", "description",
                BigDecimal.valueOf(10), group);
        productDao.save(product);
        //when
        Order order = new Order(user, LocalDateTime.now());
        order.setUser(user);
        order.getProductList().add(product);
        orderDao.save(order);
        //then
        assertEquals(1, order.getProductList().size());

        long productId = order.getProductList().get(0).getId();
        assertNotEquals(0L, productId);

        //clean
        //Long productId = product.getId();
        Long orderId = order.getId();
        orderDao.deleteById(orderId);
        productDao.deleteById(productId);
        Long prodGroupId = group.getId();
        productsGroupDao.deleteById(prodGroupId);
        Long userId = user.getId();
        userDao.deleteById(userId);
    }
}