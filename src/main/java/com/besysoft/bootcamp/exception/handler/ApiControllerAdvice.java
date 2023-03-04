package com.besysoft.bootcamp.exception.handler;

import com.besysoft.bootcamp.dto.response.ExcepcionDto;
import com.besysoft.bootcamp.exception.*;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto validException(MethodArgumentNotValidException ex){

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
    public ExcepcionDto rangoInvalidoException(RangoInvalidoException ex){
        log.info("Ocurrio una validacion de rango: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto idInvalidoException(IdInvalidoException ex){
        log.info("Ocurrio una validacion de ID: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto dateTimeParseException(DateTimeParseException ex){
        log.info("Ocurrio una validacion en el formato de la fecha: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                "Patrón incorrecto. El patrón correcto es 'dd-MM-yyyy'",
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto fechaException(FechaException ex){
        log.info("Ocurrio una validacion de fecha: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto generoException(GeneroException ex){
        log.info("Ocurrio una validacion de genero: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto peliculaSerieException(PeliculaSerieException ex){
        log.info("Ocurrio una validacion de pelicula-serie: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExcepcionDto personajeException(PersonajeException ex){
        log.info("Ocurrio una validacion de personaje: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExcepcionDto internalServerError(RuntimeException ex){
        log.warn("Ocurrio algo inesperado en el servidor: " + ex.getMessage());
        return new ExcepcionDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
    }

}
