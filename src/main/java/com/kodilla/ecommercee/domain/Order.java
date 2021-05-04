package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long orderId;

    @NotNull
    @Column(name = "USER_ID")
    private Long userId;

    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;
}
