package com.scotiabank.alumno.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumno")
public class Alumno {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String estado;
    private Integer edad;
}