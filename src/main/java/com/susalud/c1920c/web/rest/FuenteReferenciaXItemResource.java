package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.FuenteReferenciaXItem;
import com.susalud.c1920c.repository.FuenteReferenciaXItemRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.FuenteReferenciaXItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FuenteReferenciaXItemResource {

    private final Logger log = LoggerFactory.getLogger(FuenteReferenciaXItemResource.class);

    private static final String ENTITY_NAME = "fuenteReferenciaXItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FuenteReferenciaXItemRepository fuenteReferenciaXItemRepository;

    public FuenteReferenciaXItemResource(FuenteReferenciaXItemRepository fuenteReferenciaXItemRepository) {
        this.fuenteReferenciaXItemRepository = fuenteReferenciaXItemRepository;
    }

    /**
     * {@code POST  /fuente-referencia-x-items} : Create a new fuenteReferenciaXItem.
     *
     * @param fuenteReferenciaXItem the fuenteReferenciaXItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fuenteReferenciaXItem, or with status {@code 400 (Bad Request)} if the fuenteReferenciaXItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fuente-referencia-x-items")
    public ResponseEntity<FuenteReferenciaXItem> createFuenteReferenciaXItem(@Valid @RequestBody FuenteReferenciaXItem fuenteReferenciaXItem) throws URISyntaxException {
        log.debug("REST request to save FuenteReferenciaXItem : {}", fuenteReferenciaXItem);
        if (fuenteReferenciaXItem.getId() != null) {
            throw new BadRequestAlertException("A new fuenteReferenciaXItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FuenteReferenciaXItem result = fuenteReferenciaXItemRepository.save(fuenteReferenciaXItem);
        return ResponseEntity.created(new URI("/api/fuente-referencia-x-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fuente-referencia-x-items} : Updates an existing fuenteReferenciaXItem.
     *
     * @param fuenteReferenciaXItem the fuenteReferenciaXItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fuenteReferenciaXItem,
     * or with status {@code 400 (Bad Request)} if the fuenteReferenciaXItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fuenteReferenciaXItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fuente-referencia-x-items")
    public ResponseEntity<FuenteReferenciaXItem> updateFuenteReferenciaXItem(@Valid @RequestBody FuenteReferenciaXItem fuenteReferenciaXItem) throws URISyntaxException {
        log.debug("REST request to update FuenteReferenciaXItem : {}", fuenteReferenciaXItem);
        if (fuenteReferenciaXItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FuenteReferenciaXItem result = fuenteReferenciaXItemRepository.save(fuenteReferenciaXItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fuenteReferenciaXItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fuente-referencia-x-items} : get all the fuenteReferenciaXItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fuenteReferenciaXItems in body.
     */
    @GetMapping("/fuente-referencia-x-items")
    public List<FuenteReferenciaXItem> getAllFuenteReferenciaXItems() {
        log.debug("REST request to get all FuenteReferenciaXItems");
        return fuenteReferenciaXItemRepository.findAll();
    }

    /**
     * {@code GET  /fuente-referencia-x-items/:id} : get the "id" fuenteReferenciaXItem.
     *
     * @param id the id of the fuenteReferenciaXItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fuenteReferenciaXItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fuente-referencia-x-items/{id}")
    public ResponseEntity<FuenteReferenciaXItem> getFuenteReferenciaXItem(@PathVariable Long id) {
        log.debug("REST request to get FuenteReferenciaXItem : {}", id);
        Optional<FuenteReferenciaXItem> fuenteReferenciaXItem = fuenteReferenciaXItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fuenteReferenciaXItem);
    }

    /**
     * {@code DELETE  /fuente-referencia-x-items/:id} : delete the "id" fuenteReferenciaXItem.
     *
     * @param id the id of the fuenteReferenciaXItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fuente-referencia-x-items/{id}")
    public ResponseEntity<Void> deleteFuenteReferenciaXItem(@PathVariable Long id) {
        log.debug("REST request to delete FuenteReferenciaXItem : {}", id);
        fuenteReferenciaXItemRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
