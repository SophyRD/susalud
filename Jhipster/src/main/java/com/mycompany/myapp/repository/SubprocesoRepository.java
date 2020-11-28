package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Subproceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Subproceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubprocesoRepository extends JpaRepository<Subproceso, Long> {
}
