package com.internettechniques.stm.controller;

import com.internettechniques.stm.model.User;
import com.internettechniques.stm.service.TaskService;
import com.internettechniques.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public MainController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


    @GetMapping("/user/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user/register")
    public User createNewUser(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("status") boolean status
    ){
        User newUser = new User(name, lastName, email, password, status);
        return userService.addUser(newUser);
    }

    @GetMapping("/user/findById")
    public User findById(
            @RequestParam("userId") int userId
    ){
        return userService.findUserById(userId);
    }

    @PutMapping("/user/updateStatus")
    public boolean updateUserStatus(
            @RequestParam("userId") int userId,
            @RequestParam("newStatus") boolean newStatus
    ){
        return userService.updateUserStatus(userId,newStatus);
    }

    @DeleteMapping("/user/deleteUser")
    public boolean deleteUser(
            @RequestParam("userId") int userId
    ){
        return userService.deleteUserById(userId);
    }
}
