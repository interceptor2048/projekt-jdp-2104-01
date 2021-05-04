package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "PRODUCT_ID", unique = true)
    private Long productId;
    @NotNull
    @Column(name= "NAME")
    private String name;
    @NotNull
    @Column(name= "DESCRIPTION")
    private String description;
    @NotNull
    @Column(name= "PRICE")
    private Long price;
//    @ManyToOne
//    @JoinColumn(name= "GROUP_ID")
    @NotNull
    @Column(name = "GROUP_ID")
    private String groupId;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="JOIN_PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID",
                    referencedColumnName ="PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID",
                    referencedColumnName = "CART_ID")}
    )
    private List<Cart> cartList = new ArrayList<>();

//@ManyToMany(cascade = CascadeType.ALL, mappedBy = "productList")
//    private List<Order> orderList = new ArrayList<>();
}
