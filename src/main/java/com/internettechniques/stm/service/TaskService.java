package com.internettechniques.stm.service;

import com.internettechniques.stm.model.Task;
import com.internettechniques.stm.model.User;
import com.internettechniques.stm.repository.TaskRepo;
import com.internettechniques.stm.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }


    public Task addTaskByUser (Task newTask, int userId){
        Task task = null;
        User user;
        Optional<User> temp = userRepo.findById(userId);
        if(temp.isPresent()){
            user = temp.get();
            newTask.setUser(user);
            task = taskRepo.save(newTask);
        }
        return task;
    }

    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }

    public Task findTaskById(int id){
        Optional<Task> temp = taskRepo.findById(id);
        Task task = null;
        if(temp.isPresent()){
            task = temp.get();
        }
        return task;
    }

    public boolean deleteTaskById(int id){
        boolean control = false;
        if(taskRepo.existsById(id)){
            taskRepo.deleteById(id);
            control = true;
        }
        return control;
    }

    public boolean updateTaskStatus(int id, Task.Status newStatus){
        Optional<Task> temp = taskRepo.findById(id);
        Task task;
        boolean control = false;
        if(temp.isPresent()){
            task = temp.get();
            task.setStatus(newStatus);
            taskRepo.save(task);
            control = true;
        }
        return control;
    }
}
