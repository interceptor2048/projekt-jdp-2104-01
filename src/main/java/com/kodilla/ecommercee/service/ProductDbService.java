package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductDao;
import com.kodilla.ecommercee.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductDbService {

    private final ProductDao productDao;

    public List<Product> getAllProducts(){
        return  productDao.findAll();
    }

    public Optional<Product> getProductById(Long productId){
        return productDao.findById(productId);
    }

    public Product saveProduct(Product product) {
        return productDao.save(product);
    }

    public void deleteProduct(Long productId){
        productDao.deleteById(productId);
    }
}
