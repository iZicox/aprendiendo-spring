package com.spring.relaciones_1.repository;

import com.spring.relaciones_1.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByApplicantId(Long applicantId, Pageable pageable);
}
