package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cartId;

    @NotNull
    @Column(name = "order_date")
    private Date orderDate;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;
}
