package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Remove;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Remove entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RemoveRepository extends JpaRepository<Remove, Long> {
}
