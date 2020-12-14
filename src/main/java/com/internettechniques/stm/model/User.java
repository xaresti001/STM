package com.internettechniques.stm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENTATION
    private int userid;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private boolean status;
    private LocalDateTime registrationDateTime = LocalDateTime.now();

    @OneToMany
    @JoinTable( // association (relation) table
            name = "user_to_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks = new HashSet<>();

}
