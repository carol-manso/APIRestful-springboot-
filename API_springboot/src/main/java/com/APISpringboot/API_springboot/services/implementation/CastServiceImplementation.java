package com.APISpringboot.API_springboot.services.implementation;

import com.APISpringboot.API_springboot.entities.Cast;
import com.APISpringboot.API_springboot.repositories.CastRepository;
import com.APISpringboot.API_springboot.services.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CastServiceImplementation implements CastService {

    @Autowired
    private CastRepository castRepository;
    @Override
    public Page<Cast> findByEmployeeId(Long employeeId, PageRequest pageRequest) {
        return castRepository.findByEmployeeId(employeeId, pageRequest);

    }

    @Override
    public Optional<Cast> findById(Long id) {
        return castRepository.findById(id);
    }

    @Override
    public Cast persist(Cast cast) {
        return castRepository.save(cast);
    }
    @Override
    public void remove(Long id) {
        castRepository.deleteById(id);
    }
}
