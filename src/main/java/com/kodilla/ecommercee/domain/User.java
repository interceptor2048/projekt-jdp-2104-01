package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Data
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Column(name = "STATUS")
    private int status;

    @NotNull
    @Column(name = "USER_KEY", unique = true)
    private String userKey;

    @NotNull
    @Column(name = "EXPIRATION_TIME")
    private LocalTime expirationTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> listOfOrders;
}
