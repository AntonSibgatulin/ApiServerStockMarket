package ru.antonsibgatulin.apiserver.data.respond;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.ResponeType;
import ru.antonsibgatulin.apiserver.data.task.Task;
import ru.antonsibgatulin.apiserver.data.user.User;
@Data
@NoArgsConstructor
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
    private Long time;

    private Long task_id;

    public Respond(User user,String message,Integer price,Integer countDay){

        if(countDay>=14){
            countDay = 14;
        }
        if(countDay<=1){
            countDay = 1;
        }

        this.user = user;
        this.message = message;
        this.price = price;
        this.countDay = countDay;

    }



}
