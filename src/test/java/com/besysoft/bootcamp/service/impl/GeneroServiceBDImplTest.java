package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.dto.mapper.IGeneroMapper;
import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;
import com.besysoft.bootcamp.repository.database.IGeneroRepository;
import com.besysoft.bootcamp.util.GeneroTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class GeneroServiceBDImplTest {

    @MockBean
    private IGeneroRepository generoRepository;

    @Autowired
    private IGeneroMapper generoMapper;

    @Autowired
    GeneroServiceBDImpl generoServiceBD;

    @Test
    void obtenerTodos() {
        //GIVEN
        List<GeneroOutDto> esperado = GeneroTestUtil.GENEROS_CON_ID
                .stream()
                .map(this.generoMapper::mapToDto)
                .collect(Collectors.toList());

        when(this.generoRepository.findAll()).thenReturn(GeneroTestUtil.GENEROS_CON_ID);

        //WHEN
        List<GeneroOutDto> actual = this.generoServiceBD.obtenerTodos();

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado.size(), actual.size());
        assertEquals(esperado, actual);
        verify(this.generoRepository).findAll();
    }

    @Test
    void crear() {
        //GIVEN
        Genero genero = GeneroTestUtil.GENERO1_CON_ID;
        GeneroOutDto esperado = this.generoMapper.mapToDto(genero);

        GeneroInDto dto = new GeneroInDto();
        dto.setNombre(genero.getNombre());

        when(this.generoRepository.save(any(Genero.class))).thenReturn(genero);
        when(this.generoRepository.existsByNombre(anyString())).thenReturn(false);

        //WHEN
        GeneroOutDto actual = this.generoServiceBD.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.generoRepository).save(any(Genero.class));
        verify(this.generoRepository).existsByNombre(anyString());
    }

    @Test
    void actualizar() {
        //GIVEN
        Genero genero = GeneroTestUtil.GENERO1_CON_ID;
        GeneroOutDto esperado = this.generoMapper.mapToDto(genero);
        Long id = genero.getId();

        GeneroInDto dto = new GeneroInDto();
        dto.setNombre(genero.getNombre());

        when(this.generoRepository.save(any(Genero.class))).thenReturn(genero);
        when(this.generoRepository.existsByNombre(anyString())).thenReturn(false);
        when(this.generoRepository.existsById(anyLong())).thenReturn(true);

        //WHEN
        GeneroOutDto actual = this.generoServiceBD.actualizar(id, dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.generoRepository).save(any(Genero.class));
        verify(this.generoRepository).existsByNombre(anyString());
        verify(this.generoRepository).existsById(anyLong());
    }

    @Test
    void buscarPorNombre() {
        //GIVEN
        Genero genero = GeneroTestUtil.GENERO1_CON_ID;
        String nombre = genero.getNombre();

        when(this.generoRepository.findByNombre(anyString()))
                .thenReturn(Optional.of(genero));

        //WHEN
        Optional<Genero> actual = this.generoServiceBD.buscarPorNombre(nombre);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(nombre, actual.get().getNombre());
        verify(this.generoRepository).findByNombre(anyString());
    }

    @Test
    void buscarPorId() {
        //GIVEN
        Genero genero = GeneroTestUtil.GENERO1_CON_ID;
        Long id = genero.getId();

        when(this.generoRepository.findById(anyLong()))
                .thenReturn(Optional.of(genero));

        //WHEN
        Optional<Genero> actual = this.generoServiceBD.buscarPorId(id);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(id, actual.get().getId());
        verify(this.generoRepository).findById(anyLong());
    }

    @Test
    void existePorNombre() {
        //GIVEN
        String nombre = GeneroTestUtil.GENERO1_CON_ID.getNombre();

        when(this.generoRepository.existsByNombre(anyString())).thenReturn(true);

        //WHEN
        boolean existePorNombre = this.generoServiceBD.existePorNombre(nombre);

        //THEN
        assertTrue(existePorNombre);
        verify(this.generoRepository).existsByNombre(anyString());
    }

    @Test
    void existePorId() {
        //GIVEN
        Long id = GeneroTestUtil.GENERO1_CON_ID.getId();

        when(this.generoRepository.existsById(anyLong())).thenReturn(true);

        //WHEN
        boolean existePorId = this.generoServiceBD.existePorId(id);

        //THEN
        assertTrue(existePorId);
        verify(this.generoRepository).existsById(anyLong());
    }

}