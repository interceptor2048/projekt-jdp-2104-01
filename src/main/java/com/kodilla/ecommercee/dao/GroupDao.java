package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.ProductsGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupDao extends CrudRepository<ProductsGroup, Long> {

    @Override
    List<ProductsGroup> findAll();

    @Override
    ProductsGroup save(ProductsGroup group);

    @Override
    Optional<ProductsGroup> findById(Long groupId);

    @Override
    void deleteById(Long groupId);
}
