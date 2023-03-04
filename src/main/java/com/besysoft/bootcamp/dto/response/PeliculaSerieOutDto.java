package com.besysoft.bootcamp.dto.response;

import lombok.Data;

@Data
public class PeliculaSerieOutDto {

    private String titulo;
    private String fechaDeCreacion;
    private Byte calificacion;
    private String nombreGenero;

}
