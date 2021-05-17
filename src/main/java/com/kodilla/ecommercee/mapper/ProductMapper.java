package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.service.ProductDbService;
import com.kodilla.ecommercee.service.ProductsGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductMapper {

    private final ProductsGroupService productsGroupService;

    public ProductDto mapToProductDto(Product product){
        return new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductsGroup().getId());
    }

    public Product mapToProduct(ProductDto productDto) {
        return new Product();
    }

    public List<Product> mapToProductList(List<ProductDto> productDtos) throws GroupNotFoundException {
        return productDtos.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

}
