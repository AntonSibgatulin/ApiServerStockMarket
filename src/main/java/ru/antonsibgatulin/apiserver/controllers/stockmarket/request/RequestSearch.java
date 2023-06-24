package ru.antonsibgatulin.apiserver.controllers.stockmarket.request;


import lombok.Data;
import ru.antonsibgatulin.apiserver.data.task.TypeTask;

import java.util.List;

@Data
public class RequestSearch {
    private String text;
    private Integer max;
    private Integer min;
    private List<TypeTask> types;
    private Integer countRespond;
}
