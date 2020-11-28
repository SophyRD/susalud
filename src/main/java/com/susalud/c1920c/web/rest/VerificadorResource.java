package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.Verificador;
import com.susalud.c1920c.repository.VerificadorRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.Verificador}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VerificadorResource {

    private final Logger log = LoggerFactory.getLogger(VerificadorResource.class);

    private static final String ENTITY_NAME = "verificador";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VerificadorRepository verificadorRepository;

    public VerificadorResource(VerificadorRepository verificadorRepository) {
        this.verificadorRepository = verificadorRepository;
    }

    /**
     * {@code POST  /verificadors} : Create a new verificador.
     *
     * @param verificador the verificador to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new verificador, or with status {@code 400 (Bad Request)} if the verificador has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/verificadors")
    public ResponseEntity<Verificador> createVerificador(@Valid @RequestBody Verificador verificador) throws URISyntaxException {
        log.debug("REST request to save Verificador : {}", verificador);
        if (verificador.getId() != null) {
            throw new BadRequestAlertException("A new verificador cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Verificador result = verificadorRepository.save(verificador);
        return ResponseEntity.created(new URI("/api/verificadors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /verificadors} : Updates an existing verificador.
     *
     * @param verificador the verificador to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated verificador,
     * or with status {@code 400 (Bad Request)} if the verificador is not valid,
     * or with status {@code 500 (Internal Server Error)} if the verificador couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/verificadors")
    public ResponseEntity<Verificador> updateVerificador(@Valid @RequestBody Verificador verificador) throws URISyntaxException {
        log.debug("REST request to update Verificador : {}", verificador);
        if (verificador.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Verificador result = verificadorRepository.save(verificador);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, verificador.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /verificadors} : get all the verificadors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of verificadors in body.
     */
    @GetMapping("/verificadors")
    public List<Verificador> getAllVerificadors() {
        log.debug("REST request to get all Verificadors");
        return verificadorRepository.findAll();
    }

    /**
     * {@code GET  /verificadors/:id} : get the "id" verificador.
     *
     * @param id the id of the verificador to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the verificador, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/verificadors/{id}")
    public ResponseEntity<Verificador> getVerificador(@PathVariable Long id) {
        log.debug("REST request to get Verificador : {}", id);
        Optional<Verificador> verificador = verificadorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(verificador);
    }

    /**
     * {@code DELETE  /verificadors/:id} : delete the "id" verificador.
     *
     * @param id the id of the verificador to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/verificadors/{id}")
    public ResponseEntity<Void> deleteVerificador(@PathVariable Long id) {
        log.debug("REST request to delete Verificador : {}", id);
        verificadorRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
