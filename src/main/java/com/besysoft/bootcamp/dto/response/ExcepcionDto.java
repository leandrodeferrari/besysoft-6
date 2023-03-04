package com.besysoft.bootcamp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExcepcionDto {

    private int estado;
    private String mensaje;
    private Map<String, String> detalle;

}
