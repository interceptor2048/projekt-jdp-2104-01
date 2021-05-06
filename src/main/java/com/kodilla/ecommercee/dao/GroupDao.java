package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductsGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDao extends CrudRepository<ProductsGroup, Long> {
}
