package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.ProductsGroupNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDbService {
    private final OrderDao orderDao;
    private final UserDao userDao;
    private final OrderMapper orderMapper;
    private final ProductMapper productMapper;

    public List<Order> findAllOrders(){
        return orderDao.findAll();
    }

    public Optional<Order> findOrderById(Long id){
        return orderDao.findById(id);
    }

    public Order saveOrder(Order order){
        return orderDao.save(order);
    }

    public void deleteOrderById(Long id){
        orderDao.deleteById(id);
    }

    public void createOrder(Order order, Long userId, List<ProductDto> productDtoList) throws UserNotFoundException, ProductsGroupNotFoundException {
        User user = userDao.findById(userId).orElseThrow(UserNotFoundException::new);
        order.setUser(user);
        List<Product> productList = productMapper.mapToProductList(productDtoList);
        order.setProductList(productList);
        user.getListOfOrders().add(order);
        orderDao.save(order);
        userDao.save(user);
    }

    public OrderDto updateOrder(Order order, List<ProductDto> productDtoList) throws ProductsGroupNotFoundException{
        List<Product> productList = productMapper.mapToProductList(productDtoList);
        order.setProductList(productList);
        orderDao.save(order);
        return orderMapper.mapToOrderDto(order);
    }
}
