package com.besysoft.bootcamp.exception.handler;

import com.besysoft.bootcamp.dto.response.ExcepcionDto;
import com.besysoft.bootcamp.exception.GeneroException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto exceptionHandler(MethodArgumentNotValidException ex){

        List<FieldError> errores = ex.getBindingResult().getFieldErrors();

        Map<String, String> detalle = new HashMap<>();

        log.info("Ocurrio una validacion");

        errores.forEach(error -> {
            log.info("Atributo: " + error.getField() + " - Validacion: " + error.getDefaultMessage());
            detalle.put(error.getField(), error.getDefaultMessage());
        });

        return new ExcepcionDto(HttpStatus.BAD_REQUEST.value(), "Validaciones", detalle);

    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto isExist(GeneroException ex){
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

}
