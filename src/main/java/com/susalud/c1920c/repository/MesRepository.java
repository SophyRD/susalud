package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.Mes;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Mes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MesRepository extends JpaRepository<Mes, Long> {
}
