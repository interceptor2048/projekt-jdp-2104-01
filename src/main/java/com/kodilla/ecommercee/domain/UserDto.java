package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private int status;
    private String userKey;
    private LocalDateTime expirationTime;


    public UserDto(Long id, String username, int status, String userKey) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
