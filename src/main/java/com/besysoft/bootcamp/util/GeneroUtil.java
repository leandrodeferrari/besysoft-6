package com.besysoft.bootcamp.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneroUtil {

    public static final String NOMBRE_NULO_VACIO = "El nombre no puede ser nulo o vacío.";
    public static final String EXISTE = "El genero ya existe.";
    public static final String EXISTE_POR_NOMBRE = "Ya existe un genero con ese nombre.";
    public static final String NO_EXISTE_POR_ID = "No existe genero con ese ID.";

    public static void validarNombre(String nombre){

        if(nombre.isBlank()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarNombre(): " + NOMBRE_NULO_VACIO);
            throw new IllegalArgumentException(NOMBRE_NULO_VACIO);
        }

    }

}
