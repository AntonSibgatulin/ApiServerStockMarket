package ru.antonsibgatulin.apiserver.controllers.stockmarket;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import ru.antonsibgatulin.apiserver.controllers.stockmarket.request.RespondRequest;
import ru.antonsibgatulin.apiserver.controllers.stockmarket.service.StockService;
import ru.antonsibgatulin.apiserver.controllers.tasks.service.TaskService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stockmarket")
public class StockMarketController {

    private final StockService stockService;
    private final TaskService taskService;



    @PostMapping("/main/{page}")
    public ResponseEntity main(@PathVariable("page") Integer id){
        return stockService.main(id);
    }

    @GetMapping("/search")
    public ResponseEntity search(){
        return null;
    }


    @PostMapping("/respone")
    public ResponseEntity respone(@Valid @RequestBody RespondRequest respondRequest){
        return taskService.respone(respondRequest);
    }

    @GetMapping("/check")
    public ResponseEntity checkRespone(@RequestParam("id") Long id){
        return taskService.checkRespone(id);

    }




}
