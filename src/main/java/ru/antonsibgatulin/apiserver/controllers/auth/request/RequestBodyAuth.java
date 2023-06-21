package ru.antonsibgatulin.apiserver.controllers.auth.request;

import lombok.Data;

@Data
public class RequestBodyAuth {
    private String email;
    private String password;
}
