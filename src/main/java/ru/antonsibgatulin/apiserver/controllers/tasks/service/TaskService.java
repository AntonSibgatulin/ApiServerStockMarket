package ru.antonsibgatulin.apiserver.controllers.tasks.service;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.antonsibgatulin.apiserver.controllers.stockmarket.request.RespondRequest;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestRespone;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestTaskCreate;
import ru.antonsibgatulin.apiserver.data.dilog.Dilog;
import ru.antonsibgatulin.apiserver.data.dilog.DilogRepository;
import ru.antonsibgatulin.apiserver.data.notification.Notification;
import ru.antonsibgatulin.apiserver.data.notification.repository.NotificationRepository;
import ru.antonsibgatulin.apiserver.data.respond.Respond;
import ru.antonsibgatulin.apiserver.data.respond.mapper.ResponeMapper;
import ru.antonsibgatulin.apiserver.data.respond.repository.RespondRepository;
import ru.antonsibgatulin.apiserver.data.task.ActionTask;
import ru.antonsibgatulin.apiserver.data.task.Task;
import ru.antonsibgatulin.apiserver.data.task.TaskType;
import ru.antonsibgatulin.apiserver.data.task.mapper.TaskMapper;
import ru.antonsibgatulin.apiserver.data.task.repository.ActionTaskRepository;
import ru.antonsibgatulin.apiserver.data.task.repository.TaskRepository;
import ru.antonsibgatulin.apiserver.services.HelpService;
import ru.antonsibgatulin.apiserver.utils.ClassUtils;

import javax.swing.text.html.parser.Entity;

@Service
public record TaskService(TaskMapper taskMapper, TaskRepository taskRepository, RespondRepository respondRepository,
                          ResponeMapper responeMapper, ActionTaskRepository actionTaskRepository,
                          NotificationRepository notificationRepository, DilogRepository dilogRepository) {

    private static final String text_create_purchases = "Task was created!";

    private static final String text_user_respond_me = "Somebody respond you on your task";

    private static final String text_user_responded_you = "You was responded on task";

    public ResponseEntity create(RequestTaskCreate requestTaskCreate) {
        var task = taskMapper.fromRequestTaskCreateoTask(requestTaskCreate);
        var user = ClassUtils.getUser();
        task.setUser(user);
        taskRepository.save(task);
        var actionTask = new ActionTask(task,user);

        actionTaskRepository.save(actionTask);


        var notification = new Notification(user, actionTask.getTaskType(), text_create_purchases);
        notificationRepository.save(notification);


        return ResponseEntity.ok(task);
    }

    public ResponseEntity delete(Long id) {
        var user = ClassUtils.getUser();
        var task = taskRepository.getTaskByUserAndId(user, id);
        if (task != null) {
            task.clear(respondRepository);
            taskRepository.delete(task);
        }
        return HelpService.handleOk("OK", HttpStatus.OK);
    }

    public ResponseEntity edit(Long id, RequestTaskCreate requestTaskCreate) {
        var user = ClassUtils.getUser();
        var task = taskRepository.getTaskByUserAndId(user, id);

        if (task == null) return HelpService.handleError("Not found", HttpStatus.NOT_FOUND);

        taskMapper.update(task, requestTaskCreate);
        taskRepository.save(task);

        task.prepared();

        return ResponseEntity.ok(task);
    }

    public ResponseEntity respone(Long id, RequestRespone requestRespone) {
        var user = ClassUtils.getUser();

        var task = taskRepository.getTaskById(id);
        if (task == null) {
            return HelpService.handleError("Not found", HttpStatus.NOT_FOUND);
        }

        if (task.getUser().getId() == user.getId()) {
            return HelpService.handleError("Forbidden", HttpStatus.FORBIDDEN);
        }
        if (task.checkResponse(user)) {
            return HelpService.handleOk("OK", HttpStatus.OK);
        }

        var respond = responeMapper.fromRequestResponeToRespond(requestRespone);
        respond.setUser(user);
        respondRepository.save(respond);

        return ResponseEntity.ok(respond);
    }

    public ResponseEntity get(Long id) {
        var task = taskRepository.getTaskById(id);
        if (task == null) {
            return HelpService.handleError("Not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(task);
    }

    public ResponseEntity respone(RespondRequest respondRequest) {
        var user = ClassUtils.getUser();

        Task task = taskRepository.getTaskById(respondRequest.getId());
        if (task == null) {
            return ResponseEntity.status(400).body("Error");
        }

        if (task.getUser().getId() == user.getId()) {
            var jsonObject = new JSONObject();
            jsonObject.put("res", "MY_TASK");
            return ResponseEntity.ok(jsonObject);
        }

        var respond = new Respond(user, respondRequest.getMessage(), respondRequest.getPrice(), respondRequest.getCountDay());

        var notificated = new Notification(task.getUser(), TaskType.USER_RESPOND_ME, text_user_respond_me);

        var notificate = new Notification(user, TaskType.USER_RESPONDED_YOU, text_user_responded_you);


        task.getResponds().add(respond);
        task.setCountRespond(task.getCountRespond() + 1);
        task.setCountLike(task.getCountLike() + 1);
        task.setCountView(task.getCountView() + 1);

        taskRepository.save(task);
        var dilogue = new Dilog(task.getName(),user,task.getUser());


        dilogRepository.save(dilogue);
        notificationRepository.save(notificate);
        notificationRepository.save(notificated);


        return ResponseEntity.ok(respond);


    }


    public ResponseEntity checkRespone(Long id) {

        var user = ClassUtils.getUser();

        Task task = taskRepository.getTaskById(id);
        if (task == null) {
            return ResponseEntity.status(400).body("Error");
        }

        if (task.getUser().getId() == user.getId()) {
            var jsonObject = new JSONObject();
            jsonObject.put("type", "MY_TASK");
            return ResponseEntity.ok(jsonObject);
        }


        var jsonObject = new JSONObject();

        for (Respond respond : task.getRespond()) {
            if (respond.getUser().getId() == user.getId()) {
                jsonObject.put("type", "ALREADY_EXIST");
                break;
            }
        }
        return ResponseEntity.ok(jsonObject);

    }
}
