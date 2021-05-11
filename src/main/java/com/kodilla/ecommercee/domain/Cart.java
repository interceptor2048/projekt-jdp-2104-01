package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "CARTS")
public class Cart {
    public Cart(@NotNull Date orderDate) {
        this.orderDate = orderDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;

    @NotNull
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();
}
