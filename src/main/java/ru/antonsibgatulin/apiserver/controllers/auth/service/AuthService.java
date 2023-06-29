package ru.antonsibgatulin.apiserver.controllers.auth.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.antonsibgatulin.apiserver.config.JwtService;
import ru.antonsibgatulin.apiserver.controllers.auth.request.RequestBodyAuth;
import ru.antonsibgatulin.apiserver.controllers.auth.response.ResponseAuth;
import ru.antonsibgatulin.apiserver.controllers.reg.response.RegResponse;
import ru.antonsibgatulin.apiserver.data.error.ErrorResponse;
import ru.antonsibgatulin.apiserver.data.token.Token;
import ru.antonsibgatulin.apiserver.data.token.repository.TokenRepository;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;
import ru.antonsibgatulin.apiserver.services.HelpService;
import ru.antonsibgatulin.apiserver.utils.ClassUtils;

import java.util.HashMap;

@Service
public record AuthService(UserRepository userRepository, TokenRepository tokenRepository, JwtService jwtService) {


    public ResponseEntity auth(RequestBodyAuth requestBodyAuth) {
        var user = userRepository.getUserByEmailAndPassword(requestBodyAuth.getEmail(),requestBodyAuth.getPassword());
        if(user == null){
            return HelpService.handleError("Email not found",HttpStatus.NOT_FOUND);
        }
        var token = generateToken(user);
        var tokenEntity = new Token(token,user);
        tokenRepository.save(tokenEntity);

        var response = new RegResponse(tokenEntity);
        return ResponseEntity.ok(response);


    }


    public String generateToken(User user){
        return jwtService.generateToken(new HashMap<>(),user);
    }

    public ResponseEntity checkAuthByToken(String token) {
        var user = ClassUtils.getUser();
        HashMap<String,String> data = new HashMap<>();
        if (user == null){
            data.put("auth","ERROR");
        }else{
            data.put("auth","OK");
        }
        return ResponseEntity.ok(data);
    }
}