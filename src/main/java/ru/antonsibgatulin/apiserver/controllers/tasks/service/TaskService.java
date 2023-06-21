package ru.antonsibgatulin.apiserver.controllers.tasks.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestRespone;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestTaskCreate;
import ru.antonsibgatulin.apiserver.data.respond.mapper.ResponeMapper;
import ru.antonsibgatulin.apiserver.data.respond.repository.RespondRepository;
import ru.antonsibgatulin.apiserver.data.task.mapper.TaskMapper;
import ru.antonsibgatulin.apiserver.data.task.repository.TaskRepository;
import ru.antonsibgatulin.apiserver.services.HelpService;
import ru.antonsibgatulin.apiserver.utils.ClassUtils;

import javax.swing.text.html.parser.Entity;

@Service
public record TaskService(TaskMapper taskMapper, TaskRepository taskRepository, RespondRepository respondRepository,
                          ResponeMapper responeMapper) {

    public ResponseEntity create(RequestTaskCreate requestTaskCreate) {
        var task = taskMapper.fromRequestTaskCreateoTask(requestTaskCreate);
        var user = ClassUtils.getUser();
        task.setUser(user);
        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }

    public ResponseEntity delete(Long id) {
        var user = ClassUtils.getUser();
        var task = taskRepository.getTaskByUserAndId(user,id);
        if (task !=null){
            task.clear(respondRepository);
            taskRepository.delete(task);
        }
        return HelpService.handleOk("OK", HttpStatus.OK);
    }

    public ResponseEntity edit(Long id, RequestTaskCreate requestTaskCreate) {
        var user = ClassUtils.getUser();
        var task = taskRepository.getTaskByUserAndId(user,id);

        if(task == null)return HelpService.handleError("Not found",HttpStatus.NOT_FOUND);

        taskMapper.update(task,requestTaskCreate);
        taskRepository.save(task);

        task.prepared();

        return ResponseEntity.ok(task);
    }

    public ResponseEntity respone(Long id, RequestRespone requestRespone) {
        var user = ClassUtils.getUser();

        var task = taskRepository.getTaskById(id);
        if(task == null){
            return HelpService.handleError("Not found",HttpStatus.NOT_FOUND);
        }

        if(task.getUser().getId() == user.getId()){
            return HelpService.handleError("Forbidden",HttpStatus.FORBIDDEN);
        }
        if(task.checkResponse(user)){
            return HelpService.handleOk("OK",HttpStatus.OK);
        }

        var respond = responeMapper.fromRequestResponeToRespond(requestRespone);
        respond.setUser(user);
        respondRepository.save(respond);

        return ResponseEntity.ok(respond);
    }

    public ResponseEntity get(Long id) {
        var task = taskRepository.getTaskById(id);
        if(task == null){
            return HelpService.handleError("Not found",HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }
}
