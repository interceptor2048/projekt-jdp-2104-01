package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID", unique = true)
    private Long cartId;


    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(mappedBy = "cartList", fetch = FetchType.LAZY)
    private List<Product> listOfProducts  = new ArrayList<>();

}
