package com.jpa.springboot.service;

import java.util.List;
import com.jpa.springboot.entity.User;

public interface UserService {
    //1. create
    User createUser(User user);
    //2. select
    User getUserById(Long userId);
    //3. select all
    List<User> getAllUsers();
    //4. update
    User updateUser(User user);
    //5. delete
    void deleteUser(Long userId);
}
