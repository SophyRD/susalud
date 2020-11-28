package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.Mes;
import com.susalud.c1920c.repository.MesRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.Mes}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MesResource {

    private final Logger log = LoggerFactory.getLogger(MesResource.class);

    private static final String ENTITY_NAME = "mes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MesRepository mesRepository;

    public MesResource(MesRepository mesRepository) {
        this.mesRepository = mesRepository;
    }

    /**
     * {@code POST  /mes} : Create a new mes.
     *
     * @param mes the mes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mes, or with status {@code 400 (Bad Request)} if the mes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mes")
    public ResponseEntity<Mes> createMes(@Valid @RequestBody Mes mes) throws URISyntaxException {
        log.debug("REST request to save Mes : {}", mes);
        if (mes.getId() != null) {
            throw new BadRequestAlertException("A new mes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mes result = mesRepository.save(mes);
        return ResponseEntity.created(new URI("/api/mes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mes} : Updates an existing mes.
     *
     * @param mes the mes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mes,
     * or with status {@code 400 (Bad Request)} if the mes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mes")
    public ResponseEntity<Mes> updateMes(@Valid @RequestBody Mes mes) throws URISyntaxException {
        log.debug("REST request to update Mes : {}", mes);
        if (mes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Mes result = mesRepository.save(mes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mes.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mes} : get all the mes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mes in body.
     */
    @GetMapping("/mes")
    public List<Mes> getAllMes() {
        log.debug("REST request to get all Mes");
        return mesRepository.findAll();
    }

    /**
     * {@code GET  /mes/:id} : get the "id" mes.
     *
     * @param id the id of the mes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mes/{id}")
    public ResponseEntity<Mes> getMes(@PathVariable Long id) {
        log.debug("REST request to get Mes : {}", id);
        Optional<Mes> mes = mesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mes);
    }

    /**
     * {@code DELETE  /mes/:id} : delete the "id" mes.
     *
     * @param id the id of the mes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mes/{id}")
    public ResponseEntity<Void> deleteMes(@PathVariable Long id) {
        log.debug("REST request to delete Mes : {}", id);
        mesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
