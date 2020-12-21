package com.internettechniques.stm.service;


import com.internettechniques.stm.model.User;
import com.internettechniques.stm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public User addUser (User newUser){
        return userRepo.save(newUser);
    }

    public User findUserById(int id){
        Optional<User> temp = userRepo.findById(id);
        User user = null;
        if(temp.isPresent()){
            user = temp.get();
        }
        return user;
    }

    public boolean deleteUserById(int id){
        boolean control = false;
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            control = true;
        }
        return control;
    }

    public boolean updateUserStatus(int id, boolean newStatus){
        Optional<User> temp = userRepo.findById(id);
        User user;
        boolean control = false;
        if(temp.isPresent()){
            user = temp.get();
            user.setStatus(newStatus);
            userRepo.save(user);
            control = true;
        }
        return control;
    }
}
