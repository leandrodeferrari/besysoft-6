package com.besysoft.bootcamp.repository.memory;

import com.besysoft.bootcamp.domain.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroRepository {

    List<Genero> obtenerTodos();
    Genero crear(Genero genero);
    Genero actualizar(Long id, Genero genero);
    Optional<Genero> buscarPorNombre(String nombre);
    Optional<Genero> buscarPorId(Long id);
    boolean existePorId(Long id);
    boolean existePorNombre(String nombre);

}
