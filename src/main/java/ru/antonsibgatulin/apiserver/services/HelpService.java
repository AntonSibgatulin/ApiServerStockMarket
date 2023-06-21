package ru.antonsibgatulin.apiserver.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.antonsibgatulin.apiserver.data.error.ErrorResponse;

@Service
public class HelpService {
    public static  ResponseEntity handleError(String message,HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(message, httpStatus);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static  ResponseEntity handleOk(String message,HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(message, httpStatus);
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }
}
