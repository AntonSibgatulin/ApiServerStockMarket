package ru.antonsibgatulin.apiserver.data.profile;

import jakarta.persistence.*;
import lombok.Data;
import ru.antonsibgatulin.apiserver.data.user.User;
@Data
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;


}
