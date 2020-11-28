package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.TecnicasEvaluativas;
import com.susalud.c1920c.repository.TecnicasEvaluativasRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.TecnicasEvaluativas}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TecnicasEvaluativasResource {

    private final Logger log = LoggerFactory.getLogger(TecnicasEvaluativasResource.class);

    private static final String ENTITY_NAME = "tecnicasEvaluativas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TecnicasEvaluativasRepository tecnicasEvaluativasRepository;

    public TecnicasEvaluativasResource(TecnicasEvaluativasRepository tecnicasEvaluativasRepository) {
        this.tecnicasEvaluativasRepository = tecnicasEvaluativasRepository;
    }

    /**
     * {@code POST  /tecnicas-evaluativas} : Create a new tecnicasEvaluativas.
     *
     * @param tecnicasEvaluativas the tecnicasEvaluativas to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tecnicasEvaluativas, or with status {@code 400 (Bad Request)} if the tecnicasEvaluativas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tecnicas-evaluativas")
    public ResponseEntity<TecnicasEvaluativas> createTecnicasEvaluativas(@Valid @RequestBody TecnicasEvaluativas tecnicasEvaluativas) throws URISyntaxException {
        log.debug("REST request to save TecnicasEvaluativas : {}", tecnicasEvaluativas);
        if (tecnicasEvaluativas.getId() != null) {
            throw new BadRequestAlertException("A new tecnicasEvaluativas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TecnicasEvaluativas result = tecnicasEvaluativasRepository.save(tecnicasEvaluativas);
        return ResponseEntity.created(new URI("/api/tecnicas-evaluativas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tecnicas-evaluativas} : Updates an existing tecnicasEvaluativas.
     *
     * @param tecnicasEvaluativas the tecnicasEvaluativas to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tecnicasEvaluativas,
     * or with status {@code 400 (Bad Request)} if the tecnicasEvaluativas is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tecnicasEvaluativas couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tecnicas-evaluativas")
    public ResponseEntity<TecnicasEvaluativas> updateTecnicasEvaluativas(@Valid @RequestBody TecnicasEvaluativas tecnicasEvaluativas) throws URISyntaxException {
        log.debug("REST request to update TecnicasEvaluativas : {}", tecnicasEvaluativas);
        if (tecnicasEvaluativas.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TecnicasEvaluativas result = tecnicasEvaluativasRepository.save(tecnicasEvaluativas);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tecnicasEvaluativas.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tecnicas-evaluativas} : get all the tecnicasEvaluativas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tecnicasEvaluativas in body.
     */
    @GetMapping("/tecnicas-evaluativas")
    public List<TecnicasEvaluativas> getAllTecnicasEvaluativas() {
        log.debug("REST request to get all TecnicasEvaluativas");
        return tecnicasEvaluativasRepository.findAll();
    }

    /**
     * {@code GET  /tecnicas-evaluativas/:id} : get the "id" tecnicasEvaluativas.
     *
     * @param id the id of the tecnicasEvaluativas to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tecnicasEvaluativas, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tecnicas-evaluativas/{id}")
    public ResponseEntity<TecnicasEvaluativas> getTecnicasEvaluativas(@PathVariable Long id) {
        log.debug("REST request to get TecnicasEvaluativas : {}", id);
        Optional<TecnicasEvaluativas> tecnicasEvaluativas = tecnicasEvaluativasRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tecnicasEvaluativas);
    }

    /**
     * {@code DELETE  /tecnicas-evaluativas/:id} : delete the "id" tecnicasEvaluativas.
     *
     * @param id the id of the tecnicasEvaluativas to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tecnicas-evaluativas/{id}")
    public ResponseEntity<Void> deleteTecnicasEvaluativas(@PathVariable Long id) {
        log.debug("REST request to delete TecnicasEvaluativas : {}", id);
        tecnicasEvaluativasRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
