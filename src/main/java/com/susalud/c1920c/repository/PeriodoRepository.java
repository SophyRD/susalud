package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Periodo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Periodo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodoRepository extends JpaRepository<Periodo, Long> {
}
