package com.scotiabank.alumno.service.controller;

import com.scotiabank.alumno.service.dto.AlumnoRequest;
import com.scotiabank.alumno.service.dto.AlumnoResponse;
import com.scotiabank.alumno.service.service.AlumnoService;
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
class AlumnoControllerTest {

    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    private AlumnoRequest request;
    private AlumnoResponse response;

    @BeforeEach
    void setUp() {
        request = new AlumnoRequest();
        response = new AlumnoResponse();
    }

    @Test
    void registrarAlumno() {
        when(alumnoService.guardarAlumno(any(AlumnoRequest.class)))
                .thenReturn(Mono.empty());

        Mono<Void> result = alumnoController.registrarAlumno(request);

        StepVerifier.create(result)
                .verifyComplete();

        verify(alumnoService, times(1)).guardarAlumno(any(AlumnoRequest.class));
    }

    @Test
    void listarAlumnosActivos() {
        when(alumnoService.listarAlumnosActivos())
                .thenReturn(Flux.fromIterable(List.of(response)));

        Flux<AlumnoResponse> result = alumnoController.listarAlumnosActivos();

        StepVerifier.create(result)
                .expectNext(response)
                .verifyComplete();

        verify(alumnoService, times(1)).listarAlumnosActivos();
    }
}