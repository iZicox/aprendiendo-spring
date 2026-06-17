package com.fundamentos.jpa.repos;

import com.fundamentos.jpa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
