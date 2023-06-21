package ru.antonsibgatulin.apiserver.controllers.reg.service;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.antonsibgatulin.apiserver.config.JwtService;
import ru.antonsibgatulin.apiserver.controllers.reg.request.RequestBodyRegUser;
import ru.antonsibgatulin.apiserver.controllers.reg.response.RegResponse;
import ru.antonsibgatulin.apiserver.data.token.Token;
import ru.antonsibgatulin.apiserver.data.token.repository.TokenRepository;
import ru.antonsibgatulin.apiserver.data.user.User;
import ru.antonsibgatulin.apiserver.data.user.mapper.UserMapper;
import ru.antonsibgatulin.apiserver.data.user.repository.UserRepository;
import ru.antonsibgatulin.apiserver.services.HelpService;

import java.util.HashMap;

@Service
public record RegService(UserMapper userMapper, JwtService jwtService, TokenRepository tokenRepository,
                         UserRepository userRepository) {

    public ResponseEntity<RegResponse> reg(RequestBodyRegUser requestBodyRegUser) {

        if(userRepository.getUserByEmail(requestBodyRegUser.getEmail())!=null){
            return HelpService.handleError("Email already using", HttpStatus.ALREADY_REPORTED);
        }
        var user = userMapper.fromRequestRegUserToUser(requestBodyRegUser);
        userRepository.save(user);
        var token = generateToken(user);
        var tokenEntity = new Token(token,user);
        tokenRepository.save(tokenEntity);

        var response = new RegResponse(tokenEntity);
        return ResponseEntity.ok(response);


    }



    public String generateToken(User user){
        return jwtService.generateToken(new HashMap<>(),user);
    }
}
