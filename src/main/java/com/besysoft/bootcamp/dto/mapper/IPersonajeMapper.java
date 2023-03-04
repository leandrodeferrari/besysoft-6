package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPersonajeMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "peliculasSeries", ignore = true)
    })
    Personaje mapToEntity(PersonajeInDto dto);

    PersonajeOutDto mapToDto(Personaje personaje);

}
