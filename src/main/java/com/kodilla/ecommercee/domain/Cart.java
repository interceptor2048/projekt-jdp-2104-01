package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cartId;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();

    public Cart(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public Cart(User user) {
        this.user = user;
    }
}
