package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Personaje;
import com.besysoft.bootcamp.dto.mapper.IPersonajeMapper;
import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;
import com.besysoft.bootcamp.repository.database.IPersonajeRepository;
import com.besysoft.bootcamp.util.PersonajeTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonajeServiceBDImplTest {

    @MockBean
    private IPersonajeRepository personajeRepository;

    @Autowired
    IPersonajeMapper personajeMapper;

    @Autowired
    private PersonajeServiceBDImpl personajeServiceBD;

    @Test
    void buscarPorFiltros_findAll() {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.PERSONAJES_CON_ID
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
        String nombre = null;
        Byte edad = null;

        when(this.personajeRepository.findAll()).thenReturn(PersonajeTestUtil.PERSONAJES_CON_ID);

        //WHEN
        List<PersonajeOutDto> actual = this.personajeServiceBD.buscarPorFiltros(nombre, edad);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PersonajeTestUtil.PERSONAJES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.personajeRepository).findAll();
    }

    @Test
    void buscarPorFiltros_findAllByNombreAndEdad() {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.PERSONAJES_CON_ID
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
        String nombre = PersonajeTestUtil.PERSONAJE1_CON_ID.getNombre();
        Byte edad = PersonajeTestUtil.PERSONAJE1_CON_ID.getEdad();

        when(this.personajeRepository.findAllByNombreAndEdad(anyString(), anyByte()))
                .thenReturn(PersonajeTestUtil.PERSONAJES_CON_ID);

        //WHEN
        List<PersonajeOutDto> actual = this.personajeServiceBD.buscarPorFiltros(nombre, edad);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PersonajeTestUtil.PERSONAJES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.personajeRepository).findAllByNombreAndEdad(anyString(), anyByte());
    }

    @Test
    void buscarPorFiltros_findAllByNombre() {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.PERSONAJES_CON_ID
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
        String nombre = PersonajeTestUtil.PERSONAJE1_CON_ID.getNombre();
        Byte edad = null;

        when(this.personajeRepository.findAllByNombre(anyString())).thenReturn(PersonajeTestUtil.PERSONAJES_CON_ID);

        //WHEN
        List<PersonajeOutDto> actual = this.personajeServiceBD.buscarPorFiltros(nombre, edad);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PersonajeTestUtil.PERSONAJES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.personajeRepository).findAllByNombre(anyString());
    }

    @Test
    void buscarPorFiltros_findAllByEdad() {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.PERSONAJES_CON_ID
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
        String nombre = null;
        Byte edad = PersonajeTestUtil.PERSONAJE1_CON_ID.getEdad();

        when(this.personajeRepository.findAllByEdad(anyByte()))
                .thenReturn(PersonajeTestUtil.PERSONAJES_CON_ID);

        //WHEN
        List<PersonajeOutDto> actual = this.personajeServiceBD.buscarPorFiltros(nombre, edad);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PersonajeTestUtil.PERSONAJES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.personajeRepository).findAllByEdad(anyByte());
    }

    @Test
    void buscarPorEdades() {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.PERSONAJES_CON_ID
                .stream()
                .map(this.personajeMapper::mapToDto)
                .collect(Collectors.toList());
        Byte desde = PersonajeTestUtil.DESDE;
        Byte hasta = PersonajeTestUtil.HASTA;

        when(this.personajeRepository.findAllByEdadBetween(anyByte(), anyByte()))
                .thenReturn(PersonajeTestUtil.PERSONAJES_CON_ID);

        //WHEN
        List<PersonajeOutDto> actual = this.personajeServiceBD.buscarPorEdades(desde, hasta);

        //THEN
        assertEquals(esperado, actual);
        verify(this.personajeRepository).findAllByEdadBetween(anyByte(), anyByte());
    }

    @Test
    void crear() {
        //GIVEN
        Personaje personaje = PersonajeTestUtil.PERSONAJE1_CON_ID;
        PersonajeOutDto esperado = this.personajeMapper.mapToDto(personaje);

        PersonajeInDto dto = new PersonajeInDto();
        dto.setNombre(personaje.getNombre());
        dto.setEdad(personaje.getEdad());
        dto.setPeso(personaje.getPeso());
        dto.setHistoria(personaje.getHistoria());

        when(this.personajeRepository.save(any(Personaje.class))).thenReturn(personaje);

        //WHEN
        PersonajeOutDto actual = this.personajeServiceBD.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.personajeRepository).save(any(Personaje.class));
    }

    @Test
    void actualizar() {
        //GIVEN
        Personaje personaje = PersonajeTestUtil.PERSONAJE1_CON_ID;
        PersonajeOutDto esperado = this.personajeMapper.mapToDto(personaje);
        Long id = personaje.getId();

        PersonajeInDto dto = new PersonajeInDto();
        dto.setNombre(personaje.getNombre());
        dto.setEdad(personaje.getEdad());
        dto.setPeso(personaje.getPeso());
        dto.setHistoria(personaje.getHistoria());

        when(this.personajeRepository.save(any(Personaje.class))).thenReturn(personaje);
        when(this.personajeRepository.existsById(anyLong())).thenReturn(true);

        //WHEN
        PersonajeOutDto actual = this.personajeServiceBD.actualizar(id, dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.personajeRepository).save(any(Personaje.class));
        verify(this.personajeRepository).existsById(anyLong());
    }

}