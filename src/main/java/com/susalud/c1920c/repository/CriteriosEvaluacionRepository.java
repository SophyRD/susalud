package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.CriteriosEvaluacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CriteriosEvaluacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CriteriosEvaluacionRepository extends JpaRepository<CriteriosEvaluacion, Long> {
}
