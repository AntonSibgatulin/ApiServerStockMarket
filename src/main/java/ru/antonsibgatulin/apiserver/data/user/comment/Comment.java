package ru.antonsibgatulin.apiserver.data.user.comment;

import jakarta.persistence.*;
import lombok.Data;
import ru.antonsibgatulin.apiserver.data.user.User;

@Data
@Entity
@Table(name = "comment_user")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    @Column(columnDefinition = "VARCHAR(1024)")
    private String text;

    private Integer starts;
}
