package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class UserAuthenticator {

    public boolean isAuthenticated(User user, String userKeyToCheck) {
        return user.getUserKey().equals(userKeyToCheck) && user.getExpirationTime().isAfter(LocalDateTime.now())
                && user.getStatus() == 1;
    }

}
