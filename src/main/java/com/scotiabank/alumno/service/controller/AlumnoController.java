package com.scotiabank.alumno.service.controller;

import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.dto.AlumnoResponse;
import com.scotiabank.alumno.service.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
            this.alumnoService=alumnoService;
    }

    @PostMapping({"guardarAlumno"})
    public Mono<Void> registrarAlumno(@Valid @RequestBody AlumnoRequest alumnoRequest){
        return alumnoService.guardarAlumno(alumnoRequest);
    }

    @GetMapping({"listarAlumnos"})
    public Flux<AlumnoResponse> listarAlumnosActivos() {
        return alumnoService.listarAlumnosActivos();
    }
}
