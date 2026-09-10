package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicantDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantService {
    // crear
    ApplicantDto createAplicant(ApplicantDto applicantDto);
    // listar
    Page<ApplicantDto> findAll(Pageable pageable);
    // obtener por id
    ApplicantDto findById(Long id);
    //borrar
    void deleteAplicantById(Long id);
    // editar
    ApplicantDto updateAplicant(ApplicantDto applicantDto, Long id);
}
