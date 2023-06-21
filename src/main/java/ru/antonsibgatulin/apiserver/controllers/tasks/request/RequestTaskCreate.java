package ru.antonsibgatulin.apiserver.controllers.tasks.request;


import lombok.Data;


@Data
public class RequestTaskCreate {
    private String name;
    private String description;
    private Integer price;
    private Integer betterPrice;


    private String typeTasks;

    private Long timeCreate;
    private Long countLike;
    private Long countView;
}
