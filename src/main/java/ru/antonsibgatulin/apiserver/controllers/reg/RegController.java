package ru.antonsibgatulin.apiserver.controllers.reg;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.antonsibgatulin.apiserver.controllers.reg.request.RequestBodyRegUser;
import ru.antonsibgatulin.apiserver.controllers.reg.response.RegResponse;
import ru.antonsibgatulin.apiserver.controllers.reg.service.RegService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reg")
public class RegController {

    private final RegService regService;


    @PostMapping("/")
    public ResponseEntity<RegResponse> reg(@Valid @RequestBody RequestBodyRegUser requestBodyRegUser){
        return regService.reg(requestBodyRegUser);
    }

}
