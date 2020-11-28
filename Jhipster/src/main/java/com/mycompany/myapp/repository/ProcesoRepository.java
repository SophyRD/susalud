package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Proceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Proceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcesoRepository extends JpaRepository<Proceso, Long> {
}
