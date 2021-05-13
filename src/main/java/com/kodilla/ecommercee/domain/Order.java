package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;


    @NotNull
    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CART_ID", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> productList;

    public Order(@NotNull User user, @NotNull Cart cart) {
        this.user = user;
        this.cart = cart;
    }

    public Order(@NotNull LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
