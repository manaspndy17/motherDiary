package com.motherdiary.motherdiary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.model.items;
import com.motherdiary.motherdiary.repository.ItemRepo;
import com.motherdiary.motherdiary.repository.UserRepo;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private ItemRepo itemRepo;

    public boolean validateUser(String username, String password) {
        UserIdentity user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    
    public UserIdentity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserIdentity findById(int id) {
    return userRepository.findById(id).orElse(null);
}

    public List<items> findByUser(UserIdentity user){
        return itemRepo.findByUser(user);
    }

    public void saveUser(UserIdentity user) {
        userRepository.save(user);
    }
}

