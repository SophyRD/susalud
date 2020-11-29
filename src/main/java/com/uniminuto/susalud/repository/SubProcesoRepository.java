package com.uniminuto.susalud.repository;

import com.uniminuto.susalud.domain.SubProceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SubProceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubProcesoRepository extends JpaRepository<SubProceso, Long> {
}
