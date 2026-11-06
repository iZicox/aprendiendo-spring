package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicationDto;
import com.spring.relaciones_1.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicationService {
    // crear
    ApplicationDto createApplication(ApplicationDto applicationDto, Long applicantId);
    // listar
    Page<ApplicationDto> findAll(Pageable pageable);
    // listar por applicant
    Page<ApplicationDto> findByApplicant(Pageable pageable, Long applicantId);
    // optener por id
    ApplicationDto findById(Long id);
    // editar completo PUT
    ApplicationDto updateApplication(ApplicationDto applicationDto, Long applicationId);
    // editar con PATCH
    ApplicationDto patchApplication(ApplicationDto applicationDto, Long applicantId);
    // eliminar
    void deleteApplication(Long id);
}
