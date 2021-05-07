package com.kodilla.ecommercee.dao;


import com.kodilla.ecommercee.domain.ProductsGroup;
import org.springframework.data.repository.CrudRepository;

public interface ProductsGroupDao extends CrudRepository<ProductsGroup,Long> {
}
