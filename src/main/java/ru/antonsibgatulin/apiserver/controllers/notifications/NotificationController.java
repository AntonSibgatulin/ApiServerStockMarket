package ru.antonsibgatulin.apiserver.controllers.notifications;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonsibgatulin.apiserver.controllers.notifications.service.NotificationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/get")
    public ResponseEntity get() throws JsonProcessingException {
        return notificationService.get();
    }
}
