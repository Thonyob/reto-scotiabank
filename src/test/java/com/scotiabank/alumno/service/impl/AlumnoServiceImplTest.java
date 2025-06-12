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
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.core.ReactiveInsertOperation;
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

    @Mock
    private R2dbcEntityTemplate template;

    @Mock
    private ReactiveInsertOperation.ReactiveInsert<Alumno> reactiveInsert;

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
                .id(1L)
                .nombre("Juan")
                .apellido("Pérez")
                .estado("activo")
                .edad(20)
                .build();
    }

    @Test
    void guardarAlumnoIdExitste() {

        when(alumnoRepository.existsById(request.getId())).thenReturn(Mono.just(false));

        when(template.insert(Alumno.class)).thenReturn(reactiveInsert);
        when(reactiveInsert.using(any(Alumno.class))).thenReturn(Mono.just(alumno));

        Mono<Void> result = alumnoService.guardarAlumno(request);

        StepVerifier.create(result)
                .verifyComplete();

        verify(alumnoRepository).existsById(request.getId());
        verify(template).insert(Alumno.class);
        verify(reactiveInsert).using(any(Alumno.class));
    }

    @Test
    void guardarAlumnoIdNoExiste() {

        when(alumnoRepository.existsById(request.getId())).thenReturn(Mono.just(true));

        Mono<Void> result = alumnoService.guardarAlumno(request);

        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof RuntimeException &&
                                throwable.getMessage().equals("El ID ya está registrado"))
                .verify();

        verify(alumnoRepository).existsById(request.getId());
        verify(template, never()).insert(any());
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