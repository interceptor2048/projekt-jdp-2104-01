package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotAuthenticatedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class UserAuthenticator {

    public boolean isAuthenticated(User user, String userKeyToCheck) throws UserNotAuthenticatedException {
        if (user.getUserKey().equals(userKeyToCheck)
                && user.getExpirationTime().isAfter(LocalDateTime.now())
                && user.getStatus() == 1){
            return true;
        } else {
            throw new UserNotAuthenticatedException();
        }
    }

}
