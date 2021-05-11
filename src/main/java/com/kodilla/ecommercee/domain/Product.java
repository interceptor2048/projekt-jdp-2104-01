package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "PRODUCTS")
public class Product {
    public Product(@NotNull String name, @NotNull String description, @NotNull BigDecimal price, @NotNull ProductsGroup productsGroup, List<Cart> cartList, List<Order> orderList) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productsGroup = productsGroup;
        this.cartList = cartList;
        this.orderList = orderList;
    }

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
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name= "GROUP_ID")
    @NotNull
    private ProductsGroup productsGroup;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="JOIN_CART_PROD",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID",
                    referencedColumnName ="ID")},
            inverseJoinColumns = {@JoinColumn(name = "CART_ID",
                    referencedColumnName = "CART_ID")}
    )
    private List<Cart> cartList = new ArrayList<>();


    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();

}
