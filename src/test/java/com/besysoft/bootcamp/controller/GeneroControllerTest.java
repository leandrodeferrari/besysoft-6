package com.besysoft.bootcamp.controller;

import com.besysoft.bootcamp.dto.request.GeneroInDto;
import com.besysoft.bootcamp.dto.response.GeneroOutDto;
import com.besysoft.bootcamp.service.IGeneroService;
import com.besysoft.bootcamp.util.GeneroTestUtil;

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

@WebMvcTest(GeneroController.class)
class GeneroControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IGeneroService generoService;

    private ObjectMapper objectMapper;
    private String url;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
        this.url = "/generos";
    }

    @Test
    void obtenerTodos() throws Exception {
        //GIVEN
        List<GeneroOutDto> esperado = GeneroTestUtil.generarGenerosOutDtos();
        when(this.generoService.obtenerTodos()).thenReturn(esperado);

        //WHEN
        this.mvc.perform(
                get(this.url).contentType(MediaType.APPLICATION_JSON))
            //THEN
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nombre").value(esperado.get(0).getNombre()))
                .andExpect(jsonPath("$[1].nombre").value(esperado.get(1).getNombre()));
        verify(this.generoService).obtenerTodos();
    }

    @Test
    void crear() throws Exception {
        //GIVEN
        GeneroInDto dto = GeneroTestUtil.GENERO_IN_DTO;
        GeneroOutDto esperado = GeneroTestUtil.GENERO_OUT_DTO1;
        when(this.generoService.crear(any(GeneroInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(post(this.url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value(esperado.getNombre()));
        verify(this.generoService).crear(any(GeneroInDto.class));
    }

    @Test
    void actualizar() throws Exception {
        GeneroInDto dto = GeneroTestUtil.GENERO_IN_DTO;
        GeneroOutDto esperado = GeneroTestUtil.GENERO_OUT_DTO1;
        when(this.generoService.actualizar(anyLong(), any(GeneroInDto.class))).thenReturn(esperado);

        //WHEN
        this.mvc.perform(put(this.url.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(dto)))
                //THEN
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(esperado.getNombre()));
        verify(this.generoService).actualizar(anyLong(), any(GeneroInDto.class));
    }

}