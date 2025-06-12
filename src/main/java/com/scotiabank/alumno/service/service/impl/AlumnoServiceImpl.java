package com.scotiabank.alumno.service.service.impl;


import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.dto.AlumnoResponse;
import com.scotiabank.alumno.service.model.Alumno;
import com.scotiabank.alumno.service.repository.AlumnoRepository;
import com.scotiabank.alumno.service.service.AlumnoService;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final R2dbcEntityTemplate template;
    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository,R2dbcEntityTemplate template) {
        this.alumnoRepository = alumnoRepository;
        this.template=template;
    }

    public Mono<Void> guardarAlumno(AlumnoRequest alumnoRequest) {
        return alumnoRepository.existsById(alumnoRequest.getId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("El ID ya está registrado"));
                    } else {
                        return template.insert(Alumno.class).using(Alumno.builder()
                                .id(alumnoRequest.getId())
                                .nombre(alumnoRequest.getNombre())
                                .apellido(alumnoRequest.getApellido())
                                .estado(alumnoRequest.getEstado())
                                .edad(alumnoRequest.getEdad())
                                .build());
                    }
                }).then();
    }

    public Flux<AlumnoResponse> listarAlumnosActivos() {
        return alumnoRepository.findByEstado("activo")
                .map(alumno -> AlumnoResponse.builder()
                        .id(alumno.getId())
                        .nombre(alumno.getNombre())
                        .apellido(alumno.getApellido())
                        .estado(alumno.getEstado())
                        .edad(alumno.getEdad())
                        .build()
                );
    }
}