package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductDbService productDbService;


    @GetMapping(value = "getProducts")
    public List<ProductDto> getProducts() {
        return productMapper.mapToProductDtoList(productDbService.getAllProducts());
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException {
        return productMapper.mapToProductDto(
                productDbService.getProductById(productId).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto createProduct(@RequestBody ProductDto productDto) throws com.kodilla.ecommercee.exception.ProductsGroupNotFoundException {
        Product product = productMapper.mapToProduct(productDto);
        Product createdProduct = productDbService.saveProduct(new Product(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getProductsGroup()
        ));
        return productMapper.mapToProductDto(createdProduct);
    }

    @PutMapping(value = "updateProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) throws Exception {
        Product product = productMapper.mapToProduct(productDto);
        Optional<Product> productFromDb = productDbService.getProductById(productDto.getId());
        if (productFromDb.isPresent()) {
            return productMapper.mapToProductDto(productDbService.saveProduct(product));
        } else throw new ProductNotFoundException();
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId) throws ProductNotFoundException {
        if (productDbService.getProductById(productId).isPresent()) {
            productDbService.deleteProduct(productId);
        } else throw new ProductNotFoundException();
    }
}