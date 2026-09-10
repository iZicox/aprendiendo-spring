package com.spring.relaciones_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.relaciones_1.model.Applicant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ApplicantDto(
        Long id,
        @NotBlank String name,
        @NotBlank String phone,
        @NotBlank @Email String email,
        String status,
        ResumeDto resumeDto
) {

    // entidad a dto
    public static ApplicantDto getDto(Applicant applicant) {
        if (applicant == null) {return null;}
        return new ApplicantDto(
                applicant.getId(),
                applicant.getName(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicant.getStatus(),
                ResumeDto.getDto(applicant.getResume())
        );
    }

    // dto a entidad
    @JsonIgnore
    public Applicant getEntity(){
        return Applicant.builder()
                .id(this.id)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .status(this.status)
                .build(); // ← NO llama a resumeDto.toEntity() — el Resume se crea por separado
    }

    // editar entidad con la informacion del dto
    public void updateEntity(Applicant applicant){
        applicant.setName(this.name);
        applicant.setPhone(this.phone);
        applicant.setEmail(this.email);
        applicant.setStatus(this.status);
        // NO toca resume — el Resume se maneja en el servicio por separado
    }
}
