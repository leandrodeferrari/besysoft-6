package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;

import java.util.List;

public interface IPeliculaSerieService {

    List<PeliculaSerieOutDto> buscarPorFiltros(String titulo, String nombreGenero);
    List<PeliculaSerieOutDto> buscarPorFechas(String desde, String hasta);
    List<PeliculaSerieOutDto> buscarPorCalificaciones(Byte desde, Byte hasta);
    PeliculaSerieOutDto crear(PeliculaSerieInDto dto);
    PeliculaSerieOutDto actualizar(Long id, PeliculaSerieInDto dto);

}
