package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Proceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Proceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
}
