package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupMapper {

    public ProductsGroup mapToGroup(final GroupDto groupDto) {
        return new ProductsGroup(
                groupDto.getId(),
                groupDto.getName()
        );
    }

    public GroupDto mapToGroupDto(final ProductsGroup productsGroup) {
        return new GroupDto(
                productsGroup.getId(),
                productsGroup.getName()
        );
    }

    public List<GroupDto> mapToGroupDtoList(final List<ProductsGroup> productsGroupList) {
        return productsGroupList.stream()
                .map(this::mapToGroupDto)
                .collect(Collectors.toList());
    }
}
