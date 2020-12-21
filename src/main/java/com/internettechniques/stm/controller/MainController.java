package com.internettechniques.stm.controller;

import com.internettechniques.stm.service.TaskService;
import com.internettechniques.stm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public MainController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }


}
