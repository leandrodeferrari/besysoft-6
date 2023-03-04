package com.besysoft.bootcamp.service.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.dto.mapper.IPeliculaSerieMapper;
import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.dto.response.PeliculaSerieOutDto;
import com.besysoft.bootcamp.repository.database.IPeliculaSerieRepository;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroTestUtil;
import com.besysoft.bootcamp.util.PeliculaSerieTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PeliculaSerieServiceBDImplTest {

    @MockBean
    IPeliculaSerieRepository peliculaSerieRepository;

    @MockBean
    IGeneroService generoService;

    @Autowired
    IPeliculaSerieMapper peliculaSerieMapper;

    @Autowired
    PeliculaSerieServiceBDImpl peliculaSerieServiceBD;

    @Test
    void buscarPorFiltros_findAll() {
        //GIVEN
        List<PeliculaSerieOutDto> esperado = PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
        String titulo = null;
        String nombreGenero = null;

        when(this.peliculaSerieRepository.findAll()).thenReturn(PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID);

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorFiltros(titulo, nombreGenero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PeliculaSerieTestUtil.PELICULAS_SERIES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).findAll();
    }

    @Test
    void buscarPorFiltros_findAllByTituloAndGenero() {
        //GIVEN
        List<PeliculaSerieOutDto> esperado = PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
        String titulo = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID.getTitulo();
        String nombreGenero = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID.getGenero().getNombre();

        when(this.peliculaSerieRepository.findAllByTituloAndGenero(anyString(), any(Genero.class)))
                .thenReturn(PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID);
        when(this.generoService.existePorNombre(anyString())).thenReturn(true);
        when(this.generoService.buscarPorNombre(anyString()))
                .thenReturn(Optional.of(GeneroTestUtil.GENERO1_CON_ID));

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorFiltros(titulo, nombreGenero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PeliculaSerieTestUtil.PELICULAS_SERIES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).findAllByTituloAndGenero(anyString(), any(Genero.class));
        verify(this.generoService).existePorNombre(anyString());
        verify(this.generoService).buscarPorNombre(anyString());
    }

    @Test
    void buscarPorFiltros_findByTitulo() {
        //GIVEN
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID;
        List<PeliculaSerieOutDto> esperado = Collections
                .singletonList(this.peliculaSerieMapper.mapToDto(peliculaSerie));
        String titulo = peliculaSerie.getTitulo();
        String nombreGenero = null;

        when(this.peliculaSerieRepository.findByTitulo(anyString()))
                .thenReturn(Optional.of(peliculaSerie));

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorFiltros(titulo, nombreGenero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado.size(), actual.size());
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).findByTitulo(anyString());
    }

    @Test
    void buscarPorFiltros_findAllByGenero() {
        //GIVEN
        List<PeliculaSerieOutDto> esperado = PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
        String titulo = null;
        String nombreGenero = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID.getGenero().getNombre();

        when(this.generoService.existePorNombre(anyString())).thenReturn(true);
        when(this.generoService.buscarPorNombre(anyString()))
                .thenReturn(Optional.of(GeneroTestUtil.GENERO1_CON_ID));
        when(this.peliculaSerieRepository.findAllByGenero(any(Genero.class)))
                .thenReturn(PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID);

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorFiltros(titulo, nombreGenero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(PeliculaSerieTestUtil.PELICULAS_SERIES_SIZE, actual.size());
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).findAllByGenero(any(Genero.class));
        verify(this.generoService).existePorNombre(anyString());
        verify(this.generoService).buscarPorNombre(anyString());
    }

    @Test
    void buscarPorFechas() {
        //GIVEN
        List<PeliculaSerieOutDto> esperado = PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
        String desde = "01-01-2020";
        String hasta = "01-01-2022";

        when(this.peliculaSerieRepository
                .findAllByFechaDeCreacionBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID);

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorFechas(desde, hasta);

        //THEN
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository)
                .findAllByFechaDeCreacionBetween(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void buscarPorCalificaciones() {
        //GIVEN
        List<PeliculaSerieOutDto> esperado = PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID
                .stream()
                .map(this.peliculaSerieMapper::mapToDto)
                .collect(Collectors.toList());
        Byte desde = PeliculaSerieTestUtil.DESDE_BYTE;
        Byte hasta = PeliculaSerieTestUtil.HASTA_BYTE;

        when(this.peliculaSerieRepository.findAllByCalificacionBetween(anyByte(), anyByte()))
                .thenReturn(PeliculaSerieTestUtil.PELICULAS_SERIES_CON_ID);

        //WHEN
        List<PeliculaSerieOutDto> actual = this.peliculaSerieServiceBD.buscarPorCalificaciones(desde, hasta);

        //THEN
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).findAllByCalificacionBetween(anyByte(), anyByte());
    }

    @Test
    void crear() {
        //GIVEN
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID;
        PeliculaSerieOutDto esperado = this.peliculaSerieMapper.mapToDto(peliculaSerie);

        PeliculaSerieInDto dto = new PeliculaSerieInDto();
        dto.setTitulo(peliculaSerie.getTitulo());
        dto.setCalificacion(peliculaSerie.getCalificacion());
        dto.setGeneroId(peliculaSerie.getGenero().getId());
        dto.setFechaDeCreacion
                (peliculaSerie.getFechaDeCreacion().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        when(this.peliculaSerieRepository.existsByTitulo(anyString())).thenReturn(false);
        when(this.peliculaSerieRepository.save(any(PeliculaSerie.class))).thenReturn(peliculaSerie);
        when(this.generoService.buscarPorId(anyLong()))
                .thenReturn(Optional.of(GeneroTestUtil.GENERO1_CON_ID));

        //WHEN
        PeliculaSerieOutDto actual = this.peliculaSerieServiceBD.crear(dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).save(any(PeliculaSerie.class));
        verify(this.peliculaSerieRepository).existsByTitulo(anyString());
        verify(this.generoService).buscarPorId(anyLong());
    }

    @Test
    void actualizar() {
        //GIVEN
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID;
        PeliculaSerieOutDto esperado = this.peliculaSerieMapper.mapToDto(peliculaSerie);
        Long id = peliculaSerie.getId();

        PeliculaSerieInDto dto = new PeliculaSerieInDto();
        dto.setTitulo(peliculaSerie.getTitulo());
        dto.setCalificacion(peliculaSerie.getCalificacion());
        dto.setGeneroId(peliculaSerie.getGenero().getId());
        dto.setFechaDeCreacion
                (peliculaSerie.getFechaDeCreacion().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        when(this.peliculaSerieRepository.save(any(PeliculaSerie.class))).thenReturn(peliculaSerie);
        when(this.peliculaSerieRepository.existsById(anyLong())).thenReturn(true);
        when(this.peliculaSerieRepository.existsByTitulo(anyString())).thenReturn(false);
        when(this.generoService.buscarPorId(anyLong()))
                .thenReturn(Optional.of(GeneroTestUtil.GENERO1_CON_ID));

        //WHEN
        PeliculaSerieOutDto actual = this.peliculaSerieServiceBD.actualizar(id, dto);

        //THEN
        assertEquals(esperado, actual);
        verify(this.peliculaSerieRepository).save(any(PeliculaSerie.class));
        verify(this.peliculaSerieRepository).existsByTitulo(anyString());
        verify(this.peliculaSerieRepository).existsById(anyLong());
        verify(this.generoService).buscarPorId(anyLong());
    }

}