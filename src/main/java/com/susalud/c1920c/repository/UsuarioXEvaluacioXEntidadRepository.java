package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.UsuarioXEvaluacioXEntidad;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UsuarioXEvaluacioXEntidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuarioXEvaluacioXEntidadRepository extends JpaRepository<UsuarioXEvaluacioXEntidad, Long> {
}
