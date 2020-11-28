package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.FuenteReferencial;
import com.susalud.c1920c.repository.FuenteReferencialRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.FuenteReferencial}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FuenteReferencialResource {

    private final Logger log = LoggerFactory.getLogger(FuenteReferencialResource.class);

    private static final String ENTITY_NAME = "fuenteReferencial";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FuenteReferencialRepository fuenteReferencialRepository;

    public FuenteReferencialResource(FuenteReferencialRepository fuenteReferencialRepository) {
        this.fuenteReferencialRepository = fuenteReferencialRepository;
    }

    /**
     * {@code POST  /fuente-referencials} : Create a new fuenteReferencial.
     *
     * @param fuenteReferencial the fuenteReferencial to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fuenteReferencial, or with status {@code 400 (Bad Request)} if the fuenteReferencial has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fuente-referencials")
    public ResponseEntity<FuenteReferencial> createFuenteReferencial(@Valid @RequestBody FuenteReferencial fuenteReferencial) throws URISyntaxException {
        log.debug("REST request to save FuenteReferencial : {}", fuenteReferencial);
        if (fuenteReferencial.getId() != null) {
            throw new BadRequestAlertException("A new fuenteReferencial cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FuenteReferencial result = fuenteReferencialRepository.save(fuenteReferencial);
        return ResponseEntity.created(new URI("/api/fuente-referencials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fuente-referencials} : Updates an existing fuenteReferencial.
     *
     * @param fuenteReferencial the fuenteReferencial to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fuenteReferencial,
     * or with status {@code 400 (Bad Request)} if the fuenteReferencial is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fuenteReferencial couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fuente-referencials")
    public ResponseEntity<FuenteReferencial> updateFuenteReferencial(@Valid @RequestBody FuenteReferencial fuenteReferencial) throws URISyntaxException {
        log.debug("REST request to update FuenteReferencial : {}", fuenteReferencial);
        if (fuenteReferencial.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FuenteReferencial result = fuenteReferencialRepository.save(fuenteReferencial);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fuenteReferencial.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fuente-referencials} : get all the fuenteReferencials.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fuenteReferencials in body.
     */
    @GetMapping("/fuente-referencials")
    public List<FuenteReferencial> getAllFuenteReferencials() {
        log.debug("REST request to get all FuenteReferencials");
        return fuenteReferencialRepository.findAll();
    }

    /**
     * {@code GET  /fuente-referencials/:id} : get the "id" fuenteReferencial.
     *
     * @param id the id of the fuenteReferencial to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fuenteReferencial, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fuente-referencials/{id}")
    public ResponseEntity<FuenteReferencial> getFuenteReferencial(@PathVariable Long id) {
        log.debug("REST request to get FuenteReferencial : {}", id);
        Optional<FuenteReferencial> fuenteReferencial = fuenteReferencialRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fuenteReferencial);
    }

    /**
     * {@code DELETE  /fuente-referencials/:id} : delete the "id" fuenteReferencial.
     *
     * @param id the id of the fuenteReferencial to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fuente-referencials/{id}")
    public ResponseEntity<Void> deleteFuenteReferencial(@PathVariable Long id) {
        log.debug("REST request to delete FuenteReferencial : {}", id);
        fuenteReferencialRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
