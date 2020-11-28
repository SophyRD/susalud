package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.CriteriosEvaluacionXItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CriteriosEvaluacionXItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CriteriosEvaluacionXItemRepository extends JpaRepository<CriteriosEvaluacionXItem, Long> {
}
