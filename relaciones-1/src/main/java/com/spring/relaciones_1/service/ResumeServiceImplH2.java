package com.spring.relaciones_1.service;

import com.spring.relaciones_1.dto.ResumeDto;
import com.spring.relaciones_1.exception.ApplicantNotFound;
import com.spring.relaciones_1.exception.ResumeNotFound;
import com.spring.relaciones_1.model.Applicant;
import com.spring.relaciones_1.model.Resume;
import com.spring.relaciones_1.repository.ApplicantRepository;
import com.spring.relaciones_1.repository.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ResumeServiceImplH2 implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final ApplicantRepository applicantRepository;

    public ResumeServiceImplH2(ResumeRepository resumeRepository,
                               ApplicantRepository applicantRepository) {
        this.resumeRepository = resumeRepository;
        this.applicantRepository = applicantRepository;
    }

    /**
     * crear resume
     * @param resumeDto
     * @param applicantId
     * @return
     */
    @Override
    @Transactional
    public ResumeDto createResume(ResumeDto resumeDto, Long applicantId) {

        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() -> new ApplicantNotFound("applicant " + applicantId + " not fount"));
        Resume resume = resumeDto.getEntity();
        resume.setApplicant(applicant);
        Resume savedResume = resumeRepository.save(resume);

        return ResumeDto.getDto(savedResume);

    }

    /**
     * listar por applicant
     * @param applicantId
     * @return
     */
    @Override
    public ResumeDto findByApplicant(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId).orElseThrow(() -> new ApplicantNotFound("applicant " + applicantId + " not fount"));
        Resume resume = resumeRepository.findByApplicant(applicant);
        if (resume == null) {
            throw new ResumeNotFound("resume of applicant id " + applicantId + " not fount");
        }
        return ResumeDto.getDto(resume);
    }

    /**
     * eliminar por id del applicant
     * @param id del applicant
     */
    @Override
    @Transactional
    public void deleteResumeByApplicantId(Long id) {
        Applicant applicant = applicantRepository.findById(id)
                .orElseThrow(() -> new ApplicantNotFound("applicant " + id + " not fount"));
        if(applicant.getResume() == null){
            throw new ResumeNotFound("Resume not found");
        }
        applicant.setResume(null); // esto elimina el resume porque usa el orphan
    }

    /**
     * actualizar
     * @param resumeDto
     * @param id del resume
     * @return
     */
    @Override
    @Transactional
    public ResumeDto updateResume(ResumeDto resumeDto, Long id) {
        Resume exist = resumeRepository.findById(id).orElseThrow(() -> new ResumeNotFound("Resume not found"));
        resumeDto.updateEntity(exist);
        return ResumeDto.getDto(exist);
    }
}
