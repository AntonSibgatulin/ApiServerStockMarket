package ru.antonsibgatulin.apiserver.data.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonsibgatulin.apiserver.data.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
