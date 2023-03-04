package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.service.IPersonajeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        return ResponseEntity.ok(this.personajeService.buscarPorFiltros(nombre, edad));
    }

    @GetMapping("/edades")
    @ApiOperation(value = "Permite obtener todos los Personajes, según el rango de edades establecido")
    public ResponseEntity<?> buscarPorEdades(@RequestParam Byte desde,
                                             @RequestParam Byte hasta){
        return ResponseEntity.ok(this.personajeService.buscarPorEdades(desde, hasta));
    }

    @PostMapping
    @ApiOperation(value = "Permite la creación de un Personaje")
    public ResponseEntity<?> crear(@Valid @RequestBody PersonajeInDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.personajeService.crear(dto));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Permite la modificación de un Personaje")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @Valid @RequestBody PersonajeInDto dto){
        return ResponseEntity.ok(this.personajeService.actualizar(id, dto));
    }

}
