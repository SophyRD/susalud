package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Remove;
import com.mycompany.myapp.repository.RemoveRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Remove}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RemoveResource {

    private final Logger log = LoggerFactory.getLogger(RemoveResource.class);

    private static final String ENTITY_NAME = "remove";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RemoveRepository removeRepository;

    public RemoveResource(RemoveRepository removeRepository) {
        this.removeRepository = removeRepository;
    }

    /**
     * {@code POST  /removes} : Create a new remove.
     *
     * @param remove the remove to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new remove, or with status {@code 400 (Bad Request)} if the remove has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/removes")
    public ResponseEntity<Remove> createRemove(@RequestBody Remove remove) throws URISyntaxException {
        log.debug("REST request to save Remove : {}", remove);
        if (remove.getId() != null) {
            throw new BadRequestAlertException("A new remove cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Remove result = removeRepository.save(remove);
        return ResponseEntity.created(new URI("/api/removes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /removes} : Updates an existing remove.
     *
     * @param remove the remove to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated remove,
     * or with status {@code 400 (Bad Request)} if the remove is not valid,
     * or with status {@code 500 (Internal Server Error)} if the remove couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/removes")
    public ResponseEntity<Remove> updateRemove(@RequestBody Remove remove) throws URISyntaxException {
        log.debug("REST request to update Remove : {}", remove);
        if (remove.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Remove result = removeRepository.save(remove);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, remove.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /removes} : get all the removes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of removes in body.
     */
    @GetMapping("/removes")
    public List<Remove> getAllRemoves() {
        log.debug("REST request to get all Removes");
        return removeRepository.findAll();
    }

    /**
     * {@code GET  /removes/:id} : get the "id" remove.
     *
     * @param id the id of the remove to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the remove, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/removes/{id}")
    public ResponseEntity<Remove> getRemove(@PathVariable Long id) {
        log.debug("REST request to get Remove : {}", id);
        Optional<Remove> remove = removeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(remove);
    }

    /**
     * {@code DELETE  /removes/:id} : delete the "id" remove.
     *
     * @param id the id of the remove to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/removes/{id}")
    public ResponseEntity<Void> deleteRemove(@PathVariable Long id) {
        log.debug("REST request to delete Remove : {}", id);
        removeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
