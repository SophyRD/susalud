package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.UsuariosXevaluacion;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UsuariosXevaluacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuariosXevaluacionRepository extends JpaRepository<UsuariosXevaluacion, Long> {
}
