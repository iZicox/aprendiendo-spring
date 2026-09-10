package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicantDto;

import java.util.List;

public interface ApplicantService {
    // crear
    ApplicantDto createAplicant(ApplicantDto applicantDto);
    // listar
    List<ApplicantDto> findAlll();
    // obtener por id
    ApplicantDto findById(Long id);
    //borrar
    void deleteAplicantById(Long id);
    // editar
    ApplicantDto updateAplicant(ApplicantDto applicantDto);
}
