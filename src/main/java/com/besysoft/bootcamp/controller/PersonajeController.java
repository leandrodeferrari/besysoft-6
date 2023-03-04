package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.service.IPersonajeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "Endpoints - Personaje", tags = "Acciones permitidas para la entidad de Personaje")
@RequestMapping("/personajes")
public class PersonajeController {

    private final IPersonajeService personajeService;

    public PersonajeController(IPersonajeService personajeService) {
        this.personajeService = personajeService;
    }

    @GetMapping
    @ApiOperation(value = "Permite obtener todos los Personajes, según los filtros establecidos")
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String nombre,
                                              @RequestParam(required = false) Byte edad){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorFiltros(nombre, edad));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo buscarPorFiltros(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo buscarPorFiltros(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @GetMapping("/edades")
    @ApiOperation(value = "Permite obtener todos los Personajes, según el rango de edades establecido")
    public ResponseEntity<?> buscarPorEdades(@RequestParam Byte desde,
                                             @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(this.personajeService.buscarPorEdades(desde, hasta));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo buscarPorEdades(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo buscarPorEdades(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de un Personaje")
    public ResponseEntity<?> crear(@Valid @RequestBody PersonajeInDto dto){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.personajeService.crear(dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de un Personaje")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PersonajeInDto dto){

        try {
            return ResponseEntity.ok(this.personajeService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
