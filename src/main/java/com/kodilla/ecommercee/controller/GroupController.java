package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")

public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "lists")
    public List<GroupDto> getGroup() {
        List<GroupDto> listGroup = new ArrayList<>();
        listGroup.add(new GroupDto(1L, "Test name of group"));
        listGroup.add(new GroupDto(2L, "Another test of group"));
        listGroup.add(new GroupDto(3L, "And one more name test of group"));
        return listGroup;
    }

    @RequestMapping(method = RequestMethod.POST, value = "create-group")
    public void createGroup(@RequestBody GroupDto groupDto) {
        List<GroupDto> listGroup = new ArrayList<>();
        listGroup.add(new GroupDto(4L, "New added group"));
    }

    @RequestMapping(method = RequestMethod.GET, value = "group")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return new GroupDto(1L, "Test name of group");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "update-group")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return new GroupDto(1L, "Name group after update ");
    }
}
