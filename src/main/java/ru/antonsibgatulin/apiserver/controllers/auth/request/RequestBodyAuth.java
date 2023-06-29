package ru.antonsibgatulin.apiserver.controllers.auth.request;

import lombok.Data;

@Data
public class RequestBodyAuth {
    private String email;
    private String password;

    public RequestBodyAuth(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
