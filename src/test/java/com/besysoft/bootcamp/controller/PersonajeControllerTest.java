package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.PersonajeInDto;
import com.besysoft.bootcamp.dto.response.PersonajeOutDto;
import com.besysoft.bootcamp.service.IPersonajeService;
import com.besysoft.bootcamp.util.PersonajeTestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonajeController.class)
class PersonajeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IPersonajeService personajeService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/personajes";
    }

    @Test
    void buscarPorFiltros_ConParametrosNulos() throws Exception {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.generarPersonajesOutDtos();
        when(this.personajeService.buscarPorFiltros(isNull(), isNull()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(get(this.url)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.personajeService).buscarPorFiltros(isNull(), isNull());
    }

    @Test
    void buscarPorFiltros_ConEdadNulo() throws Exception {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.generarPersonajesOutDtos();
        String nombre = "Tiburon";
        when(this.personajeService.buscarPorFiltros(anyString(), isNull()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(get(this.url)
                        .param("nombre", nombre)
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.personajeService).buscarPorFiltros(anyString(), isNull());
    }

    @Test
    void buscarPorFiltros_ConNombreNulo() throws Exception {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.generarPersonajesOutDtos();
        Byte edad = (byte) 45;
        when(this.personajeService.buscarPorFiltros(isNull(), anyByte()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(get(this.url)
                        .param("edad", edad.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.personajeService).buscarPorFiltros(isNull(), anyByte());
    }

    @Test
    void buscarPorFiltros_ConAmbosParametros() throws Exception {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.generarPersonajesOutDtos();
        String nombre = "Tiburon";
        Byte edad = (byte) 45;
        when(this.personajeService.buscarPorFiltros(anyString(), anyByte()))
                .thenReturn(esperado);

        //WHEN
        this.mvc.perform(get(this.url)
                        .param("nombre", nombre)
                        .param("edad", edad.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.personajeService).buscarPorFiltros(anyString(), anyByte());
    }

    @Test
    void buscarPorEdades() throws Exception {
        //GIVEN
        List<PersonajeOutDto> esperado = PersonajeTestUtil.generarPersonajesOutDtos();
        Byte desde = PersonajeTestUtil.DESDE;
        Byte hasta = PersonajeTestUtil.HASTA;
        when(this.personajeService.buscarPorEdades(anyByte(), anyByte())).thenReturn(esperado);

        //WHEN
        this.mvc.perform(get(this.url.concat("/edades"))
                .param("desde", desde.toString())
                .param("hasta", hasta.toString())
                .contentType(MediaType.APPLICATION_JSON))
                //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        verify(this.personajeService).buscarPorEdades(anyByte(), anyByte());
    }

    @Test
    void crear() throws Exception {
        //GIVEN
        PersonajeInDto dto = PersonajeTestUtil.PERSONAJE_IN_DTO;
        PersonajeOutDto esperado = PersonajeTestUtil.PERSONAJE_OUT_DTO;
        when(this.personajeService.crear(any(PersonajeInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value(esperado.getNombre()))
                .andExpect(jsonPath("$.edad").value(esperado.getEdad().toString()))
                .andExpect(jsonPath("$.peso").value(esperado.getPeso().toString()))
                .andExpect(jsonPath("$.historia").value(esperado.getHistoria()));
        verify(this.personajeService).crear(any(PersonajeInDto.class));
    }

    @Test
    void actualizar() throws Exception {
        PersonajeInDto dto = PersonajeTestUtil.PERSONAJE_IN_DTO;
        PersonajeOutDto esperado = PersonajeTestUtil.PERSONAJE_OUT_DTO;
        when(this.personajeService.actualizar(anyLong(), any(PersonajeInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(put(this.url.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(esperado.getNombre()))
                .andExpect(jsonPath("$.edad").value(esperado.getEdad().toString()))
                .andExpect(jsonPath("$.peso").value(esperado.getPeso().toString()))
                .andExpect(jsonPath("$.historia").value(esperado.getHistoria()));
        verify(this.personajeService).actualizar(anyLong(), any(PersonajeInDto.class));
    }

}