package com.spring.relaciones_1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.relaciones_1.model.Application;
import jakarta.validation.constraints.NotBlank;

public record ApplicationDto(
        Long id,
        @NotBlank String position,
        @NotBlank String status,
        ApplicantSummary applicantSummary
) {

    public static ApplicationDto getDto(Application application) {
        if (application == null) {return null;}
        return new ApplicationDto(
                application.getId(),
                application.getPosition(),
                application.getStatus(),
                ApplicantSummary.from(application.getApplicant())
        );

    }

    @JsonIgnore
    public Application getEntity() {
        return new Application(
                this.id,
                this.status,
                this.position,
                null
        );
    }

    public void updateEntity(Application application) {
        application.setPosition(this.position);
        application.setStatus(this.status);
    }


}
