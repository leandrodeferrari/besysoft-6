package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PeliculaSerieInDto;
import com.besysoft.bootcamp.service.IPeliculaSerieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return ResponseEntity.ok(this.peliculaSerieService.buscarPorFiltros(titulo, nombreGenero));
    }

    @GetMapping("/fechas")
    @ApiOperation(value = "Permite obtener todas las Peliculas/Series, según el rango de fechas de creación establecido")
    public ResponseEntity<?> buscarPorFechas(@RequestParam String desde,
                                             @RequestParam String hasta){
        return ResponseEntity.ok(this.peliculaSerieService.buscarPorFechas(desde, hasta));
    }

    @GetMapping("/calificaciones")
    @ApiOperation(value = "Permite obtener todas las Peliculas/Series, según el rango de calificaciones establecido")
    public ResponseEntity<?> buscarPorCalificaciones(@RequestParam Byte desde,
                                                     @RequestParam Byte hasta){
        return ResponseEntity.ok(this.peliculaSerieService.buscarPorCalificaciones(desde, hasta));
    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de una Pelicula/Serie")
    public ResponseEntity<?> crear(@Valid @RequestBody PeliculaSerieInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.peliculaSerieService.crear(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de una Pelicula/Serie")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PeliculaSerieInDto dto){
        return ResponseEntity.ok(this.peliculaSerieService.actualizar(id, dto));
    }

}
