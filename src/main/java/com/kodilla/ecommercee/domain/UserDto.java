package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private int status;
    private String userKey;
    private LocalDateTime expirationTime;
    private List<Order> listOfOrders;

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public UserDto(Long id, String username, int status, String userKey, LocalDateTime expirationTime) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.expirationTime = expirationTime;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserDto(){}
}
