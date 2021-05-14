package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "JOIN_PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> productList;

    public Order(User user, List<Product> productList) {
        this.user = user;
        this.productList = productList;
    }
}
