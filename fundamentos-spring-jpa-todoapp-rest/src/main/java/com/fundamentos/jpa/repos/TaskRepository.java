package com.fundamentos.jpa.repos;

import com.fundamentos.jpa.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
        select t from Task t
        left join fetch t.items
        left join fetch t.tags
    """)
    Page<Task> findAllWithItemsAndTags(Pageable pageable);

    @Query("""
        select t from Task t
            left join fetch t.items
            left join fetch t.tags
        where t.id = :id
        """)
    Optional<Task> findByIdWithItemsAndTags(Long id);

    @Query("""
        select case when count(t) > 0 then true else false end
        from Task t
        where t.id = :id and type(t) = :type
        """)
    boolean existsByIdAndTaskType(@Param("type") Class taskType, Long id);



}
