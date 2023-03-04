package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeliculaSerieUtil {

    public static final String CALIFICACION_NULA = "La calificación no puede ser nula.";
    public static final String CALIFICACION_RANGO = "La calificación no puede ser menor a 1 o mayor a 5.";
    public static final String TITULO_NULO_VACIO = "El título no puede ser nulo o vacío.";
    public static final String TITULO_VACIO = "El título no puede ser vacío.";
    public static final String NOMBRE_GENERO_VACIO = "El nombre del genero, no puede ser vacío.";
    public static final String EXISTE = "La pelicula/serie ya existe.";
    public static final String NO_EXISTE_GENERO_POR_NOMBRE = "No existe genero con ese nombre.";
    public static final String EXISTE_POR_TITULO = "Ya existe una pelicula/serie con ese título.";
    public static final String NO_EXISTE_POR_ID = "No existe pelicula/serie con ese ID.";

    public static void validar(PeliculaSerie peliculaSerie){

        validarTitulo(peliculaSerie.getTitulo());
        validarCalificacion(peliculaSerie.getCalificacion());
        FechaUtil.validar(peliculaSerie.getFechaDeCreacion());

    }

    public static void validarDto(PeliculaSerieInDto dto){

        validarTitulo(dto.getTitulo());
        validarCalificacion(dto.getCalificacion());
        FechaUtil.validar(FechaUtil.formatear(dto.getFechaDeCreacion()));

    }

    public static void validarCalificacion(Byte calificacion){

        if(calificacion == null){
            log.info("Ocurrio una validacion personalizada, en el metodo validarCalificacion(): " + CALIFICACION_NULA);
            throw new IllegalArgumentException(CALIFICACION_NULA);
        }

        if(calificacion < 1 || calificacion > 5){
            log.info("Ocurrio una validacion personalizada, en el metodo validarCalificacion(): " + CALIFICACION_RANGO);
            throw new IllegalArgumentException(CALIFICACION_RANGO);
        }

    }

    public static void validarTitulo(String titulo){

        if(titulo.isBlank()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarTitulo(): " + TITULO_NULO_VACIO);
            throw new IllegalArgumentException(TITULO_NULO_VACIO);
        }

    }

    public static void validarTituloVacio(String titulo){

        if(titulo.isEmpty()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarTituloVacio(): " + TITULO_VACIO);
            throw new IllegalArgumentException(TITULO_VACIO);
        }

    }

    public static void validarNombreGeneroVacio(String nombreGenero){

        if(nombreGenero.isEmpty()){
            log.info("Ocurrio una validacion personalizada, en el metodo validarNombreGeneroVacio(): " + NOMBRE_GENERO_VACIO);
            throw new IllegalArgumentException(NOMBRE_GENERO_VACIO);
        }

    }

}
