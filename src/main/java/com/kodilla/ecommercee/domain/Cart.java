package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "CARTS")
public class Cart {
    public Cart(Long cartId, Order order) {
        this.cartId = cartId;
        this.order = order;
    }

    public Cart(Order order) {
        this.order = order;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();

    public Cart() {

    }
}
