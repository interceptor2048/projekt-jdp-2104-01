package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.domain.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.mapper.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {


    private final ProductsGroupDao service;
    private final GroupMapper groupMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups() {
        List<ProductsGroup> groups = (List<ProductsGroup>) service.findAll();
        return groupMapper.mapToGroupDtoList(groups);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(
                service.findById(groupId).orElseThrow(GroupNotFoundException::new)
        );
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        ProductsGroup group = groupMapper.mapToGroup(groupDto);
        service.save(group);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        ProductsGroup group = groupMapper.mapToGroup(groupDto);
        ProductsGroup savedGroup = service.save(group);
        return groupMapper.mapToGroupDto(savedGroup);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(Long groupId) {
        service.deleteById(groupId);
    }
}
