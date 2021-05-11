package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsGroup;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@DisplayName("ProductDao Test Suites")
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
        Long id = product.getId();
        Optional<Product> savedProduct = Optional.ofNullable(productDao.findByNameAndPrice(product.getName(), product.getPrice()));

        //Then
        assertTrue(savedProduct.isPresent());
//        assertEquals("name", product.getName());
//        assertNotEquals(0, Optional.ofNullable(id));
        //CleanUp
        productDao.deleteById(id);
    }

    @Test
    void testProductsGroupSaveWithProduct() {
        //Given
        ProductsGroup productsGroup1 = new ProductsGroup("number one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product1 = new Product("name1", "description1",
                new BigDecimal("100"), productsGroup1, cartList, orderList);
        Product product2 = new Product("name2", "description2",
                new BigDecimal("200"), productsGroup1, cartList, orderList);
        product1.setProductsGroup(productsGroup1);
        product2.setProductsGroup(productsGroup1);
        productsGroup1.getProducts().add(product1);
        productsGroup1.getProducts().add(product2);
        //When
        productsGroupDao.save(productsGroup1);
        Long id = productsGroup1.getId();
        //Then
        assertNotEquals(Optional.of(0), id);
        //CleanUp
        productsGroupDao.deleteById(id);
    }
}
