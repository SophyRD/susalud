package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Maestraverificador;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Maestraverificador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaestraverificadorRepository extends JpaRepository<Maestraverificador, Long> {
}
