package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.AutoevalucionXproceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AutoevalucionXproceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutoevalucionXprocesoRepository extends JpaRepository<AutoevalucionXproceso, Long> {
}
