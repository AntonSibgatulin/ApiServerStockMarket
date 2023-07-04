package ru.antonsibgatulin.apiserver.data.notification;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonsibgatulin.apiserver.data.task.TaskType;
import ru.antonsibgatulin.apiserver.data.user.User;

@Data

@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Column(nullable = false, columnDefinition = "VARCHAR(150)")

    private String text;

    private Long time;

    private Boolean saw;

    public Notification(User user, TaskType taskType, String text) {
        this.user = user;

        this.taskType = taskType;
        time = System.currentTimeMillis();
        saw = false;
        this.text = text;
    }


}
