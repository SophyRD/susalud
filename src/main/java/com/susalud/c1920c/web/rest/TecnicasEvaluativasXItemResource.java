package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.TecnicasEvaluativasXItem;
import com.susalud.c1920c.repository.TecnicasEvaluativasXItemRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.TecnicasEvaluativasXItem}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TecnicasEvaluativasXItemResource {

    private final Logger log = LoggerFactory.getLogger(TecnicasEvaluativasXItemResource.class);

    private static final String ENTITY_NAME = "tecnicasEvaluativasXItem";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TecnicasEvaluativasXItemRepository tecnicasEvaluativasXItemRepository;

    public TecnicasEvaluativasXItemResource(TecnicasEvaluativasXItemRepository tecnicasEvaluativasXItemRepository) {
        this.tecnicasEvaluativasXItemRepository = tecnicasEvaluativasXItemRepository;
    }

    /**
     * {@code POST  /tecnicas-evaluativas-x-items} : Create a new tecnicasEvaluativasXItem.
     *
     * @param tecnicasEvaluativasXItem the tecnicasEvaluativasXItem to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tecnicasEvaluativasXItem, or with status {@code 400 (Bad Request)} if the tecnicasEvaluativasXItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tecnicas-evaluativas-x-items")
    public ResponseEntity<TecnicasEvaluativasXItem> createTecnicasEvaluativasXItem(@Valid @RequestBody TecnicasEvaluativasXItem tecnicasEvaluativasXItem) throws URISyntaxException {
        log.debug("REST request to save TecnicasEvaluativasXItem : {}", tecnicasEvaluativasXItem);
        if (tecnicasEvaluativasXItem.getId() != null) {
            throw new BadRequestAlertException("A new tecnicasEvaluativasXItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TecnicasEvaluativasXItem result = tecnicasEvaluativasXItemRepository.save(tecnicasEvaluativasXItem);
        return ResponseEntity.created(new URI("/api/tecnicas-evaluativas-x-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tecnicas-evaluativas-x-items} : Updates an existing tecnicasEvaluativasXItem.
     *
     * @param tecnicasEvaluativasXItem the tecnicasEvaluativasXItem to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecnicasEvaluativasXItem,
     * or with status {@code 400 (Bad Request)} if the tecnicasEvaluativasXItem is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tecnicasEvaluativasXItem couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tecnicas-evaluativas-x-items")
    public ResponseEntity<TecnicasEvaluativasXItem> updateTecnicasEvaluativasXItem(@Valid @RequestBody TecnicasEvaluativasXItem tecnicasEvaluativasXItem) throws URISyntaxException {
        log.debug("REST request to update TecnicasEvaluativasXItem : {}", tecnicasEvaluativasXItem);
        if (tecnicasEvaluativasXItem.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TecnicasEvaluativasXItem result = tecnicasEvaluativasXItemRepository.save(tecnicasEvaluativasXItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecnicasEvaluativasXItem.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tecnicas-evaluativas-x-items} : get all the tecnicasEvaluativasXItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tecnicasEvaluativasXItems in body.
     */
    @GetMapping("/tecnicas-evaluativas-x-items")
    public List<TecnicasEvaluativasXItem> getAllTecnicasEvaluativasXItems() {
        log.debug("REST request to get all TecnicasEvaluativasXItems");
        return tecnicasEvaluativasXItemRepository.findAll();
    }

    /**
     * {@code GET  /tecnicas-evaluativas-x-items/:id} : get the "id" tecnicasEvaluativasXItem.
     *
     * @param id the id of the tecnicasEvaluativasXItem to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tecnicasEvaluativasXItem, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tecnicas-evaluativas-x-items/{id}")
    public ResponseEntity<TecnicasEvaluativasXItem> getTecnicasEvaluativasXItem(@PathVariable Long id) {
        log.debug("REST request to get TecnicasEvaluativasXItem : {}", id);
        Optional<TecnicasEvaluativasXItem> tecnicasEvaluativasXItem = tecnicasEvaluativasXItemRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tecnicasEvaluativasXItem);
    }

    /**
     * {@code DELETE  /tecnicas-evaluativas-x-items/:id} : delete the "id" tecnicasEvaluativasXItem.
     *
     * @param id the id of the tecnicasEvaluativasXItem to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tecnicas-evaluativas-x-items/{id}")
    public ResponseEntity<Void> deleteTecnicasEvaluativasXItem(@PathVariable Long id) {
        log.debug("REST request to delete TecnicasEvaluativasXItem : {}", id);
        tecnicasEvaluativasXItemRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
