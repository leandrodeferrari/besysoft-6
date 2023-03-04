package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;
import com.besysoft.bootcamp.repository.database.IPersonajeRepository;
import com.besysoft.bootcamp.service.IPersonajeService;
import com.besysoft.bootcamp.util.PersonajeUtil;
import com.besysoft.bootcamp.util.ValidacionGeneralUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ConditionalOnProperty(prefix = "app", name = "type-data", havingValue = "database")
@Service
public class PersonajeServiceBDImpl implements IPersonajeService {

    private final IPersonajeMapper personajeMapper;
    private final IPersonajeRepository personajeRepository;

    public PersonajeServiceBDImpl(IPersonajeMapper personajeMapper,
                                  IPersonajeRepository personajeRepository) {
        this.personajeMapper = personajeMapper;
        this.personajeRepository = personajeRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeOutDto> buscarPorFiltros(String nombre, Byte edad) {

        if(nombre == null && edad == null){
            return this.personajeRepository.findAll()
                    .stream()
                    .map(this.personajeMapper::mapToDto)
                    .collect(Collectors.toList());
        }

        if (nombre != null && edad != null){
            return this.personajeRepository.findAllByNombreAndEdad(nombre, edad)
                    .stream()
                    .map(this.personajeMapper::mapToDto)
                    .collect(Collectors.toList());
        }

        if(nombre != null){
            PersonajeUtil.validarNombreVacio(nombre);
            return this.personajeRepository.findAllByNombre(nombre)
                    .stream()
                    .map(this.personajeMapper::mapToDto)
                    .collect(Collectors.toList());
        } else {
            PersonajeUtil.validarEdadMinima(edad);
            return this.personajeRepository.findAllByEdad(edad)
                    .stream()
                    .map(this.personajeMapper::mapToDto)
                    .collect(Collectors.toList());
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<PersonajeOutDto> buscarPorEdades(Byte desde, Byte hasta) {

        PersonajeUtil.validarEdad(desde);
        PersonajeUtil.validarEdad(hasta);
        ValidacionGeneralUtil.validarRangoDeNumeros(desde, hasta);

        return this.personajeRepository.findAllByEdadBetween(desde, hasta)
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeOutDto crear(PersonajeInDto dto) {

        PersonajeUtil.validarDto(dto);

        Personaje personaje = this.personajeRepository.save(this.personajeMapper.mapToEntity(dto));

        return this.personajeMapper.mapToDto(personaje);

    }

    @Transactional(readOnly = false)
    @Override
    public PersonajeOutDto actualizar(Long id, PersonajeInDto dto) {

        ValidacionGeneralUtil.validarId(id);
        PersonajeUtil.validarDto(dto);

        if(!this.personajeRepository.existsById(id)){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + PersonajeUtil.NO_EXISTE_POR_ID);
            throw new IllegalArgumentException(PersonajeUtil.NO_EXISTE_POR_ID);
        }

        Personaje personaje = this.personajeMapper.mapToEntity(dto);
        personaje.setId(id);

        return this.personajeMapper.mapToDto(this.personajeRepository.save(personaje));

    }

}
