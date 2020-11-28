package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.Subproceso;
import com.susalud.c1920c.repository.SubprocesoRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.Subproceso}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SubprocesoResource {

    private final Logger log = LoggerFactory.getLogger(SubprocesoResource.class);

    private static final String ENTITY_NAME = "subproceso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubprocesoRepository subprocesoRepository;

    public SubprocesoResource(SubprocesoRepository subprocesoRepository) {
        this.subprocesoRepository = subprocesoRepository;
    }

    /**
     * {@code POST  /subprocesos} : Create a new subproceso.
     *
     * @param subproceso the subproceso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subproceso, or with status {@code 400 (Bad Request)} if the subproceso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/subprocesos")
    public ResponseEntity<Subproceso> createSubproceso(@Valid @RequestBody Subproceso subproceso) throws URISyntaxException {
        log.debug("REST request to save Subproceso : {}", subproceso);
        if (subproceso.getId() != null) {
            throw new BadRequestAlertException("A new subproceso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Subproceso result = subprocesoRepository.save(subproceso);
        return ResponseEntity.created(new URI("/api/subprocesos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /subprocesos} : Updates an existing subproceso.
     *
     * @param subproceso the subproceso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subproceso,
     * or with status {@code 400 (Bad Request)} if the subproceso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subproceso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/subprocesos")
    public ResponseEntity<Subproceso> updateSubproceso(@Valid @RequestBody Subproceso subproceso) throws URISyntaxException {
        log.debug("REST request to update Subproceso : {}", subproceso);
        if (subproceso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Subproceso result = subprocesoRepository.save(subproceso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, subproceso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /subprocesos} : get all the subprocesos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subprocesos in body.
     */
    @GetMapping("/subprocesos")
    public List<Subproceso> getAllSubprocesos() {
        log.debug("REST request to get all Subprocesos");
        return subprocesoRepository.findAll();
    }

    /**
     * {@code GET  /subprocesos/:id} : get the "id" subproceso.
     *
     * @param id the id of the subproceso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subproceso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/subprocesos/{id}")
    public ResponseEntity<Subproceso> getSubproceso(@PathVariable Long id) {
        log.debug("REST request to get Subproceso : {}", id);
        Optional<Subproceso> subproceso = subprocesoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(subproceso);
    }

    /**
     * {@code DELETE  /subprocesos/:id} : delete the "id" subproceso.
     *
     * @param id the id of the subproceso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/subprocesos/{id}")
    public ResponseEntity<Void> deleteSubproceso(@PathVariable Long id) {
        log.debug("REST request to delete Subproceso : {}", id);
        subprocesoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
