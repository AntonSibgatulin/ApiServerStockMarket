package ru.antonsibgatulin.apiserver.controllers.tasks.request;


import lombok.Data;

@Data
public class RequestRespone {

    private String message;
    private Integer price;
    private Integer countDay;
    private ResponeType type;


}
