package com.kainv.rest.exception_handlers;

import com.kainv.exceptions.MyEntityNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class EntityNotFoundHandler {
    @ResponseBody
    @ExceptionHandler(MyEntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String entityNotFoundHandler(MyEntityNotFoundException exception) {
        return exception.getMessage();
    }
}
