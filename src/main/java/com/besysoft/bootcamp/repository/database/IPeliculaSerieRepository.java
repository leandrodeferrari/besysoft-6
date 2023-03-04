package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.domain.PeliculaSerie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long> {

    List<PeliculaSerie> findAllByGenero(Genero genero);
    Optional<PeliculaSerie> findByTitulo(String titulo);
    List<PeliculaSerie> findAllByTituloAndGenero(String titulo, Genero genero);
    List<PeliculaSerie> findAllByFechaDeCreacionBetween(LocalDate start, LocalDate end);
    List<PeliculaSerie> findAllByCalificacionBetween(Byte start, Byte end);
    boolean existsByTitulo(String titulo);

}
