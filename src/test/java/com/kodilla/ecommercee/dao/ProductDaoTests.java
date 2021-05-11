package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsGroup;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()

public class ProductDaoTests {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductsGroupDao productsGroupDao;

    @Test
    public void testFindAll() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup("new one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product("name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productsGroupDao.save(productsGroup);
        productDao.save(product);
        productDao.findAll();
        Long id = product.getId();
        System.out.println(id);
        //Then
        assertNotEquals(java.util.Optional.of(0), id);
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    public void testFindById() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup("new one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product("name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productsGroupDao.save(productsGroup);
        productDao.save(product);
        Long id = product.getId();

        //Then
        Optional<Product> savedProduct = productDao.findById(product.getId());
        assertTrue(savedProduct.isPresent());
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    public void testSaveProduct() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup("new one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product("name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productsGroupDao.save(productsGroup);
        productDao.save(product);
        Long id = product.getId();
        //Then
        assertNotEquals(Optional.of(0), id);
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    public void testFindByNameAndPrice() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup("new one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product("name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productsGroupDao.save(productsGroup);
        productDao.save(product);
        productDao.findByNameAndPrice(product.getName(), product.getPrice());
        Long id = product.getId();
//        List<Product> savedProduct =

        //Then
//        assertEquals("name", savedProduct.equals("name"));
        assertNotEquals(0, Optional.ofNullable(id));
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    public void testSaveManyToManyOnOrderList(){
        //Given
        //When
        //Then
        //CleanUp
    }
}
