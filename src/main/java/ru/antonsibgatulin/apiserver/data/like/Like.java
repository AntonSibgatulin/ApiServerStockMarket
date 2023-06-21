package ru.antonsibgatulin.apiserver.data.like;

import jakarta.persistence.*;
import lombok.Data;
import ru.antonsibgatulin.apiserver.data.task.Task;

@Data
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Task task;

    private Long time;


}
