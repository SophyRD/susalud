package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.CriteriosEvaluacionXItem;
import com.susalud.c1920c.repository.CriteriosEvaluacionXItemRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.CriteriosEvaluacionXItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CriteriosEvaluacionXItemResource {

    private final Logger log = LoggerFactory.getLogger(CriteriosEvaluacionXItemResource.class);

    private static final String ENTITY_NAME = "criteriosEvaluacionXItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CriteriosEvaluacionXItemRepository criteriosEvaluacionXItemRepository;

    public CriteriosEvaluacionXItemResource(CriteriosEvaluacionXItemRepository criteriosEvaluacionXItemRepository) {
        this.criteriosEvaluacionXItemRepository = criteriosEvaluacionXItemRepository;
    }

    /**
     * {@code POST  /criterios-evaluacion-x-items} : Create a new criteriosEvaluacionXItem.
     *
     * @param criteriosEvaluacionXItem the criteriosEvaluacionXItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new criteriosEvaluacionXItem, or with status {@code 400 (Bad Request)} if the criteriosEvaluacionXItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/criterios-evaluacion-x-items")
    public ResponseEntity<CriteriosEvaluacionXItem> createCriteriosEvaluacionXItem(@Valid @RequestBody CriteriosEvaluacionXItem criteriosEvaluacionXItem) throws URISyntaxException {
        log.debug("REST request to save CriteriosEvaluacionXItem : {}", criteriosEvaluacionXItem);
        if (criteriosEvaluacionXItem.getId() != null) {
            throw new BadRequestAlertException("A new criteriosEvaluacionXItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CriteriosEvaluacionXItem result = criteriosEvaluacionXItemRepository.save(criteriosEvaluacionXItem);
        return ResponseEntity.created(new URI("/api/criterios-evaluacion-x-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /criterios-evaluacion-x-items} : Updates an existing criteriosEvaluacionXItem.
     *
     * @param criteriosEvaluacionXItem the criteriosEvaluacionXItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated criteriosEvaluacionXItem,
     * or with status {@code 400 (Bad Request)} if the criteriosEvaluacionXItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the criteriosEvaluacionXItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/criterios-evaluacion-x-items")
    public ResponseEntity<CriteriosEvaluacionXItem> updateCriteriosEvaluacionXItem(@Valid @RequestBody CriteriosEvaluacionXItem criteriosEvaluacionXItem) throws URISyntaxException {
        log.debug("REST request to update CriteriosEvaluacionXItem : {}", criteriosEvaluacionXItem);
        if (criteriosEvaluacionXItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CriteriosEvaluacionXItem result = criteriosEvaluacionXItemRepository.save(criteriosEvaluacionXItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, criteriosEvaluacionXItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /criterios-evaluacion-x-items} : get all the criteriosEvaluacionXItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of criteriosEvaluacionXItems in body.
     */
    @GetMapping("/criterios-evaluacion-x-items")
    public List<CriteriosEvaluacionXItem> getAllCriteriosEvaluacionXItems() {
        log.debug("REST request to get all CriteriosEvaluacionXItems");
        return criteriosEvaluacionXItemRepository.findAll();
    }

    /**
     * {@code GET  /criterios-evaluacion-x-items/:id} : get the "id" criteriosEvaluacionXItem.
     *
     * @param id the id of the criteriosEvaluacionXItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the criteriosEvaluacionXItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/criterios-evaluacion-x-items/{id}")
    public ResponseEntity<CriteriosEvaluacionXItem> getCriteriosEvaluacionXItem(@PathVariable Long id) {
        log.debug("REST request to get CriteriosEvaluacionXItem : {}", id);
        Optional<CriteriosEvaluacionXItem> criteriosEvaluacionXItem = criteriosEvaluacionXItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(criteriosEvaluacionXItem);
    }

    /**
     * {@code DELETE  /criterios-evaluacion-x-items/:id} : delete the "id" criteriosEvaluacionXItem.
     *
     * @param id the id of the criteriosEvaluacionXItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/criterios-evaluacion-x-items/{id}")
    public ResponseEntity<Void> deleteCriteriosEvaluacionXItem(@PathVariable Long id) {
        log.debug("REST request to delete CriteriosEvaluacionXItem : {}", id);
        criteriosEvaluacionXItemRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
