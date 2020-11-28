package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Subproceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Subproceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubprocesoRepository extends JpaRepository<Subproceso, Long> {
}
