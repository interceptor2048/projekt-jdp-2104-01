package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import com.kodilla.ecommercee.domain.UserDtoShort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public List<UserDto> mapToUserDtoList(List<User> user){
        return user.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey());
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey());
    }
    public UserDtoShort mapToUserDtoShort(User user){
        return new UserDtoShort(
                user.getId(),
                user.getUsername(),
                user.getStatus());
    }
    public List<UserDtoShort> mapToUserDtoShortList(List<User> user){
        return user.stream()
                .map(this::mapToUserDtoShort)
                .collect(Collectors.toList());
    }
}