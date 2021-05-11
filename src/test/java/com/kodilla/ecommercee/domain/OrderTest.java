package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.OrderDao;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class OrderTest {

    @Autowired
    private OrderDao orderDao;


    @Test
    public void testSave(){
        //given
        User user = new User(
                1L, "testUser", 1,
                "testkey", LocalDateTime.now().minusHours(5));

        Order order = new Order(user);
        //when
        orderDao.save(order);
        long orderId = order.getId();

        //then
        assertNotEquals(0L, orderId);

        //cleanup
        orderDao.deleteById(orderId);

    }
}