package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.service.IGeneroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "Endpoints - Genero", tags = "Acciones permitidas para la entidad de Genero")
@RequestMapping("/generos")
public class GeneroController {

    private final IGeneroService generoService;

    public GeneroController(IGeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    @ApiOperation(value = "Permite obtener todos los Generos")
    public ResponseEntity<?> obtenerTodos(){

        try {
            return ResponseEntity.ok(this.generoService.obtenerTodos());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el endpoint obtenerTodos(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de un Genero")
    public ResponseEntity<?> crear(@Valid @RequestBody GeneroInDto dto){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.generoService.crear(dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de un Genero")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody GeneroInDto dto){

        try {
            return ResponseEntity.ok(this.generoService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
