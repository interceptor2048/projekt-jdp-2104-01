package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.UserDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {
    public List<UserDto> mapToUserDtoList(List<User> user){
        return new ArrayList<UserDto>();
    }

    public User mapToUser(UserDto userDto){
        return new User();
    }

    public UserDto mapToUserDto(User user){
        return new UserDto();
    }
}
