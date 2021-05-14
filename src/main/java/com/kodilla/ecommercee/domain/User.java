package com.kodilla.ecommercee.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "USERS")
public class User {

    public User(Long id, @NotNull String username, @NotNull int status, @NotNull String userKey, LocalDateTime expirationTime) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }
    public User(@NotNull String username, @NotNull int status, @NotNull String userKey, LocalDateTime expirationTime) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }

    public User(Long id, String username, int status, String userKey, LocalDateTime expirationTime, List<Order> listOfOrders) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
        this.listOfOrders = listOfOrders;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
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

    @Column(name = "EXPIRATION_TIME")
    private LocalDateTime expirationTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.LAZY
    )
    private List<Order> listOfOrders = new ArrayList<>();
}

