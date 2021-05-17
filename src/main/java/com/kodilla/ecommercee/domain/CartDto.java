package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDto {
    private Long cartId;
    private User user;

    public CartDto(Long cartId, User user) {
        this.cartId = cartId;
        this.user = user;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
