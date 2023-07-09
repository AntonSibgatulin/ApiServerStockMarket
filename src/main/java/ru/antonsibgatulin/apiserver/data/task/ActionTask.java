package ru.antonsibgatulin.apiserver.data.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonsibgatulin.apiserver.data.user.User;

@Entity
@NoArgsConstructor

@Data
public class ActionTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Task task;

    private boolean private_task;

    @Column(nullable = true)
    private String message;

    private Long time;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public ActionTask(Task task,User user) {
        this.task = task;
        this.user = user;

        time=System.currentTimeMillis();
        private_task = false;
        taskType = TaskType.CREATE_PURCHASES;

    }
}
