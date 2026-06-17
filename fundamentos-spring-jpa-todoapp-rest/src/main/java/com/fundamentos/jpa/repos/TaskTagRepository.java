package com.fundamentos.jpa.repos;

import com.fundamentos.jpa.model.TaskTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTagRepository extends JpaRepository<TaskTag, Long> {
}
