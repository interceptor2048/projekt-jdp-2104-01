package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;

    //@NotNull
   // @Column(name = "ORDER_DATE")
    //private Date orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    //@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    //private Order order;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }
}
