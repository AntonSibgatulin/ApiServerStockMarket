package ru.antonsibgatulin.apiserver.controllers.reg.request;

import lombok.Data;
import ru.antonsibgatulin.apiserver.data.profile.Profile;

@Data
public class RequestBodyRegUser {

    private String email;
    private String password;

    private Profile profile;

}
