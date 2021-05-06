package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;
    @NotNull
    @Column(name= "NAME")
    private String name;
    @NotNull
    @Column(name= "DESCRIPTION")
    private String description;
    @NotNull
    @Column(name= "PRICE")
    private Double price;
    //    @NotNull
//    @Column(name = "GROUP_ID")
    @ManyToOne
    @JoinColumn(name= "GROUP_ID")
    @NotNull
    private Group group;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="JOIN_PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID",
                    referencedColumnName ="ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID",
                    referencedColumnName = "CART_ID")}
    )
    private List<Cart> cartList = new ArrayList<>();

//@ManyToMany(cascade = CascadeType.ALL, mappedBy = "productList")
//    private List<Order> orderList = new ArrayList<>();


    public Product(@NotNull String name, @NotNull String description, @NotNull Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
