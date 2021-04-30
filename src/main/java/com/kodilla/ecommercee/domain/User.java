package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @Column(name = "status")
    private int status;

    @NotNull
    @Column(name = "cart_id", unique = true)
    private Long cartId;

    @NotNull
    @Column(name = "user_key", unique = true)
    private String userKey;

    @NotNull
    @Column(name = "expiration_time")
    private Date expirationTime;
}
