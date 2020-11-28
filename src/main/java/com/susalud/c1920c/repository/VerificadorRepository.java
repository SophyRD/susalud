package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Verificador;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Verificador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerificadorRepository extends JpaRepository<Verificador, Long> {
}
