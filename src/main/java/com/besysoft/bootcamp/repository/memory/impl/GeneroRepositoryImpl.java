package com.besysoft.bootcamp.repository.memory.impl;

import com.besysoft.bootcamp.domain.Genero;
import com.besysoft.bootcamp.repository.memory.IGeneroRepository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class GeneroRepositoryImpl implements IGeneroRepository {

    private List<Genero> generos;

    public GeneroRepositoryImpl() {

        this.generos = new ArrayList<>(
                Arrays.asList(
                        new Genero(1L, "Terror"),
                        new Genero(2L, "Suspenso"),
                        new Genero(3L, "Policial"),
                        new Genero(4L, "Romance")
                )
        );

    }

    @Override
    public List<Genero> obtenerTodos() {
        return this.generos;
    }

    @Override
    public Genero crear(Genero genero) {

        genero.setId(this.generos.size()+1L);

        this.generos.add(genero);

        return genero;

    }

    @Override
    public Genero actualizar(Long id, Genero genero) {

        this.generos.stream()
                .filter(g -> g.getId().equals(id)).findFirst().get().setNombre(genero.getNombre());

        return genero;

    }

    @Override
    public Optional<Genero> buscarPorNombre(String nombre) {
        return this.generos.stream()
                .filter(g -> g.getNombre().equalsIgnoreCase(nombre)).findFirst();
    }

    @Override
    public Optional<Genero> buscarPorId(Long id) {
        return this.generos.stream().filter(g -> g.getId().equals(id)).findFirst();
    }

    @Override
    public boolean existePorId(Long id) {
        return this.generos.stream().anyMatch(g -> g.getId().equals(id));
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return this.generos.stream().anyMatch(g -> g.getNombre().equalsIgnoreCase(nombre));
    }

}
