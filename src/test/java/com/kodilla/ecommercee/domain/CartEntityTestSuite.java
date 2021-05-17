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
import java.time.LocalDateTime;
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
        Cart cart = new Cart();

        //when
        cartDao.save(cart);
        Long cartId = cart.getCartId();

        //then
        assertNotEquals(0, cartId);

        //clean
        cartDao.deleteById(cartId);
    }

    @Test
    public void testReadDataFromCart() {
        //given
        Cart cart = new Cart();
        cartDao.save(cart);

        //when
        Long id = cart.getCartId();
        Optional<Cart> cartFromDb = cartDao.findById(id);

        //then
        assertTrue(cartFromDb.isPresent());

        //clean
        cartDao.deleteById(id);
    }

    @Test
    public void testUpdateDataOnCart() {
        //given
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

        //clean
        Long productId = testProduct.getId();
        Long prodGroupId = newGroup.getId();
        productDao.deleteById(productId);
        productsGroupDao.deleteById(prodGroupId);
        cartDao.deleteById(cart.getCartId());
    }

    @Test
    public void testDeleteCart() {
        //given
        Cart cart = new Cart();
        cartDao.save(cart);

        //when
        Long id = cart.getCartId();
        cartDao.deleteById(id);
        Optional<Cart> deletedCart = cartDao.findById(id);

        //then
        assertFalse(deletedCart.isPresent());
    }

    @Test
    public void testRelationWithUser() {
        //given
        User user = new User("username3", 1, "hgdasa", LocalDateTime.now());
        Cart cart = new Cart();
        Cart zapisanyCart = cartDao.save(cart);
        user.setCart(zapisanyCart);
        ProductsGroup newGroup = new ProductsGroup("test group2");
        User zapisany = userDao.save(user);
        ProductsGroup productGroupsZapisany = productsGroupDao.save(newGroup);
        Product testProduct = new Product("test product2", "test description2", new BigDecimal(300), newGroup);
        Product productZapisany = productDao.save(testProduct);

        //when
        zapisany.getCart().getListOfProducts().add(testProduct);

        int listSize = cart.getListOfProducts().size();

        //then
        assertEquals(1, listSize);

        //clean
        Long cartId = zapisany.getCart().getCartId();
        Long userId = zapisany.getId();
        Long productId = productZapisany.getId();
        Long prodGroupId = productGroupsZapisany.getId();
        productDao.deleteById(productId);
        productsGroupDao.deleteById(prodGroupId);
        userDao.deleteById(userId);
    }

    @Test
    public void testRelationWithProduct() {
        //given
        Cart cart = new Cart();
        cartDao.save(cart);
        ProductsGroup newGroup = new ProductsGroup("test group3");
        productsGroupDao.save(newGroup);
        Product testProduct = new Product("test product3", "test description3", new BigDecimal(300), newGroup);
        testProduct.getCartList().add(cart);
        productDao.save(testProduct);
        cart.getListOfProducts().add(testProduct);
        cartDao.save(cart);

        //when
        Long cartId = cart.getCartId();
        String productName = cart.getListOfProducts().get(0).getName();
        int listSize = testProduct.getCartList().size();

        //then
        assertNotEquals(0, cartId);
        assertEquals("test product3", productName);
        assertEquals(1, listSize);

        //clean
        Long prodGroupId = newGroup.getId();
        Long productId = testProduct.getId();
        productDao.deleteById(productId);
        productsGroupDao.deleteById(prodGroupId);
        cartDao.deleteById(cartId);
    }
}
