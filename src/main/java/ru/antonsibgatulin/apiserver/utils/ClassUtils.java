package ru.antonsibgatulin.apiserver.utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.antonsibgatulin.apiserver.data.user.User;

public class ClassUtils {
    public static User getUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
