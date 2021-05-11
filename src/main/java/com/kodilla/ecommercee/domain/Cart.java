package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "CARTS")
public class Cart {
    private Long cartId;
    private Date orderDate;
    private List<Product> listOfProducts  = new ArrayList<>();
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID")
    public Long getCartId() {
        return cartId;
    }
    @NotNull
    @Column(name = "ORDER_DATE")
    public Date getOrderDate() {
        return orderDate;
    }
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    public List<Product> getListOfProducts() {
        return listOfProducts;
    }
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }



    private void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    private void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private void setListOfProducts(List<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}

/*@Data
@Entity(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "CART_ID")
    private Long cartId;

    @NotNull
    @Column(name = "ORDER_DATE")
    private Date orderDate;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();


}*/
