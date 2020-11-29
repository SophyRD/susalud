package com.uniminuto.susalud.web.rest;

import com.uniminuto.susalud.domain.SubProceso;
import com.uniminuto.susalud.repository.SubProcesoRepository;
import com.uniminuto.susalud.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.uniminuto.susalud.domain.SubProceso}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SubProcesoResource {

    private final Logger log = LoggerFactory.getLogger(SubProcesoResource.class);

    private static final String ENTITY_NAME = "subProceso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SubProcesoRepository subProcesoRepository;

    public SubProcesoResource(SubProcesoRepository subProcesoRepository) {
        this.subProcesoRepository = subProcesoRepository;
    }

    /**
     * {@code POST  /sub-procesos} : Create a new subProceso.
     *
     * @param subProceso the subProceso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new subProceso, or with status {@code 400 (Bad Request)} if the subProceso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sub-procesos")
    public ResponseEntity<SubProceso> createSubProceso(@RequestBody SubProceso subProceso) throws URISyntaxException {
        log.debug("REST request to save SubProceso : {}", subProceso);
        if (subProceso.getId() != null) {
            throw new BadRequestAlertException("A new subProceso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubProceso result = subProcesoRepository.save(subProceso);
        return ResponseEntity.created(new URI("/api/sub-procesos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sub-procesos} : Updates an existing subProceso.
     *
     * @param subProceso the subProceso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated subProceso,
     * or with status {@code 400 (Bad Request)} if the subProceso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the subProceso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sub-procesos")
    public ResponseEntity<SubProceso> updateSubProceso(@RequestBody SubProceso subProceso) throws URISyntaxException {
        log.debug("REST request to update SubProceso : {}", subProceso);
        if (subProceso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubProceso result = subProcesoRepository.save(subProceso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, subProceso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sub-procesos} : get all the subProcesos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of subProcesos in body.
     */
    @GetMapping("/sub-procesos")
    public List<SubProceso> getAllSubProcesos() {
        log.debug("REST request to get all SubProcesos");
        return subProcesoRepository.findAll();
    }

    /**
     * {@code GET  /sub-procesos/:id} : get the "id" subProceso.
     *
     * @param id the id of the subProceso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the subProceso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sub-procesos/{id}")
    public ResponseEntity<SubProceso> getSubProceso(@PathVariable Long id) {
        log.debug("REST request to get SubProceso : {}", id);
        Optional<SubProceso> subProceso = subProcesoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(subProceso);
    }

    /**
     * {@code DELETE  /sub-procesos/:id} : delete the "id" subProceso.
     *
     * @param id the id of the subProceso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sub-procesos/{id}")
    public ResponseEntity<Void> deleteSubProceso(@PathVariable Long id) {
        log.debug("REST request to delete SubProceso : {}", id);
        subProcesoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
