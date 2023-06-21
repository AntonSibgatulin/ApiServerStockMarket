package ru.antonsibgatulin.apiserver.data.user.mapper;

import ru.antonsibgatulin.apiserver.controllers.reg.request.RequestBodyRegUser;
import ru.antonsibgatulin.apiserver.data.user.User;

public interface UserMapper {
    User fromRequestRegUserToUser(RequestBodyRegUser requestBodyRegUser);
}
