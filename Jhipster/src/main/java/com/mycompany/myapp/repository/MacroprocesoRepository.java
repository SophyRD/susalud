package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Macroproceso;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Macroproceso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MacroprocesoRepository extends JpaRepository<Macroproceso, Long> {
}
