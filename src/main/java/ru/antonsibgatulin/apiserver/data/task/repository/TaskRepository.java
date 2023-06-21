package ru.antonsibgatulin.apiserver.data.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.antonsibgatulin.apiserver.data.task.Task;
import ru.antonsibgatulin.apiserver.data.user.User;

import java.awt.print.Pageable;
import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task getTaskById(Long id);
    Task getTaskByUserAndId(User user, Long id);



}
