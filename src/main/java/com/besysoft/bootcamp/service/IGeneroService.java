package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    List<GeneroOutDto> obtenerTodos();
    GeneroOutDto crear(GeneroInDto dto);
    GeneroOutDto actualizar(Long id, GeneroInDto dto);
    Optional<Genero> buscarPorNombre(String nombre);
    Optional<Genero> buscarPorId(Long id);
    boolean existePorNombre(String nombre);
    boolean existePorId(Long id);

}
