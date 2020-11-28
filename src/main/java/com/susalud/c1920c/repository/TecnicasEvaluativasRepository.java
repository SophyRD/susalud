package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.TecnicasEvaluativas;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TecnicasEvaluativas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TecnicasEvaluativasRepository extends JpaRepository<TecnicasEvaluativas, Long> {
}
