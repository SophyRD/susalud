package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Macroproceso;
import com.mycompany.myapp.repository.MacroprocesoRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Macroproceso}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MacroprocesoResource {

    private final Logger log = LoggerFactory.getLogger(MacroprocesoResource.class);

    private static final String ENTITY_NAME = "macroproceso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MacroprocesoRepository macroprocesoRepository;

    public MacroprocesoResource(MacroprocesoRepository macroprocesoRepository) {
        this.macroprocesoRepository = macroprocesoRepository;
    }

    /**
     * {@code POST  /macroprocesos} : Create a new macroproceso.
     *
     * @param macroproceso the macroproceso to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new macroproceso, or with status {@code 400 (Bad Request)} if the macroproceso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/macroprocesos")
    public ResponseEntity<Macroproceso> createMacroproceso(@Valid @RequestBody Macroproceso macroproceso) throws URISyntaxException {
        log.debug("REST request to save Macroproceso : {}", macroproceso);
        if (macroproceso.getId() != null) {
            throw new BadRequestAlertException("A new macroproceso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Macroproceso result = macroprocesoRepository.save(macroproceso);
        return ResponseEntity.created(new URI("/api/macroprocesos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /macroprocesos} : Updates an existing macroproceso.
     *
     * @param macroproceso the macroproceso to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated macroproceso,
     * or with status {@code 400 (Bad Request)} if the macroproceso is not valid,
     * or with status {@code 500 (Internal Server Error)} if the macroproceso couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/macroprocesos")
    public ResponseEntity<Macroproceso> updateMacroproceso(@Valid @RequestBody Macroproceso macroproceso) throws URISyntaxException {
        log.debug("REST request to update Macroproceso : {}", macroproceso);
        if (macroproceso.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Macroproceso result = macroprocesoRepository.save(macroproceso);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, macroproceso.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /macroprocesos} : get all the macroprocesos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of macroprocesos in body.
     */
    @GetMapping("/macroprocesos")
    public List<Macroproceso> getAllMacroprocesos() {
        log.debug("REST request to get all Macroprocesos");
        return macroprocesoRepository.findAll();
    }

    /**
     * {@code GET  /macroprocesos/:id} : get the "id" macroproceso.
     *
     * @param id the id of the macroproceso to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the macroproceso, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/macroprocesos/{id}")
    public ResponseEntity<Macroproceso> getMacroproceso(@PathVariable Long id) {
        log.debug("REST request to get Macroproceso : {}", id);
        Optional<Macroproceso> macroproceso = macroprocesoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(macroproceso);
    }

    /**
     * {@code DELETE  /macroprocesos/:id} : delete the "id" macroproceso.
     *
     * @param id the id of the macroproceso to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/macroprocesos/{id}")
    public ResponseEntity<Void> deleteMacroproceso(@PathVariable Long id) {
        log.debug("REST request to delete Macroproceso : {}", id);
        macroprocesoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
