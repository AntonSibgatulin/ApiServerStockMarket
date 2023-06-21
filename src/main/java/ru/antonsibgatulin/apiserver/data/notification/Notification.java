package ru.antonsibgatulin.apiserver.data.notification;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private NotificationType notificationType;

    @Column(columnDefinition = "VARCHAR(150)")
    private String message;
    private Long time;
    private Boolean show = false;

}
