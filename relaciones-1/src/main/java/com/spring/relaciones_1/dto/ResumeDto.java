package com.spring.relaciones_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.relaciones_1.model.Resume;
import jakarta.validation.constraints.NotBlank;


public record ResumeDto(
        Long id,
        @NotBlank String content,
        ApplicantSummary applicantSummary
) {

    // entity to dto
    public static ResumeDto getDto(Resume resume) {
        if (resume == null) { return null; }
        return new ResumeDto(
                resume.getId(),
                resume.getContent(),
                ApplicantSummary.from(resume.getApplicant())
        );
    }

    // dto to entity
    @JsonIgnore
    public Resume getEntity() {
        // NO convierte applicantSummary a entidad
        // El vinculo se establece en el servicio con: resume.setApplicant(applicant)
        return Resume.builder()
                .id(this.id)
                .content(this.content)
                .build();
    }

    // update entity
    public void updateEntity(Resume resume) {
        resume.setContent(this.content);
    }
}
