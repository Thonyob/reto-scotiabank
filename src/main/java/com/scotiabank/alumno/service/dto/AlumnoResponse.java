package com.scotiabank.alumno.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoResponse {


    private Long id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;

}
