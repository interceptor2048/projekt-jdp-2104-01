package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.ProductsGroupNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductMapper {

    private final ProductsGroupDao productsGroupDao;

    public Product mapToProduct(final ProductDto productDto) throws ProductsGroupNotFoundException {

            Optional<ProductsGroup> productsGroupOptional = productsGroupDao.findById(Long.parseLong(productDto.getGroupId()));

            if (productsGroupOptional.isPresent()) {
                ProductsGroup productsGroup = productsGroupOptional.get();

                return new Product(
                        productDto.getId(),
                        productDto.getName(),
                        productDto.getDescription(),
                        productDto.getPrice(),
                        productsGroup
                );
            } else throw new ProductsGroupNotFoundException();
    }

    public ProductDto mapToProductDto(final Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                String.valueOf(product.getProductsGroup().getId())
        );
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productsList) {
        return productsList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }
}
