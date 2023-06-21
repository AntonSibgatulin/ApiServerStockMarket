package ru.antonsibgatulin.apiserver.controllers.stockmarket.service;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;
import ru.antonsibgatulin.apiserver.data.task.repository.TaskRepository;

@Service
public record StockService(TaskRepository taskRepository) {
    private static final int PAGE_SIZE = 30;



    public ResponseEntity main(Integer id) {
        if(id<=0){
            id = 0;
        }
        Pageable pageable = PageRequest.of(id,PAGE_SIZE, Sort.by("countView").descending());
        return ResponseEntity.ok(taskRepository.findAll(pageable));
    }




}
