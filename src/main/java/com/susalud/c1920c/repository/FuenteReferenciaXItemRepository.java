package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.FuenteReferenciaXItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FuenteReferenciaXItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuenteReferenciaXItemRepository extends JpaRepository<FuenteReferenciaXItem, Long> {
}
