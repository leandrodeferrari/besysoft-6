package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.exception.IdInvalidoException;
import com.besysoft.bootcamp.exception.RangoInvalidoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidacionGeneralUtil {

    public static final String RANGO_INVALIDO = "Rango inválido.";
    public static final String ID_MENOR_A_UNO = "El ID no puede ser menor a 1.";
    public static final String ID_NULO = "El ID no puede ser nulo.";

    public static void validarRangoDeNumeros(Byte desde, Byte hasta){

        if(desde > hasta){
            log.info("Ocurrio una validacion personalizada, en el metodo validarRangoDeNumeros(): " + RANGO_INVALIDO);
            throw new RangoInvalidoException(RANGO_INVALIDO);
        }

    }

    public static void validarId(Long id){

        if(id == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validarId(): " + ID_NULO);
            throw new IdInvalidoException(ID_NULO);
        }

        if(id < 1){
            log.info("Ocurrio una validacion personalizada, en el metodo validarId(): " + ID_MENOR_A_UNO);
            throw new IdInvalidoException(ID_MENOR_A_UNO);
        }

    }

}
