package com.spring.relaciones_1.dto;

import com.spring.relaciones_1.model.Applicant;

public record ApplicantSummary(
        Long id,
        String name
) {
    public static ApplicantSummary from(Applicant applicant) {
        if(applicant == null){
            return null;
        }
        return new  ApplicantSummary(applicant.getId(), applicant.getName());
    }
}
