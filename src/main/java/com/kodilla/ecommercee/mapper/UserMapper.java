package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
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
                //userDto.getId(),
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey());
               // userDto.getExpirationTime(),
               // userDto.getListOfOrders());
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey());
             //   user.getExpirationTime(),
             //   user.getListOfOrders());
    }
}