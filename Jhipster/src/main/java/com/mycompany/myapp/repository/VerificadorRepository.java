package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Verificador;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Verificador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VerificadorRepository extends JpaRepository<Verificador, Long> {
}
