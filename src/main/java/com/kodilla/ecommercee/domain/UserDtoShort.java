package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserDtoShort {
    private Long id;
    private String username;
    private int status;


    public UserDtoShort(Long id, String username, int status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

}
