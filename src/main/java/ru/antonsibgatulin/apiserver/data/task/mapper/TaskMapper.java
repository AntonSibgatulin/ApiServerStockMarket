package ru.antonsibgatulin.apiserver.data.task.mapper;

import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestTaskCreate;
import ru.antonsibgatulin.apiserver.data.task.Task;

public interface TaskMapper {
    Task fromRequestTaskCreateoTask(RequestTaskCreate requestTaskCreate);


    void update(Task task, RequestTaskCreate requestTaskCreate);
}
