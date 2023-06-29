package ru.antonsibgatulin.apiserver.controllers.stockmarket;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import ru.antonsibgatulin.apiserver.controllers.stockmarket.service.StockService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stockmarket")
public class StockMarketController {

    private final StockService stockService;


    @PostMapping("/main/{page}")
    public ResponseEntity main(@PathVariable("page") Integer id){
        return stockService.main(id);
    }

    @GetMapping("/search")
    public ResponseEntity search(){
        return null;
    }



}
