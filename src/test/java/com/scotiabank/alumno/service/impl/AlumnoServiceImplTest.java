package com.scotiabank.alumno.service.impl;

import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.model.Alumno;
import com.scotiabank.alumno.service.repository.AlumnoRepository;
import com.scotiabank.alumno.service.service.impl.AlumnoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlumnoServiceImplTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Alumno alumno;
    private AlumnoRequest request;

    @BeforeEach
    void setUp() {
        request = AlumnoRequest.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .estado("activo")
                .edad(20)
                .build();

        alumno = Alumno.builder()
                .id("1")
                .nombre("Juan")
                .apellido("Pérez")
                .estado("activo")
                .edad(20)
                .build();
    }

    @Test
    void guardarAlumno() {

        when(alumnoRepository.save(any(Alumno.class)))
                .thenReturn(Mono.just(alumno));

        Mono<Void> result = alumnoService.guardarAlumno(request);

        StepVerifier.create(result)
                .verifyComplete();

        verify(alumnoRepository, times(1)).save(any(Alumno.class));

    }

    @Test
    void listarAlumnosActivos() {

        when(alumnoRepository.findByEstado("activo"))
                .thenReturn(Flux.fromIterable(List.of(alumno)));

        StepVerifier.create(alumnoService.listarAlumnosActivos())
                .expectNextMatches(response ->
                        response.getNombre().equals("Juan") &&
                                response.getApellido().equals("Pérez") &&
                                response.getEstado().equals("activo") &&
                                response.getEdad() == 20)
                .verifyComplete();

        verify(alumnoRepository, times(1)).findByEstado("activo");

    }
}