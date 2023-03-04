package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;
import com.besysoft.bootcamp.util.GeneroTestUtil;
import com.besysoft.bootcamp.util.PeliculaSerieTestUtil;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IPeliculaSerieRepositoryTest {

    @Autowired
    IGeneroRepository generoRepository;

    @Autowired
    IPeliculaSerieRepository peliculaSerieRepository;

    @Test
    void findAll() {
        //GIVEN
        // Ya el metodo setUp() me genera el contexto necesario para este test.

        //WHEN
        List<PeliculaSerie> peliculasSeries = this.peliculaSerieRepository.findAll();

        //THEN
        assertFalse(peliculasSeries.isEmpty());
        assertEquals(PeliculaSerieTestUtil.PELICULAS_SERIES_SIZE, peliculasSeries.size());
    }

    @Test
    void save() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);

        PeliculaSerie esperado = PeliculaSerieTestUtil.PELICULA_SERIE1_SIN_ID;
        esperado.setGenero(genero);

        //WHEN
        PeliculaSerie actual = this.peliculaSerieRepository.save(esperado);

        //THEN
        assertEquals(esperado.getTitulo(), actual.getTitulo());
        assertEquals(esperado.getFechaDeCreacion(), actual.getFechaDeCreacion());
        assertEquals(esperado.getCalificacion(), actual.getCalificacion());
        assertEquals(esperado.getGenero(), actual.getGenero());
    }

    @Test
    void update() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);
        PeliculaSerie peliculaSerieParaGuardar = PeliculaSerieTestUtil.PELICULA_SERIE1_SIN_ID;
        peliculaSerieParaGuardar.setGenero(genero);
        PeliculaSerie peliculaSerie = this.peliculaSerieRepository.save(peliculaSerieParaGuardar);
        Long id = peliculaSerie.getId();

        PeliculaSerie esperado = PeliculaSerieTestUtil.PELICULA_SERIE2_SIN_ID;
        esperado.setGenero(genero);
        esperado.setId(id);

        //WHEN
        PeliculaSerie actual = this.peliculaSerieRepository.save(esperado);

        //THEN
        assertEquals(esperado.getId(), actual.getId());
        assertEquals(esperado.getTitulo(), actual.getTitulo());
        assertEquals(esperado.getFechaDeCreacion(), actual.getFechaDeCreacion());
        assertEquals(esperado.getCalificacion(), actual.getCalificacion());
        assertEquals(esperado.getGenero(), actual.getGenero());
    }

    @Test
    void existsById() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE2_SIN_ID;
        peliculaSerie.setGenero(genero);

        PeliculaSerie esperado = this.peliculaSerieRepository.save(peliculaSerie);
        Long id = esperado.getId();

        //WHEN
        boolean existePorId = this.peliculaSerieRepository.existsById(id);

        //THEN
        assertTrue(existePorId);
    }

    @Test
    void findAllByGenero() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO1_SIN_ID);

        List<PeliculaSerie> esperado = this.peliculaSerieRepository.findAll()
                .stream()
                .filter(p -> p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByGenero(genero);

        //THEN
        assertEquals(esperado, actual);
    }

    @Test
    void findByTitulo() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE1_SIN_ID;
        peliculaSerie.setGenero(genero);
        this.peliculaSerieRepository.save(peliculaSerie);
        String titulo = peliculaSerie.getTitulo();

        PeliculaSerie esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(p -> p.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow();

        //WHEN
        Optional<PeliculaSerie> actual = this.peliculaSerieRepository.findByTitulo(titulo);

        //THEN
        assertTrue(actual.isPresent());
        assertEquals(esperado.getId(), actual.get().getId());
        assertEquals(esperado.getTitulo(), actual.get().getTitulo());
        assertEquals(esperado.getCalificacion(), actual.get().getCalificacion());
        assertEquals(esperado.getGenero(), actual.get().getGenero());
        assertEquals(esperado.getFechaDeCreacion(), actual.get().getFechaDeCreacion());
    }

    @Test
    void findAllByTituloAndGenero() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE2_SIN_ID;
        peliculaSerie.setGenero(genero);
        this.peliculaSerieRepository.save(peliculaSerie);
        String titulo = peliculaSerie.getTitulo();

        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream().filter(p -> p.getTitulo().equalsIgnoreCase(titulo) && p.getGenero().equals(genero))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByTituloAndGenero(titulo, genero);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByFechaDeCreacionBetween() {
        //GIVEN
        LocalDate desde = PeliculaSerieTestUtil.DESDE_LOCAL_DATE;
        LocalDate hasta = PeliculaSerieTestUtil.HASTA_LOCAL_DATE;

        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getFechaDeCreacion().isAfter(desde.minusDays(1)) && ps.getFechaDeCreacion().isBefore(hasta.plusDays(1)))
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByFechaDeCreacionBetween(desde, hasta);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void findAllByCalificacionBetween() {
        //GIVEN
        Byte desde = PeliculaSerieTestUtil.DESDE_BYTE;
        Byte hasta = PeliculaSerieTestUtil.HASTA_BYTE;

        List<PeliculaSerie> esperado = this.peliculaSerieRepository
                .findAll()
                .stream()
                .filter(ps -> ps.getCalificacion() >= desde && ps.getCalificacion() <= hasta)
                .collect(Collectors.toList());

        //WHEN
        List<PeliculaSerie> actual = this.peliculaSerieRepository
                .findAllByCalificacionBetween(desde, hasta);

        //THEN
        assertFalse(actual.isEmpty());
        assertEquals(esperado, actual);
    }

    @Test
    void existsByTitulo() {
        //GIVEN
        Genero genero = this.generoRepository.save(GeneroTestUtil.GENERO2_SIN_ID);
        PeliculaSerie peliculaSerie = PeliculaSerieTestUtil.PELICULA_SERIE1_CON_ID;
        peliculaSerie.setGenero(genero);

        PeliculaSerie esperado = this.peliculaSerieRepository.save(peliculaSerie);
        String titulo = esperado.getTitulo();

        //WHEN
        boolean existePorTitulo = this.peliculaSerieRepository.existsByTitulo(titulo);

        //THEN
        assertTrue(existePorTitulo);
    }

}