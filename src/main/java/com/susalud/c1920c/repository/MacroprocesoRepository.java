package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Macroproceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Macroproceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MacroprocesoRepository extends JpaRepository<Macroproceso, Long> {
}
