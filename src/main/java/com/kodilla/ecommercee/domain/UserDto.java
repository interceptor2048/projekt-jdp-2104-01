package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private int status;
    private Long cartId;
    private String userKey;
    private Date expirationTime;

    public UserDto(){}
}
