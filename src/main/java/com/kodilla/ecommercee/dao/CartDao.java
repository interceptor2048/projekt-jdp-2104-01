package com.kodilla.ecommercee.dao;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CartDao extends CrudRepository<Cart, Long> {

    @Override
    Cart save(Cart cart);
    
    List<Cart> findAll();
}

