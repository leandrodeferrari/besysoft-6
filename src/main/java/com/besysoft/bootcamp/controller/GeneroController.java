package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.service.IGeneroService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return ResponseEntity.ok(this.generoService.obtenerTodos());
    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de un Genero")
    public ResponseEntity<?> crear(@Valid @RequestBody GeneroInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.generoService.crear(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de un Genero")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody GeneroInDto dto){
        return ResponseEntity.ok(this.generoService.actualizar(id, dto));
    }

}
