package ru.antonsibgatulin.apiserver.data.respond;

import jakarta.persistence.*;
import lombok.Data;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.ResponeType;
import ru.antonsibgatulin.apiserver.data.task.Task;
import ru.antonsibgatulin.apiserver.data.user.User;
@Data
@Entity
public class Respond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    @ManyToOne
    private User user;


    private Integer price;
    private Integer countDay;
    private ResponeType type;
    private Long time;


}
