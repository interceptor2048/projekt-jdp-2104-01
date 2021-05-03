package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    private List<GroupDto> listGroupDto;

    @Autowired
    public GroupController() {
        this.listGroupDto = new ArrayList<>();
        GroupDto group1 = new GroupDto(1L, "Ubrania");
        GroupDto group2 = new GroupDto(2L, "Dodatki");
        GroupDto group3 = new GroupDto(3L, "Bizuteria");
        GroupDto group4 = new GroupDto(4L, "Obuwie");
        listGroupDto.add(group1);
        listGroupDto.add(group2);
        listGroupDto.add(group3);
        listGroupDto.add(group4);

    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public List<GroupDto> getGroup() {
        return listGroupDto;
    }

    @RequestMapping(method = RequestMethod.GET, value = "group")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return listGroupDto.get(Math.toIntExact(groupId));
    }


    @RequestMapping(method = RequestMethod.POST, value = "create-group")
    public void createGroup(@RequestBody GroupDto groupDto) {
        listGroupDto.add(groupDto);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "update-group")
    public boolean updateGroup(@RequestBody GroupDto groupDto) {
        return listGroupDto.add(groupDto);
    }
}
