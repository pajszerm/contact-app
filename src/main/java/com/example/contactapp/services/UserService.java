package com.example.contactapp.services;

import com.example.contactapp.domain.UserInfo;
import com.example.contactapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkUserNameExists(String username) {
        UserInfo userToFind = userRepository.findUserInfoByUsername(username);
        return userToFind != null;
    }

    public boolean checkEmailExists(String email) {
        UserInfo userToFind = userRepository.findUserByEmail(email);
        return userToFind != null;
    }
}
