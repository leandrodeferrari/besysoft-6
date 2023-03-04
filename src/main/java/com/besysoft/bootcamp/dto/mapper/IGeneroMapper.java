package com.besysoft.bootcamp.dto.mapper;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IGeneroMapper {

    @Mapping(target = "id", ignore = true)
    Genero mapToEntity(GeneroInDto dto);

    GeneroOutDto mapToDto(Genero genero);

}
