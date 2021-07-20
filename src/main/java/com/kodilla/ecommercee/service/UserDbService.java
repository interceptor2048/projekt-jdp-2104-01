package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dao.ProductsGroupDao;
import com.kodilla.ecommercee.dao.UserDao;
import com.kodilla.ecommercee.domain.ProductsGroup;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserDao userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }
    public User createUser(final User user) {
        return userRepository.save(user);
    }


    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void disableUser(Long userId) {
        Optional<User> userToDisable = getUser(userId);
        User user = userToDisable.orElseThrow(UserNotFoundException::new);
        user.setStatus(0);
        User savedUser = saveUser(user);
    }
    public void generateKey(Long userId) {
        Optional<User> userToChangeKey = getUser(userId);
        User user = userToChangeKey.orElseThrow(UserNotFoundException::new);

        int key = (int)(Math.random()*20000);
        user.setUserKey(String.valueOf(key));
        user.setExpirationTime(LocalDateTime.now().plusHours(1L));
        User savedUser = saveUser(user);
    }

}