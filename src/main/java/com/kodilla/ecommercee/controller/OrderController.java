package com.kodilla.ecommercee.controller;


import com.kodilla.ecommercee.dao.OrderDao;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDao service;
    private final OrderMapper mapper;

    @RequestMapping(method = RequestMethod.GET,value = "getOrders")
    public List<OrderDto> getOrders(){
        List<Order> foundList = service.findAll();
        List<OrderDto> returnList = mapper.mapToOrderDtoList(foundList);
        return returnList;
    }

    @RequestMapping(method = RequestMethod.POST,value = "createOrder")
    public void createOrder(@RequestBody OrderDto orderDto){
        return;
    }

    @RequestMapping(method = RequestMethod.GET,value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId){
        return new OrderDto(1,new User(), LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(2,new User(),LocalDateTime.now());
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId){
        return;
    }

}
