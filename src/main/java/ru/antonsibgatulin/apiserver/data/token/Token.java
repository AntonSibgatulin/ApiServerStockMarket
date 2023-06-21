package ru.antonsibgatulin.apiserver.data.token;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.antonsibgatulin.apiserver.data.user.User;

@Table(name = "tokens")
@Entity
@Data
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(columnDefinition = "VARCHAR(1024)")
    private String token;
    @ManyToOne
    private User user;

    public Token(String token, User user) {

        this.token = token;
        this.user = user;

    }
}
