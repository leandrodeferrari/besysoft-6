package com.besysoft.bootcamp.util;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.List;

public class PeliculaSerieTestUtil {

    public static final PeliculaSerie PELICULA_SERIE1_SIN_ID = new PeliculaSerie
            ("Tiburon", LocalDate.parse("2023-01-01"), (byte) 4, null, null);
    public static final PeliculaSerie PELICULA_SERIE2_SIN_ID = new PeliculaSerie
            ("Jumanji", LocalDate.parse("2021-11-08"), (byte) 5, null, null);

    /*Tienen que ser rangos de fechas que se encuentren en los inserts de peliculas_series de import.sql*/
    public static final LocalDate DESDE_LOCAL_DATE = LocalDate.parse("2020-01-01");
    public static final LocalDate HASTA_LOCAL_DATE = LocalDate.parse("2023-01-01");

    /*Tienen que ser valores entre el 1 y el 5, inclusives*/
    public static final Byte DESDE_BYTE = (byte) 1;
    public static final Byte HASTA_BYTE = (byte) 3;

    public static final List<PeliculaSerie> PELICULAS_SERIES_CON_ID = Arrays.asList(
            new PeliculaSerie(1L, "Chucky", LocalDate.parse("2004-10-10"), (byte) 5, new Genero(1L, "Terror"),null),
            new PeliculaSerie(2L, "Annabelle", LocalDate.parse("2006-01-10"), (byte) 2, new Genero(1L, "Terror"),null),
            new PeliculaSerie(2L, "Jaula", LocalDate.parse("2021-02-12"), (byte) 1, new Genero(2L, "Policial"),null),
            new PeliculaSerie(2L, "Culpable", LocalDate.parse("2020-07-11"), (byte) 4, new Genero(3L, "Romance"),null)
    );

    public static final PeliculaSerie PELICULA_SERIE1_CON_ID = PELICULAS_SERIES_CON_ID.get(0);

    /*La cantidad de peliculas_series en PELICULAS_SERIES_CON_ID debe ser la misma que los inserts de peliculas_series, en import.sql*/
    public static final int PELICULAS_SERIES_SIZE = PELICULAS_SERIES_CON_ID.size();

    public static List<PeliculaSerieOutDto> generarPeliculasSeriesOutDtos(){
        PeliculaSerieOutDto peliculaSerieOutDto1 = new PeliculaSerieOutDto();
        peliculaSerieOutDto1.setTitulo("Tiburon");
        peliculaSerieOutDto1.setCalificacion((byte) 3);
        peliculaSerieOutDto1.setNombreGenero("Terror");
        peliculaSerieOutDto1.setFechaDeCreacion(LocalDate.parse("2020-10-10")
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        PeliculaSerieOutDto peliculaSerieOutDto2 = new PeliculaSerieOutDto();
        peliculaSerieOutDto2.setTitulo("Godzilla");
        peliculaSerieOutDto2.setCalificacion((byte) 5);
        peliculaSerieOutDto2.setNombreGenero("Suspenso");
        peliculaSerieOutDto2.setFechaDeCreacion(LocalDate.parse("2022-01-01")
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        return Arrays.asList(
                peliculaSerieOutDto1,
                peliculaSerieOutDto2
        );

    }

    public static final PeliculaSerieOutDto PELICULA_SERIE_OUT_DTO = generarPeliculasSeriesOutDtos().get(0);

    public static PeliculaSerieInDto generarPeliculaSerieInDto(){
        PeliculaSerieInDto dto = new PeliculaSerieInDto();
        dto.setTitulo("Godzilla");
        dto.setCalificacion((byte) 5);
        dto.setGeneroId(1L);
        dto.setFechaDeCreacion(LocalDate.parse("2022-01-01")
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        return dto;

    }

    public static final PeliculaSerieInDto PELICULA_SERIE_IN_DTO = generarPeliculaSerieInDto();

}
