package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.ProductsGroupDto;
import com.kodilla.ecommercee.domain.ProductsGroupNotFoundException;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.mapper.ProductsGroupMapper;
import com.kodilla.ecommercee.service.ProductsGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class ProductsGroupController {


    private final ProductsGroupService service;
    private final ProductsGroupMapper productsGroupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<ProductsGroupDto> getGroups() {
        List<ProductsGroup> groups = service.findAll();
        return productsGroupMapper.mapToGroupDtoList(groups);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public ProductsGroupDto getGroup(@RequestParam Long groupId) throws ProductsGroupNotFoundException {
        return productsGroupMapper.mapToGroupDto(
                service.findById(groupId).orElseThrow(ProductsGroupNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody ProductsGroupDto productsGroupDto) {
        ProductsGroup group = productsGroupMapper.mapToGroup(productsGroupDto);
        service.save(group);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public ProductsGroupDto updateGroup(@RequestBody ProductsGroupDto productsGroupDto) {
        ProductsGroup group = productsGroupMapper.mapToGroup(productsGroupDto);
        ProductsGroup savedGroup = service.save(group);
        return productsGroupMapper.mapToGroupDto(savedGroup);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(Long groupId) {
        service.deleteById(groupId);
    }
}
