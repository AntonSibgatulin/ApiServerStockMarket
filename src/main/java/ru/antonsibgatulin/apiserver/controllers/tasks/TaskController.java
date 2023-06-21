package ru.antonsibgatulin.apiserver.controllers.tasks;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestRespone;
import ru.antonsibgatulin.apiserver.controllers.tasks.request.RequestTaskCreate;
import ru.antonsibgatulin.apiserver.controllers.tasks.service.TaskService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/create")
    public ResponseEntity create(@Valid @RequestBody RequestTaskCreate requestTaskCreate){
        return taskService.create(requestTaskCreate);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathParam("id") Long id){
        return taskService.delete(id);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity edit(@PathParam("id") Long id,@Valid @RequestBody RequestTaskCreate requestTaskCreate){
        return taskService.edit(id,requestTaskCreate);
    }

    @GetMapping("/respone/{id}")
    public ResponseEntity respone(@PathParam("id") Long id, @Valid @RequestBody RequestRespone requestRespone){
        return taskService.respone(id,requestRespone);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathParam("id") Long id){
        return taskService.get(id);
    }
}
