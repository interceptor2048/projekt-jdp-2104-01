package com.kodilla.ecommercee.service;


import com.kodilla.ecommercee.UserRepository;

import com.kodilla.ecommercee.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {

    private final UserRepository userRepository;

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

    public void delete(long id){
        userRepository.deleteById(id);
    }

}
