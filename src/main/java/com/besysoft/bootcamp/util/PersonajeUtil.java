package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.exception.PersonajeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonajeUtil {

    public static final String EDAD_NULA = "La edad no puede ser nula.";
    public static final String EDAD_MENOR_A_CERO = "La edad no puede ser menor a 0.";
    public static final String HISTORIA_NULA_VACIA = "La historia no puede ser nula o vacía.";
    public static final String PESO_MAYOR_A_QUINIENTOS = "El peso no puede ser mayor a 500.";
    public static final String PESO_MENOR_A_CERO = "El peso no puede ser menor a 0.";
    public static final String PESO_NULO = "El peso no puede ser nulo.";
    public static final String NOMBRE_NULO_VACIO = "El nombre no puede ser nulo o vacío.";
    public static final String NOMBRE_VACIO = "El nombre no puede ser vacío.";
    public static final String NO_EXISTE_POR_ID = "No existe personaje con ese ID.";

    public static void validar(Personaje personaje){

        validarNombre(personaje.getNombre());
        validarEdad(personaje.getEdad());
        validarPeso(personaje.getPeso());
        validarHistoria(personaje.getHistoria());

    }

    public static void validarDto(PersonajeInDto dto){

        validarNombre(dto.getNombre());
        validarEdad(dto.getEdad());
        validarPeso(dto.getPeso());
        validarHistoria(dto.getHistoria());

    }

    public static void validarEdad(Byte edad){

        if(edad == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validarEdad(): " + EDAD_NULA);
            throw new PersonajeException(EDAD_NULA);
        }

        validarEdadMinima(edad);

    }

    public static void validarEdadMinima(Byte edad){

        if(edad < 0){
            log.info("Ocurrio una validacion personalizada, en el metodo validarEdadMinima(): " + EDAD_MENOR_A_CERO);
            throw new PersonajeException(EDAD_MENOR_A_CERO);
        }

    }

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarNombre(): " + NOMBRE_NULO_VACIO);
            throw new PersonajeException(NOMBRE_NULO_VACIO);
        }

    }

    public static void validarNombreVacio(String nombre){

        if(nombre.isEmpty()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarNombreVacio(): " + NOMBRE_VACIO);
            throw new PersonajeException(NOMBRE_VACIO);
        }

    }

    public static void validarPeso(Double peso){

        if(peso == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validarPeso(): " + PESO_NULO);
            throw new PersonajeException(PESO_NULO);
        }

        if(peso < 0){
            log.info("Ocurrio una validacion personalizada, en el metodo validarPeso(): " + PESO_MENOR_A_CERO);
            throw new PersonajeException(PESO_MENOR_A_CERO);
        }

        if(peso > 500){
            log.info("Ocurrio una validacion personalizada, en el metodo validarPeso(): " + PESO_MAYOR_A_QUINIENTOS);
            throw new PersonajeException(PESO_MAYOR_A_QUINIENTOS);
        }

    }

    public static void validarHistoria(String historia){

        if(historia.isBlank()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarHistoria(): " + HISTORIA_NULA_VACIA);
            throw new PersonajeException(HISTORIA_NULA_VACIA);
        }

    }

}
