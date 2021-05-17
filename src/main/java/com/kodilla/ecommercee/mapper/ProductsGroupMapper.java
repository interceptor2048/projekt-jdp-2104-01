package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.ProductsGroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsGroupMapper {

    public ProductsGroup mapToGroup(final ProductsGroupDto productsGroupDto) {
        return new ProductsGroup(
                productsGroupDto.getId(),
                productsGroupDto.getName()
        );
    }

    public ProductsGroupDto mapToGroupDto(final ProductsGroup productsGroup) {
        return new ProductsGroupDto(
                productsGroup.getId(),
                productsGroup.getName()
        );
    }

    public List<ProductsGroupDto> mapToGroupDtoList(final List<ProductsGroup> productsGroupList) {
        return productsGroupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}