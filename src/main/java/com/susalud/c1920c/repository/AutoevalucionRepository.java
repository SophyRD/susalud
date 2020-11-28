package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Autoevalucion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Autoevalucion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AutoevalucionRepository extends JpaRepository<Autoevalucion, Long> {
}
