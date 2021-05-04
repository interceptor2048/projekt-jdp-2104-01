package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
