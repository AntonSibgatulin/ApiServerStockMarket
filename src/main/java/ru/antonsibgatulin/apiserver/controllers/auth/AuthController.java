package ru.antonsibgatulin.apiserver.controllers.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antonsibgatulin.apiserver.controllers.auth.request.RequestBodyAuth;
import ru.antonsibgatulin.apiserver.controllers.auth.response.ResponseAuth;
import ru.antonsibgatulin.apiserver.controllers.auth.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /*
    @PostMapping("/")
    public ResponseEntity<ResponseAuth> auth(@Valid @RequestBody RequestBodyAuth requestBodyAuth){
        return authService.auth(requestBodyAuth);
    }
     */
    @PostMapping("/")
    public ResponseEntity auth(@RequestParam("email")String email,@RequestParam("password")String pass){
        var requestBodyAuth = new RequestBodyAuth(email,pass);
        return authService.auth(requestBodyAuth);
    }


    @PostMapping("/check")
    public ResponseEntity checkAuthByToken(@RequestParam("token") String token){
        return authService.checkAuthByToken(token);

    }

}
