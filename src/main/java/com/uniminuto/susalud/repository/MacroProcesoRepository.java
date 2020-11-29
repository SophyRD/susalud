package com.uniminuto.susalud.repository;

import com.uniminuto.susalud.domain.MacroProceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MacroProceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MacroProcesoRepository extends JpaRepository<MacroProceso, Long> {
}
