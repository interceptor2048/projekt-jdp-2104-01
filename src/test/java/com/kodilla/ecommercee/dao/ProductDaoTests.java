package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Autowired
    private CartDao cartDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;

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
        Long prodGroupId = productsGroup.getId();
        productDao.deleteById(id);
        productsGroupDao.deleteById(prodGroupId);
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
        Long prodGroupId = productsGroup.getId();
        productDao.deleteById(id);
        productsGroupDao.deleteById(prodGroupId);
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
        Long prodGroupId = productsGroup.getId();
        productsGroupDao.deleteById(prodGroupId);
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
        List<Product> listOfProducts = productDao.findByNameAndPrice(product.getName(), product.getPrice());
        //żeby wartość którą zwraca ta metoda w linii 109 mogła być użyta poniżej do porównania (a takie chyba było założenie)
        //trzeba w prowadzic te zmiany w kodzie
        Long id = listOfProducts.get(0).getId();

        //Then
        assertNotEquals(0, Optional.ofNullable(id));

        //CleanUp
        productDao.deleteById(id);
        Long prodGroupId = productsGroup.getId();
        productsGroupDao.deleteById(prodGroupId);
    }

    @Test
    public void testProductsGroupSaveWithProduct() {
        //Given
        ProductsGroup productsGroup1 = new ProductsGroup("number one");
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        Product product1 = new Product("name1", "description1",
                new BigDecimal("100"), productsGroup1, cartList, orderList);
        Product product2 = new Product("name2", "description2",
                new BigDecimal("200"), productsGroup1, cartList, orderList);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        productsGroupDao.save(productsGroup1);
        Long id = productsGroup1.getId();
        productDao.save(product1);
        Long product1Id = product1.getId();
        productDao.save(product2);
        Long product2Id = product2.getId();
        product1.setProductsGroup(productsGroup1);
        product2.setProductsGroup(productsGroup1);
        productsGroup1.getProducts().add(product1);
        productsGroup1.getProducts().add(product2);
        //When&Then
        assertNotEquals(Optional.of(0), id);
        //CleanUp
        productDao.deleteById(product1Id);
        productDao.deleteById(product2Id);
        productsGroupDao.deleteById(id);
    }

    @Test
    public void testSaveManyToManyOnCartList() {
        //Given
        ProductsGroup productsGroup1 = new ProductsGroup("number one");
        List<Order> orderList = new ArrayList<>();
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart2);
        cartList.add(cart3);
        Product product1 = new Product("name1", "description1",
                new BigDecimal("100"), productsGroup1, cartList, orderList);
        Product product2 = new Product("name2", "description2",
                new BigDecimal("200"), productsGroup1, cartList, orderList);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        cart1.setListOfProducts(productList);
        cart2.setListOfProducts(productList);
        cart3.setListOfProducts(productList);
        productsGroup1.setProducts(productList);
        productsGroupDao.save(productsGroup1);
        cartDao.save(cart1);
        Long cartId1 = cart1.getCartId();
        cartDao.save(cart2);
        Long cartId2 = cart2.getCartId();
        cartDao.save(cart3);
        Long cartId3 = cart3.getCartId();
        Long id = productsGroup1.getId();
        product1.getCartList().add(cart1);
        product1.getCartList().add(cart2);
        product2.getCartList().add(cart3);
        cart1.getListOfProducts().add(product1);
        cart2.getListOfProducts().add(product1);
        cart3.getListOfProducts().add(product2);
        //When
        productDao.save(product1);
        long product1Id = product1.getId();
        productDao.save(product2);
        long product2Id = product2.getId();
        //Then
        assertNotEquals(0, product1Id);
        assertNotEquals(0, product2Id);
        //CleanUp
        productDao.deleteById(product1Id);
        productDao.deleteById(product2Id);
        productsGroupDao.deleteById(id);
        cartDao.deleteById(cartId1);
        cartDao.deleteById(cartId2);
        cartDao.deleteById(cartId3);
    }

    @Test
    public void testSaveManyToManyOnOrderList() {
        //Given
        ProductsGroup productsGroup1 = new ProductsGroup("number one");
        productsGroupDao.save(productsGroup1);
        List<Order> orderList = new ArrayList<>();
        List<Cart> cartList = new ArrayList<>();
        User user = new User("Name",1, "123");
        userDao.save(user);
        Order order1 = new Order(user, LocalDateTime.of(2021, 5, 11, 11, 11));
        Order order2 = new Order(user, LocalDateTime.of(2020, 7, 27, 21, 51));
        Order order3 = new Order(user, LocalDateTime.of(2020, 11, 17, 10, 44));
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
           Product product1 = new Product("name1", "description1",
                new BigDecimal("100"), productsGroup1, cartList, orderList);
        Product product2 = new Product("name2", "description2",
                new BigDecimal("200"), productsGroup1, cartList, orderList);
        productDao.save(product1);
        productDao.save(product2);
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        order1.setProductList(productList);
        order2.setProductList(productList);
        order3.setProductList(productList);
        productsGroup1.setProducts(productList);
        productsGroupDao.save(productsGroup1);
        orderDao.save(order1);
        Long orderId1 = order1.getId();
        orderDao.save(order2);
        Long orderId2 = order2.getId();
        orderDao.save(order3);
        Long orderId3 = order3.getId();
        product1.getOrderList().add(order1);
        product1.getOrderList().add(order2);
        product2.getOrderList().add(order3);
        order1.getProductList().add(product1);
        order2.getProductList().add(product1);
        order3.getProductList().add(product2);

        //When
        productDao.save(product1);
        long product1Id = product1.getId();
        productDao.save(product2);
        long product2Id = product2.getId();
        //Then
        assertNotEquals(0, product1Id);
        assertNotEquals(0, product2Id);
        //CleanUp
        Long prodGroupId = productsGroup1.getId();
        Long userId = user.getId();
        orderDao.deleteById(orderId1);
        orderDao.deleteById(orderId2);
        orderDao.deleteById(orderId3);
        productDao.deleteById(product1Id);
        productDao.deleteById(product2Id);
        productsGroupDao.deleteById(prodGroupId);
        userDao.deleteById(userId);
    }
}
