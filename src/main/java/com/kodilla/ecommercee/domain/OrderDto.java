package com.kodilla.ecommercee.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private long id;
    private LocalDateTime orderDate;
}
