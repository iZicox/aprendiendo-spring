package com.spring.relaciones_1.repository;

import com.spring.relaciones_1.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
}
