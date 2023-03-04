package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;

import java.util.Arrays;
import java.util.List;

public class GeneroTestUtil {

    public static final Genero GENERO1_SIN_ID = new Genero("Comedia");
    public static final Genero GENERO2_SIN_ID = new Genero("Ciencia ficcion");

    public static final List<Genero> GENEROS_CON_ID = Arrays.asList(
            new Genero(1L, "Terror"),
            new Genero(2L, "Policial"),
            new Genero(3L, "Suspenso"),
            new Genero(4L, "Romance")
    );

    public static final Genero GENERO1_CON_ID = GENEROS_CON_ID.get(0);

    /*La cantidad de generos en GENEROS_CON_ID debe ser la misma que los inserts de generos, en import.sql*/
    public static final int GENEROS_SIZE = GENEROS_CON_ID.size();

    public static List<GeneroOutDto> generarGenerosOutDtos(){

        GeneroOutDto generoOutDto1 = new GeneroOutDto();
        generoOutDto1.setNombre("Terror");
        GeneroOutDto generoOutDto2 = new GeneroOutDto();
        generoOutDto1.setNombre("Suspenso");

        return Arrays.asList(
          generoOutDto1,
          generoOutDto2
        );

    }

    public static final GeneroOutDto GENERO_OUT_DTO1 = generarGenerosOutDtos().get(0);

    public static GeneroInDto generarGeneroInDto(){

        GeneroInDto dto = new GeneroInDto();
        dto.setNombre("Terror");

        return dto;

    }

    public static final GeneroInDto GENERO_IN_DTO = generarGeneroInDto();
}
