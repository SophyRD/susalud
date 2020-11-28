package com.susalud.c1920c.repository;

import com.susalud.c1920c.domain.TecnicasEvaluativasXItem;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TecnicasEvaluativasXItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TecnicasEvaluativasXItemRepository extends JpaRepository<TecnicasEvaluativasXItem, Long> {
}
