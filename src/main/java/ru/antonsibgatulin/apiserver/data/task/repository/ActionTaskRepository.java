package ru.antonsibgatulin.apiserver.data.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.antonsibgatulin.apiserver.data.task.ActionTask;

public interface ActionTaskRepository extends JpaRepository<ActionTask,Long>
{

}
