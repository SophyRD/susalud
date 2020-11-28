package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.CriteriosEvaluacion;
import com.susalud.c1920c.repository.CriteriosEvaluacionRepository;
import com.susalud.c1920c.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.susalud.c1920c.domain.CriteriosEvaluacion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CriteriosEvaluacionResource {

    private final Logger log = LoggerFactory.getLogger(CriteriosEvaluacionResource.class);

    private static final String ENTITY_NAME = "criteriosEvaluacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CriteriosEvaluacionRepository criteriosEvaluacionRepository;

    public CriteriosEvaluacionResource(CriteriosEvaluacionRepository criteriosEvaluacionRepository) {
        this.criteriosEvaluacionRepository = criteriosEvaluacionRepository;
    }

    /**
     * {@code POST  /criterios-evaluacions} : Create a new criteriosEvaluacion.
     *
     * @param criteriosEvaluacion the criteriosEvaluacion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new criteriosEvaluacion, or with status {@code 400 (Bad Request)} if the criteriosEvaluacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/criterios-evaluacions")
    public ResponseEntity<CriteriosEvaluacion> createCriteriosEvaluacion(@Valid @RequestBody CriteriosEvaluacion criteriosEvaluacion) throws URISyntaxException {
        log.debug("REST request to save CriteriosEvaluacion : {}", criteriosEvaluacion);
        if (criteriosEvaluacion.getId() != null) {
            throw new BadRequestAlertException("A new criteriosEvaluacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CriteriosEvaluacion result = criteriosEvaluacionRepository.save(criteriosEvaluacion);
        return ResponseEntity.created(new URI("/api/criterios-evaluacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /criterios-evaluacions} : Updates an existing criteriosEvaluacion.
     *
     * @param criteriosEvaluacion the criteriosEvaluacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criteriosEvaluacion,
     * or with status {@code 400 (Bad Request)} if the criteriosEvaluacion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the criteriosEvaluacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/criterios-evaluacions")
    public ResponseEntity<CriteriosEvaluacion> updateCriteriosEvaluacion(@Valid @RequestBody CriteriosEvaluacion criteriosEvaluacion) throws URISyntaxException {
        log.debug("REST request to update CriteriosEvaluacion : {}", criteriosEvaluacion);
        if (criteriosEvaluacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CriteriosEvaluacion result = criteriosEvaluacionRepository.save(criteriosEvaluacion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criteriosEvaluacion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /criterios-evaluacions} : get all the criteriosEvaluacions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of criteriosEvaluacions in body.
     */
    @GetMapping("/criterios-evaluacions")
    public List<CriteriosEvaluacion> getAllCriteriosEvaluacions() {
        log.debug("REST request to get all CriteriosEvaluacions");
        return criteriosEvaluacionRepository.findAll();
    }

    /**
     * {@code GET  /criterios-evaluacions/:id} : get the "id" criteriosEvaluacion.
     *
     * @param id the id of the criteriosEvaluacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the criteriosEvaluacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/criterios-evaluacions/{id}")
    public ResponseEntity<CriteriosEvaluacion> getCriteriosEvaluacion(@PathVariable Long id) {
        log.debug("REST request to get CriteriosEvaluacion : {}", id);
        Optional<CriteriosEvaluacion> criteriosEvaluacion = criteriosEvaluacionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(criteriosEvaluacion);
    }

    /**
     * {@code DELETE  /criterios-evaluacions/:id} : delete the "id" criteriosEvaluacion.
     *
     * @param id the id of the criteriosEvaluacion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/criterios-evaluacions/{id}")
    public ResponseEntity<Void> deleteCriteriosEvaluacion(@PathVariable Long id) {
        log.debug("REST request to delete CriteriosEvaluacion : {}", id);
        criteriosEvaluacionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
