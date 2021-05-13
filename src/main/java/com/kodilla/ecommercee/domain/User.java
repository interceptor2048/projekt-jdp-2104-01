package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity(name = "USERS")
public class User {

    public User(@NotNull String username, @NotNull int status, @NotNull String userKey, LocalDateTime expirationTime) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }


    public User(Long id, @NotNull String username, @NotNull int status, @NotNull String userKey, LocalDateTime expirationTime) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }
    public User() {}

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

    @Column(name = "EXPIRATION_TIME")
    private LocalDateTime expirationTime;

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Order> listOfOrders = new ArrayList<>();

    }
