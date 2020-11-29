package com.uniminuto.susalud.repository;

import com.uniminuto.susalud.domain.Proceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Proceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
}
