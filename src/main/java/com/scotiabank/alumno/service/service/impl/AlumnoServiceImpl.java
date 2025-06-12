package com.scotiabank.alumno.service.service.impl;


import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.dto.AlumnoResponse;
import com.scotiabank.alumno.service.model.Alumno;
import com.scotiabank.alumno.service.repository.AlumnoRepository;
import com.scotiabank.alumno.service.service.AlumnoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public Mono<Void> guardarAlumno(AlumnoRequest alumnoRequest) {
         Alumno nuevo = Alumno.builder()
                                .nombre(alumnoRequest.getNombre())
                                .apellido(alumnoRequest.getApellido())
                                .estado(alumnoRequest.getEstado())
                                .edad(alumnoRequest.getEdad())
                                .build();

        return  alumnoRepository.save(nuevo).then();
                    }

        public Flux<AlumnoResponse> listarAlumnosActivos () {
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