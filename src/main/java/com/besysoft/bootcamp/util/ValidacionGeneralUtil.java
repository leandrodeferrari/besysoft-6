package com.besysoft.bootcamp.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidacionGeneralUtil {

    public static final String RANGO_INVALIDO = "Rango inválido.";
    public static final String ID_MENOR_A_UNO = "El ID no puede ser menor a 1.";
    public static final String ID_NULO = "El ID no puede ser nulo.";

    public static void validarRangoDeNumeros(Byte desde, Byte hasta){

        if(desde > hasta){
            log.info("Ocurrio una validacion personalizada, en el metodo validarRangoDeNumeros(): " + RANGO_INVALIDO);
            throw new IllegalArgumentException(RANGO_INVALIDO);
        }

    }

    public static void validarId(Long id){

        if(id == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validarId(): " + ID_NULO);
            throw new IllegalArgumentException(ID_NULO);
        }

        if(id < 1){
            log.info("Ocurrio una validacion personalizada, en el metodo validarId(): " + ID_MENOR_A_UNO);
            throw new IllegalArgumentException(ID_MENOR_A_UNO);
        }

    }

}
