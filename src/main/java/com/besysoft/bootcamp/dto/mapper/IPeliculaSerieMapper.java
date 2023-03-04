package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IPeliculaSerieMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "genero", ignore = true),
            @Mapping(target = "personajes", ignore = true),
            @Mapping(target = "fechaDeCreacion", dateFormat = "dd-MM-yyyy")
    })
    PeliculaSerie mapToEntity(PeliculaSerieInDto dto);

    @Mappings({
            @Mapping(target = "nombreGenero", source = "peliculaSerie.genero.nombre"),
            @Mapping(target = "fechaDeCreacion", dateFormat = "dd-MM-yyyy")
    })
    PeliculaSerieOutDto mapToDto(PeliculaSerie peliculaSerie);

}
