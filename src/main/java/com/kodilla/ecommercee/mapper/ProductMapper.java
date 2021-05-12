package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductMapper {

    public ProductDto mapToProductDto(Product product){

        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductsGroup().getId());
    }

    public Product mapToProduct(ProductDto productDto){

        return new Product();
    }

}
