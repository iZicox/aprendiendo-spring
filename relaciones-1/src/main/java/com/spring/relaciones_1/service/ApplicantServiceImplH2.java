package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicantDto;
import com.spring.relaciones_1.model.Applicant;
import com.spring.relaciones_1.repository.ApplicantRepository;

import java.util.List;

public class ApplicantServiceImplH2 implements ApplicantService {

    private final ApplicantRepository repository;

    public ApplicantServiceImplH2(ApplicantRepository repository) {
        this.repository = repository;
    }


    @Override
    public ApplicantDto createAplicant(ApplicantDto applicantDto) {
        Applicant applicant = repository.save(applicantDto.getEntity());
        return ApplicantDto.getDto(applicant);
    }

    @Override
    public List<ApplicantDto> findAlll() {

        return repository.findAll().stream()
                .map(ApplicantDto::getDto)
                .toList();
    }

    @Override
    public ApplicantDto findById(Long id) {

        return ApplicantDto.getDto(repository.findById(id)
                .orElseThrow(
                () -> {throw new RuntimeException("Applicant not found with id " + id);}
        ));
    }

    @Override
    public void deleteAplicantById(Long id) {

    }

    @Override
    public ApplicantDto updateAplicant(ApplicantDto applicantDto) {
        return null;
    }
}
