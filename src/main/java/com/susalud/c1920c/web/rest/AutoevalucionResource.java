package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.Autoevalucion;
import com.susalud.c1920c.repository.AutoevalucionRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.Autoevalucion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AutoevalucionResource {

    private final Logger log = LoggerFactory.getLogger(AutoevalucionResource.class);

    private static final String ENTITY_NAME = "autoevalucion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutoevalucionRepository autoevalucionRepository;

    public AutoevalucionResource(AutoevalucionRepository autoevalucionRepository) {
        this.autoevalucionRepository = autoevalucionRepository;
    }

    /**
     * {@code POST  /autoevalucions} : Create a new autoevalucion.
     *
     * @param autoevalucion the autoevalucion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autoevalucion, or with status {@code 400 (Bad Request)} if the autoevalucion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/autoevalucions")
    public ResponseEntity<Autoevalucion> createAutoevalucion(@Valid @RequestBody Autoevalucion autoevalucion) throws URISyntaxException {
        log.debug("REST request to save Autoevalucion : {}", autoevalucion);
        if (autoevalucion.getId() != null) {
            throw new BadRequestAlertException("A new autoevalucion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Autoevalucion result = autoevalucionRepository.save(autoevalucion);
        return ResponseEntity.created(new URI("/api/autoevalucions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /autoevalucions} : Updates an existing autoevalucion.
     *
     * @param autoevalucion the autoevalucion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autoevalucion,
     * or with status {@code 400 (Bad Request)} if the autoevalucion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autoevalucion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/autoevalucions")
    public ResponseEntity<Autoevalucion> updateAutoevalucion(@Valid @RequestBody Autoevalucion autoevalucion) throws URISyntaxException {
        log.debug("REST request to update Autoevalucion : {}", autoevalucion);
        if (autoevalucion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Autoevalucion result = autoevalucionRepository.save(autoevalucion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, autoevalucion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /autoevalucions} : get all the autoevalucions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autoevalucions in body.
     */
    @GetMapping("/autoevalucions")
    public List<Autoevalucion> getAllAutoevalucions() {
        log.debug("REST request to get all Autoevalucions");
        return autoevalucionRepository.findAll();
    }

    /**
     * {@code GET  /autoevalucions/:id} : get the "id" autoevalucion.
     *
     * @param id the id of the autoevalucion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autoevalucion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/autoevalucions/{id}")
    public ResponseEntity<Autoevalucion> getAutoevalucion(@PathVariable Long id) {
        log.debug("REST request to get Autoevalucion : {}", id);
        Optional<Autoevalucion> autoevalucion = autoevalucionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autoevalucion);
    }

    /**
     * {@code DELETE  /autoevalucions/:id} : delete the "id" autoevalucion.
     *
     * @param id the id of the autoevalucion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/autoevalucions/{id}")
    public ResponseEntity<Void> deleteAutoevalucion(@PathVariable Long id) {
        log.debug("REST request to delete Autoevalucion : {}", id);
        autoevalucionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
