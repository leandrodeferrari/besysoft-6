package com.besysoft.bootcamp.service;

import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;

import java.util.List;

public interface IPersonajeService {

    List<PersonajeOutDto> buscarPorFiltros(String nombre, Byte edad);
    List<PersonajeOutDto> buscarPorEdades(Byte desde, Byte hasta);
    PersonajeOutDto crear(PersonajeInDto dto);
    PersonajeOutDto actualizar(Long id, PersonajeInDto dto);

}
