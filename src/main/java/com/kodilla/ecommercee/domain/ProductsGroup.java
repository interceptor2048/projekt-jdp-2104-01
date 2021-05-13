package com.kodilla.ecommercee.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "PRODUCTS_GROUPS")
public class ProductsGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    public ProductsGroup(String name) {
        this.name = name;
    }

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "productsGroup",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Product> products = new ArrayList<>();

    public ProductsGroup(long id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }
}
