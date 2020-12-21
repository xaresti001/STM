package com.internettechniques.stm.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENTATION
    private int taskId;
    private String title;
    private String description;
    private LocalDateTime dateAdded = LocalDateTime.now();
    public enum Type{
        TASK,
        BUG,
        FEATURE
    }
    public enum Status{
        NEW,
        IN_PROGRESS,
        DONE
    }
    private Type type;
    private Status status;

    @ManyToOne
    @JoinTable( // association (relation) table
            name = "user_to_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private User user;

    public Task(String title, String description, Type type, Status status) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.status = status;
    }
}
