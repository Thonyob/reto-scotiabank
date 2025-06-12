package com.scotiabank.alumno.service.repository;

import com.scotiabank.alumno.service.model.Alumno;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AlumnoRepository extends ReactiveCrudRepository<Alumno,Long> {
    Flux<Alumno> findByEstado(String estado);
}
