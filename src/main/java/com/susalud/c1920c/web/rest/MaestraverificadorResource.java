package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.Maestraverificador;
import com.susalud.c1920c.repository.MaestraverificadorRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.Maestraverificador}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MaestraverificadorResource {

    private final Logger log = LoggerFactory.getLogger(MaestraverificadorResource.class);

    private static final String ENTITY_NAME = "maestraverificador";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaestraverificadorRepository maestraverificadorRepository;

    public MaestraverificadorResource(MaestraverificadorRepository maestraverificadorRepository) {
        this.maestraverificadorRepository = maestraverificadorRepository;
    }

    /**
     * {@code POST  /maestraverificadors} : Create a new maestraverificador.
     *
     * @param maestraverificador the maestraverificador to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maestraverificador, or with status {@code 400 (Bad Request)} if the maestraverificador has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maestraverificadors")
    public ResponseEntity<Maestraverificador> createMaestraverificador(@Valid @RequestBody Maestraverificador maestraverificador) throws URISyntaxException {
        log.debug("REST request to save Maestraverificador : {}", maestraverificador);
        if (maestraverificador.getId() != null) {
            throw new BadRequestAlertException("A new maestraverificador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Maestraverificador result = maestraverificadorRepository.save(maestraverificador);
        return ResponseEntity.created(new URI("/api/maestraverificadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maestraverificadors} : Updates an existing maestraverificador.
     *
     * @param maestraverificador the maestraverificador to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maestraverificador,
     * or with status {@code 400 (Bad Request)} if the maestraverificador is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maestraverificador couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maestraverificadors")
    public ResponseEntity<Maestraverificador> updateMaestraverificador(@Valid @RequestBody Maestraverificador maestraverificador) throws URISyntaxException {
        log.debug("REST request to update Maestraverificador : {}", maestraverificador);
        if (maestraverificador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Maestraverificador result = maestraverificadorRepository.save(maestraverificador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maestraverificador.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /maestraverificadors} : get all the maestraverificadors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maestraverificadors in body.
     */
    @GetMapping("/maestraverificadors")
    public List<Maestraverificador> getAllMaestraverificadors() {
        log.debug("REST request to get all Maestraverificadors");
        return maestraverificadorRepository.findAll();
    }

    /**
     * {@code GET  /maestraverificadors/:id} : get the "id" maestraverificador.
     *
     * @param id the id of the maestraverificador to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maestraverificador, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maestraverificadors/{id}")
    public ResponseEntity<Maestraverificador> getMaestraverificador(@PathVariable Long id) {
        log.debug("REST request to get Maestraverificador : {}", id);
        Optional<Maestraverificador> maestraverificador = maestraverificadorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(maestraverificador);
    }

    /**
     * {@code DELETE  /maestraverificadors/:id} : delete the "id" maestraverificador.
     *
     * @param id the id of the maestraverificador to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maestraverificadors/{id}")
    public ResponseEntity<Void> deleteMaestraverificador(@PathVariable Long id) {
        log.debug("REST request to delete Maestraverificador : {}", id);
        maestraverificadorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
