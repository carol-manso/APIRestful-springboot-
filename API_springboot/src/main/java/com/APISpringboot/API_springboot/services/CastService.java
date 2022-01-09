package com.APISpringboot.API_springboot.services;

import com.APISpringboot.API_springboot.entities.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface CastService {
    Page<Cast> findByEmployeeId(Long employeeId, PageRequest pageRequest);
    Optional<Cast> findById(Long id);
    Cast persist(Cast cast);
    void remove (Long id);

}
