package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMapper {

    public Order mapToOrder(OrderDto orderDto){
        return new Order(
                orderDto.getOrderDate()
        );
    }

    public OrderDto mapToOrderDto(Order order){
        return new OrderDto(
                order.getId(),
                order.getOrderDate()
        );
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders){
        List<OrderDto> returnList = orders.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());
        return returnList;
    }
}
