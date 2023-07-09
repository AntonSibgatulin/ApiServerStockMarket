package ru.antonsibgatulin.apiserver.data.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonsibgatulin.apiserver.data.notification.Notification;
import ru.antonsibgatulin.apiserver.data.user.User;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findAllByUser(User user);
}
