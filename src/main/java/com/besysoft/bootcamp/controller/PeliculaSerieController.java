package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.service.IPeliculaSerieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "Endpoints - Pelicula/Serie", tags = "Acciones permitidas para la entidad de Pelicula/Serie")
@RequestMapping("/peliculas-series")
public class PeliculaSerieController {

    private final IPeliculaSerieService peliculaSerieService;

    public PeliculaSerieController(IPeliculaSerieService peliculaSerieService) {
        this.peliculaSerieService = peliculaSerieService;
    }

    @GetMapping
    @ApiOperation(value = "Permite obtener todas las Peliculas/Series, según los filtros establecidos")
    public ResponseEntity<?> buscarPorFiltros(@RequestParam(required = false) String titulo,
                                             @RequestParam(required = false) String nombreGenero){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorFiltros(titulo, nombreGenero));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo buscarPorFiltros(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch(RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el endpoint buscarPorFiltros(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @GetMapping("/fechas")
    @ApiOperation(value = "Permite obtener todas las Peliculas/Series, según el rango de fechas de creación establecido")
    public ResponseEntity<?> buscarPorFechas(@RequestParam String desde,
                                             @RequestParam String hasta){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorFechas(desde, hasta));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo buscarPorFechas(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo buscarPorFechas(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @GetMapping("/calificaciones")
    @ApiOperation(value = "Permite obtener todas las Peliculas/Series, según el rango de calificaciones establecido")
    public ResponseEntity<?> buscarPorCalificaciones(@RequestParam Byte desde,
                                                     @RequestParam Byte hasta){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.buscarPorCalificaciones(desde, hasta));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo buscarPorCalificaciones(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo buscarPorCalificaciones(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de una Pelicula/Serie")
    public ResponseEntity<?> crear(@Valid @RequestBody PeliculaSerieInDto dto){

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.peliculaSerieService.crear(dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo crear(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de una Pelicula/Serie")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PeliculaSerieInDto dto){

        try {
            return ResponseEntity.ok(this.peliculaSerieService.actualizar(id, dto));
        } catch (IllegalArgumentException ex){
            log.info("Ocurrio una validacion personalizada, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (RuntimeException ex){
            log.warn("Ocurrio algo inesperado en el servidor, en el metodo actualizar(): " + ex.getMessage());
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }

    }

}
