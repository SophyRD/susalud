package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Usuarios;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Usuarios entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
