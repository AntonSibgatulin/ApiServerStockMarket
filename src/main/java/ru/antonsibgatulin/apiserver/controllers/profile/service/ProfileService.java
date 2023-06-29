package ru.antonsibgatulin.apiserver.controllers.profile.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;
import ru.antonsibgatulin.apiserver.services.HelpService;
import ru.antonsibgatulin.apiserver.utils.ClassUtils;

@Service
public record ProfileService(UserRepository userRepository) {


    public ResponseEntity getProfile(Long id) {
        var user = userRepository.getUserById(id);
        if (user == null){
            return HelpService.handleError("User not found", HttpStatus.NOT_FOUND);

        }else{
            return ResponseEntity.ok(user);
        }
    }


    public User getMe() {
        return ClassUtils.getUser();
    }
}
