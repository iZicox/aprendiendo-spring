package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ResumeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResumeService {
    // crear
    ResumeDto createResume(ResumeDto resumeDto,  Long applicantId);
    // listar por id del applicant
    ResumeDto findByApplicant(Long applicantId);
    //borrar
    void deleteResumeByApplicantId(Long id);
    // editar
    ResumeDto updateResume(ResumeDto resumeDto, Long id);
}
