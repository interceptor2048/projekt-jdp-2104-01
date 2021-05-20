package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.controller.exception.OrderNotFoundException;
import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.ProductsGroupNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService service;
    private final OrderMapper mapper;

    @RequestMapping(method = RequestMethod.GET,value = "getOrders")
    public List<OrderDto> getOrders(){
        List<Order> foundList = service.findAllOrders();
        List<OrderDto> returnList = mapper.mapToOrderDtoList(foundList);
        return returnList;
    }

    @RequestMapping(method = RequestMethod.POST,value = "createOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOrder(@RequestParam Long userId, @RequestBody List<ProductDto> productList) throws UserNotFoundException, ProductsGroupNotFoundException {
        Order order = new Order(LocalDateTime.now());

        service.createOrder(order, userId, productList);
    }

    @RequestMapping(method = RequestMethod.GET,value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId) throws OrderNotFoundException {
        return mapper.mapToOrderDto(service.findOrderById(orderId)
                .orElseThrow(OrderNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateOrder")
    public OrderDto updateOrder(@RequestParam Long orderId, @RequestBody List<ProductDto> productList) throws OrderNotFoundException, ProductsGroupNotFoundException{
        Order order = service.findOrderById(orderId).orElseThrow(OrderNotFoundException::new);
        return service.updateOrder(order,productList);
    }


    @RequestMapping(method = RequestMethod.DELETE,value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId){
        service.deleteOrderById(orderId);
    }

}
