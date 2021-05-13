package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ProductDao extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();

    @Override
    Optional<Product> findById(Long productId);

    @Override
    Product save(Product product);

    List<Product> findByNameAndPrice(String name, BigDecimal price);

    @Override
    void deleteById(Long productId);

}
