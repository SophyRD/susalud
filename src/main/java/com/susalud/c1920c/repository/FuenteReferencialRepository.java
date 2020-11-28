package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.FuenteReferencial;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FuenteReferencial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuenteReferencialRepository extends JpaRepository<FuenteReferencial, Long> {
}
