package ru.antonsibgatulin.apiserver.controllers.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonsibgatulin.apiserver.controllers.auth.request.RequestBodyAuth;
import ru.antonsibgatulin.apiserver.controllers.auth.response.ResponseAuth;
import ru.antonsibgatulin.apiserver.controllers.auth.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/")
    public ResponseEntity<ResponseAuth> auth(@Valid @RequestBody RequestBodyAuth requestBodyAuth){
        return authService.auth(requestBodyAuth);
    }

}
