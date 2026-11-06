package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ApplicationDto;
import com.spring.relaciones_1.exception.ApplicantNotFound;
import com.spring.relaciones_1.model.Applicant;
import com.spring.relaciones_1.model.Application;
import com.spring.relaciones_1.repository.ApplicantRepository;
import com.spring.relaciones_1.repository.ApplicationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ApplicationServiceImplH2 implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplicantRepository applicantRepository;

    public ApplicationServiceImplH2(ApplicationRepository applicationRepository,  ApplicantRepository applicantRepository) {
        this.applicationRepository = applicationRepository;
        this.applicantRepository = applicantRepository;
    }

    /**
     * crear
     * @param applicationDto
     * @param applicantId
     * @return
     */
    @Transactional
    @Override
    public ApplicationDto createApplication(ApplicationDto applicationDto, Long applicantId) {
        Application newApplication = applicationRepository.save(applicationDto.getEntity());
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new RuntimeException("applicant " + applicantId + " not fount"));
        newApplication.setApplicant(applicant);
        return ApplicationDto.getDto(newApplication);
    }

    /**
     * listar
     * @param pageable
     * @return
     */
    @Override
    public Page<ApplicationDto> findAll(Pageable pageable) {
        return applicationRepository.findAll(pageable)
                .map(ApplicationDto::getDto);
    }

    /**
     * listar la application por medio del id del applicant
     * @param applicantId
     * @return
     */
    @Override
    public Page<ApplicationDto> findByApplicant(Pageable pageable, Long applicantId) {

        if(applicantRepository.existsById(applicantId)){ // revisa que exista el applicant
            return applicationRepository.findByApplicantId(applicantId, pageable) // crear la lista con pagable
                    .map(ApplicationDto::getDto);
        }else{
            throw new ApplicantNotFound("Applicant not found with id " + applicantId);
        }

    }

    /**
     * listar por id de application
     * @param id
     * @return
     */
    @Override
    public ApplicationDto findById(Long id) {

        return applicationRepository.findById(id)
                .map(ApplicationDto::getDto)
                .orElseThrow(() -> new RuntimeException("Application not found with id " + id));
    }

    /**
     *
     * @param applicationDto
     * @param applicationId
     * @return
     */
    @Override
    public ApplicationDto updateApplication(ApplicationDto applicationDto, Long applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found with id " + applicationId));
        applicationDto.updateEntity(application);
        return ApplicationDto.getDto(application);
    }

    @Override
    public ApplicationDto patchApplication(ApplicationDto applicationDto, Long applicantId) {
        return null;
    }

    /**
     * eliminar
     * @param id
     */
    @Override
    public void deleteApplication(Long id) {
        if(applicantRepository.existsById(id)){
            applicationRepository.deleteById(id);
        }
    }
}
