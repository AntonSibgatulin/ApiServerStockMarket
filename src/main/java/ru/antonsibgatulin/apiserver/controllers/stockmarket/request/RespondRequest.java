package ru.antonsibgatulin.apiserver.controllers.stockmarket.request;

import lombok.Data;

@Data
public class RespondRequest {



    private Long id;
    private String message;
    private Integer price;
    private Integer countDay;
}
