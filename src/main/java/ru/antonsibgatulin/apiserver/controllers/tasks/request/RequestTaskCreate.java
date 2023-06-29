package ru.antonsibgatulin.apiserver.controllers.tasks.request;


import lombok.Data;


@Data
public class RequestTaskCreate {
    private String name;
    private String description;
    private Integer price;
    private Integer betterPrice;


    private String typeTasks="HOMETASK";

    private Long timeCreate=System.currentTimeMillis();
    private Long countLike=0L;
    private Long countView=0L;
    private Integer countRespond = 0;


}
