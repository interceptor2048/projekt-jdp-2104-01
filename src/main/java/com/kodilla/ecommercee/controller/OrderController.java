package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/orders")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET,value = "getOrders")
    public List<OrderDto> getOrders(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST,value = "addOrder")
    public void createOrder(@RequestBody OrderDto orderDto){
        return;
    }

    @RequestMapping(method = RequestMethod.GET,value = "getOrder")
    public OrderDto getOrder(@RequestParam Long orderId){
        return new OrderDto(1,1,1);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto){
        return new OrderDto(2,2,2);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteOrder")
    public void deleteOrder(@RequestParam Long orderId){
        return;
    }
}
