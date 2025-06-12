package com.scotiabank.alumno.service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlumnoRequest {


    private @NotNull(message = "El id no puede ser vacio")
    Long id;

    private @NotBlank(message = "El nombre es obligatorio")
    @Pattern(regexp = "[a-zA-Z]+", message = "Nombre solo puede contener letras")
    String nombre;

    private @NotBlank(message = "El apellido es obligatorio")
    @Pattern(regexp = "[a-zA-Z]+", message = "Apellido solo puede contener letras")
    String apellido;

    private @NotBlank(message = "El estado es obligatorio")
    @Pattern(regexp = "activo|inactivo", message = "Estado debe ser activo o inactivo")
    String estado;

    private @NotNull(message = "La edad es obligatoria")
    @Min(value = 0L, message = "La edad mínima permitida es 0")
    @Max(value = 100L, message = "La edad máxima permitida es 100 años")
    Integer edad;

}
