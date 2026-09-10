package com.spring.relaciones_1.dto;

import com.spring.relaciones_1.model.Applicant;

public record ApplicantDto(
        Long id,
        String name,
        String phone,
        String email,
        String status
) {

    // entidad a dto
    public static ApplicantDto getDto(Applicant applicant) {
        return new ApplicantDto(
                applicant.getId(),
                applicant.getName(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicant.getStatus()
        );
    }

    // dto a entidad
    public Applicant getEntity(){
        return Applicant.builder()
                .id(this.id)
                .name(this.name)
                .phone(this.phone)
                .email(this.email)
                .status(this.status)
                .build();
    }

    // editar entidad con la informacion del dto
    public void updateEntity(Applicant applicant){
        applicant.setName(this.name);
        applicant.setPhone(this.phone);
        applicant.setEmail(this.email);
        applicant.setStatus(this.status);
    }
}
