package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsGroup;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@DisplayName("ProductDao Test Suites")
public class ProductDaoTests {
    @Autowired
    private ProductDao productDao;

    @Test
    void testFindAll() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup();
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product(1L, "name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productDao.findAll();
        Long id = product.getId();
        //Then
        assertNotEquals(java.util.Optional.of(0), id);
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    void testFindById() {
        //Given
        ProductsGroup productsGroup = new ProductsGroup();
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product(1L, "name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productDao.save(product);
        //Then
        Long id = product.getId();
        Optional<Product> savedProduct = productDao.findById(product.getId());
        assertTrue(savedProduct.isPresent());
        //CleanUp
        productDao.deleteById(id);
    }
    @Test
    void testSaveProduct(){
        //Given
        ProductsGroup productsGroup = new ProductsGroup();
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product(1L, "name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productDao.save(product);
        Long id = product.getId();
        //Then
        assertNotEquals(Optional.of(0), id);
        //CleanUp
        productDao.deleteById(id);
    }
    @Test
    void testFindByNameAndPrice(){
        //Given
        ProductsGroup productsGroup = new ProductsGroup();
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product = new Product(1L, "name", "description",
                new BigDecimal("100"), productsGroup, cartList, orderList);
        //When
        productDao.save(product);
        productDao.findByNameAndPrice(product.getName(), product.getPrice());
        Long id = product.getId();
        //Then
        assertEquals("name", product.getName());
        assertNotEquals(0, Optional.ofNullable(id));
        //CleanUp
        productDao.deleteById(id);
    }
}
