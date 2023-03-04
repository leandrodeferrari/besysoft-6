package com.besysoft.bootcamp.repository.database;

import com.besysoft.bootcamp.domain.Personaje;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Long> {

    List<Personaje> findAllByNombre(String nombre);
    List<Personaje> findAllByEdad(Byte edad);
    List<Personaje> findAllByNombreAndEdad(String nombre, Byte edad);
    List<Personaje> findAllByEdadBetween(Byte start, Byte end);

}
