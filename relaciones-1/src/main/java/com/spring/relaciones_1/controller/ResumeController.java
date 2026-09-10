package com.spring.relaciones_1.controller;

import com.spring.relaciones_1.dto.ApplicantDto;
import com.spring.relaciones_1.dto.ResumeDto;
import com.spring.relaciones_1.model.Resume;
import com.spring.relaciones_1.repository.ResumeRepository;
import com.spring.relaciones_1.service.ApplicantService;
import com.spring.relaciones_1.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/applicants/{applicantId}/resume")
public class ResumeController {
    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService
                            ) {
        this.resumeService = resumeService;
    }

    // consultar resume
    @GetMapping
    public ResponseEntity<ResumeDto> getResume(@PathVariable Long applicantId) {
        ResumeDto resumeDto = resumeService.findByApplicant(applicantId);
        return ResponseEntity.ok(resumeDto);
    }

    // crear resume
    @PostMapping
    public ResponseEntity<ResumeDto> saveResume(@PathVariable Long applicantId, @RequestBody ResumeDto resumeDto) {
        ResumeDto resumeDtoNew = resumeService.createResume(resumeDto, applicantId);
        return ResponseEntity.ok(resumeDtoNew);
    }

    // eliminar
    @DeleteMapping
    public ResponseEntity<Void> deleteResume(@PathVariable Long applicantId) {
        resumeService.deleteResumeByApplicantId(applicantId);
        return ResponseEntity.noContent().build();
    }
}
