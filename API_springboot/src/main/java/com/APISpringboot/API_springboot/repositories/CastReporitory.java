package com.APISpringboot.API_springboot.repositories;

import com.APISpringboot.API_springboot.entities.Cast;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional()
@NamedQueries({
        @NamedQuery(name = "CastRepository.findByEmployeeId",
                query = "SELECT cast FROM cast WHERE cast.employee.id = :employeeId ")
})
public interface CastReporitory extends JpaRepository<Cast, Long> {
    List<Cast> findByEmployeeId(@Param("employeeId") Long employeeId); //spring não consegue criar a query por convenção. Quero os lançamentos para um determinado funcionário, o que está associado a aoutra entity.
    Page<Cast> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
}
