package ru.antonsibgatulin.apiserver.controllers.profile;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antonsibgatulin.apiserver.controllers.profile.service.ProfileService;
import ru.antonsibgatulin.apiserver.data.user.User;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;


    @GetMapping("/getProfile/{id}")
    public ResponseEntity getProfile(@PathParam("id") Long id){
        return profileService.getProfile(id);

    }

    @PostMapping({"/getMe","/getMe/"})
    public User responseEntity(){
        return profileService.getMe();
    }



}
