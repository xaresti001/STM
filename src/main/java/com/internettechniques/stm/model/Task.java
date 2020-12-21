package com.internettechniques.stm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
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


}
