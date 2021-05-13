package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {
    private long id;
    private User user;
    private LocalDateTime orderDate;
    private List<Product> productList;

    public OrderDto(long id, User user, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
    }
}
