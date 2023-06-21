package ru.antonsibgatulin.apiserver.controllers.profile;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.antonsibgatulin.apiserver.controllers.profile.service.ProfileService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("/getProfile/{id}")
    public ResponseEntity getProfile(@PathParam("id") Long id){
        return profileService.getProfile(id);

    }

}
