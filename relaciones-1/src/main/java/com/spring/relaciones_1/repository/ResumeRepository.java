package com.spring.relaciones_1.repository;

import com.spring.relaciones_1.model.Applicant;
import com.spring.relaciones_1.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Resume findByApplicant(Applicant applicant);
    Resume findById(long id);
}
