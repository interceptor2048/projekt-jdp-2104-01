package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
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

    public User() {}
    private Long id;
    private String username;
    private int status;
    private String userKey;
    private LocalDateTime expirationTime;
    private List<Order> listOfOrders = new ArrayList<>();
    private Cart cart;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    @NotNull
    @Column(name = "USERNAME", unique = true)
    public String getUsername() {
        return username;
    }

    @NotNull
    @Column(name = "STATUS")
    public int getStatus() {
        return status;
    }

    @NotNull
    @Column(name = "USER_KEY", unique = true)
    public String getUserKey() {
        return userKey;
    }

    @Column(name = "EXPIRATION_TIME")
    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @OneToMany(
            targetEntity = Order.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setStatus(int status) {
        this.status = status;
    }

    private void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    private void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    private void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }
}
/*@Data
@Entity(name = "USERS")
public class User {
    public User(Long id, @NotNull String username, @NotNull int status, @NotNull String userKey, LocalDateTime expirationTime) {
        this.id = id;
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

    public User(String username, int status, String userKey, LocalDateTime expirationTime) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }
}*/
