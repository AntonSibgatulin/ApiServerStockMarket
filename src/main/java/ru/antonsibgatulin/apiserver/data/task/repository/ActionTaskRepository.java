package ru.antonsibgatulin.apiserver.data.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.thymeleaf.expression.Lists;
import ru.antonsibgatulin.apiserver.data.task.ActionTask;
import ru.antonsibgatulin.apiserver.data.user.User;

import java.util.List;

public interface ActionTaskRepository extends JpaRepository<ActionTask,Long>
{

    List<ActionTask> findAllByUser(User user);
}
