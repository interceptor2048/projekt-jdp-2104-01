package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.ProductsGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends CrudRepository<ProductsGroup, Long> {

    @Override
    List<ProductsGroup> findAll();

    @Override
    ProductsGroup save(ProductsGroup productsGroup);

    @Override
    Optional<ProductsGroup> findById(Long groupId);
}
