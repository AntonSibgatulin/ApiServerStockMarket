package ru.antonsibgatulin.apiserver.data.respond.mapper;

import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestRespone;
import ru.antonsibgatulin.apiserver.data.respond.Respond;
import ru.antonsibgatulin.apiserver.data.user.User;

public interface ResponeMapper {
    Respond fromRequestResponeToRespond(RequestRespone requestRespone);
}
