package com.internettechniques.stm.controller;

import com.internettechniques.stm.model.Task;
import com.internettechniques.stm.model.User;
import com.internettechniques.stm.service.TaskService;
import com.internettechniques.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public MainController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


    // USER REQUESTS //

    @GetMapping("/user/getAllUsers")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user/createUser")
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
    public User findUserById(
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
    public boolean deleteUserById(
            @RequestParam("userId") int userId
    ){
        return userService.deleteUserById(userId);
    }

    // TASK REQUESTS //

    @GetMapping("/task/getAllTasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @PostMapping("/task/createTask")
    public Task createTaskByUser(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Task.Type type,
            @RequestParam("status") Task.Status status,
            @RequestParam("userId") int userId
    ){
        Task task = new Task(title,description,type,status);
        return taskService.addTaskByUser(task, userId);
    }

    @GetMapping("/task/findById")
    public Task findTaskById(
            @RequestParam("taskId") int taskId
    ){
        return taskService.findTaskById(taskId);
    }

    @PutMapping("/task/updateStatus")
    public boolean updateTaskStatus(
            @RequestParam("taskId") int taskId,
            @RequestParam("newStatus") Task.Status newStatus
    ){
        return taskService.updateTaskStatus(taskId, newStatus);
    }

    @DeleteMapping("/task/deleteTask")
    public boolean deleteTaskById(
            @RequestParam("taskId") int taskId
    ){
        return taskService.deleteTaskById(taskId);
    }

}
