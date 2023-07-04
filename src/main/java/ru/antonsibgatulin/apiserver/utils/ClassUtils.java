package ru.antonsibgatulin.apiserver.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.antonsibgatulin.apiserver.data.user.User;

public class ClassUtils {
    public static User getUser(){
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public static <T> String fromObjectToString(T t) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(t);
    }
}
