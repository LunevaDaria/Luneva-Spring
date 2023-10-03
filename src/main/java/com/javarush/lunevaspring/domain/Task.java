package com.javarush.lunevaspring.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="task")
public class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    public Task() {
    }
    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
    }
}
