package com.besysoft.bootcamp.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Slf4j
public class FechaUtil {

    public static final String RANGO_INVALIDO = "Rango de fecha inválido.";
    public static final String FECHA_NULA = "La fecha no puede ser nula.";
    public static final String FECHA_FUTURA = "La fecha no puede ser del futuro.";

    public static LocalDate formatear(String fecha){

        DateTimeFormatter formateador = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .append(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                .toFormatter();

        return LocalDate.parse(fecha, formateador);

    }

    public static void validarRango(LocalDate desde, LocalDate hasta){

        if(desde.compareTo(hasta) > 0){
            log.info("Ocurrio una validacion personalizada, en el metodo validarRango(): " + RANGO_INVALIDO);
            throw new IllegalArgumentException(RANGO_INVALIDO);
        }

    }

    public static void validar(LocalDate fecha){

        if(fecha == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validar(): " + FECHA_NULA);
            throw new IllegalArgumentException(FECHA_NULA);
        }

        if(fecha.isAfter(LocalDate.now())){
            log.info("Ocurrio una validacion personalizada, en el metodo validar(): " + FECHA_FUTURA);
            throw new IllegalArgumentException(FECHA_FUTURA);
        }

    }

}
