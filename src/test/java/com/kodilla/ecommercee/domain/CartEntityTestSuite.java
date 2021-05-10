package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dao.CartDao;
import com.kodilla.ecommercee.dao.UserDao;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CartEntityTestSuite {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;


    @Test
    public void testSaveCart() {
        //given
        User user = new User("username1", 1, "kj7dai");

        //when
        userDao.save(user);
        Long id = user.getId();

        //then
        assertNotEquals(0, id);
    }
}
