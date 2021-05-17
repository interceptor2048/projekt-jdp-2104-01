package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductDao productDao;

    public Product findById(Long productId) throws ProductNotFoundException {
        return productDao.findById(productId).orElseThrow(ProductNotFoundException::new);
    }

    public Product save(Product product){
        return productDao.save(product);
    }


}
