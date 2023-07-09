package ru.antonsibgatulin.apiserver.controllers.notifications.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;
import ru.antonsibgatulin.apiserver.data.notification.Notification;
import ru.antonsibgatulin.apiserver.data.notification.repository.NotificationRepository;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;
import ru.antonsibgatulin.apiserver.utils.ClassUtils;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public record NotificationService(UserRepository userRepository, NotificationRepository notificationRepository) {


    public ResponseEntity get() throws JsonProcessingException {
        var user = ClassUtils.getUser();

        List<Notification> notifications = notificationRepository.findAllByUser(user);

        for(Notification notification:notifications){
            System.out.println(notification);
            System.out.println(ClassUtils.fromObjectToString(notification));
        }
        return ResponseEntity.ok(ClassUtils.fromObjectToString(notifications.toArray()));
    }

}
