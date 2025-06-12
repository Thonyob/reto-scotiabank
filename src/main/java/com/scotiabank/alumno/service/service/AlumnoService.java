package com.scotiabank.alumno.service.service;

import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.dto.AlumnoResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlumnoService {

    Mono<Void> guardarAlumno(AlumnoRequest alumnoRequest);
    Flux<AlumnoResponse> listarAlumnosActivos();

}
