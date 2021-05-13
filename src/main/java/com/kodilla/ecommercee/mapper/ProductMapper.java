package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductsGroup;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Product> mapToProductList(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

}
