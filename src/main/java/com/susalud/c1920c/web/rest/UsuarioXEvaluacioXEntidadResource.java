package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.UsuarioXEvaluacioXEntidad;
import com.susalud.c1920c.repository.UsuarioXEvaluacioXEntidadRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.UsuarioXEvaluacioXEntidad}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UsuarioXEvaluacioXEntidadResource {

    private final Logger log = LoggerFactory.getLogger(UsuarioXEvaluacioXEntidadResource.class);

    private static final String ENTITY_NAME = "usuarioXEvaluacioXEntidad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsuarioXEvaluacioXEntidadRepository usuarioXEvaluacioXEntidadRepository;

    public UsuarioXEvaluacioXEntidadResource(UsuarioXEvaluacioXEntidadRepository usuarioXEvaluacioXEntidadRepository) {
        this.usuarioXEvaluacioXEntidadRepository = usuarioXEvaluacioXEntidadRepository;
    }

    /**
     * {@code POST  /usuario-x-evaluacio-x-entidads} : Create a new usuarioXEvaluacioXEntidad.
     *
     * @param usuarioXEvaluacioXEntidad the usuarioXEvaluacioXEntidad to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usuarioXEvaluacioXEntidad, or with status {@code 400 (Bad Request)} if the usuarioXEvaluacioXEntidad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usuario-x-evaluacio-x-entidads")
    public ResponseEntity<UsuarioXEvaluacioXEntidad> createUsuarioXEvaluacioXEntidad(@Valid @RequestBody UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad) throws URISyntaxException {
        log.debug("REST request to save UsuarioXEvaluacioXEntidad : {}", usuarioXEvaluacioXEntidad);
        if (usuarioXEvaluacioXEntidad.getId() != null) {
            throw new BadRequestAlertException("A new usuarioXEvaluacioXEntidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsuarioXEvaluacioXEntidad result = usuarioXEvaluacioXEntidadRepository.save(usuarioXEvaluacioXEntidad);
        return ResponseEntity.created(new URI("/api/usuario-x-evaluacio-x-entidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usuario-x-evaluacio-x-entidads} : Updates an existing usuarioXEvaluacioXEntidad.
     *
     * @param usuarioXEvaluacioXEntidad the usuarioXEvaluacioXEntidad to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usuarioXEvaluacioXEntidad,
     * or with status {@code 400 (Bad Request)} if the usuarioXEvaluacioXEntidad is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usuarioXEvaluacioXEntidad couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usuario-x-evaluacio-x-entidads")
    public ResponseEntity<UsuarioXEvaluacioXEntidad> updateUsuarioXEvaluacioXEntidad(@Valid @RequestBody UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad) throws URISyntaxException {
        log.debug("REST request to update UsuarioXEvaluacioXEntidad : {}", usuarioXEvaluacioXEntidad);
        if (usuarioXEvaluacioXEntidad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsuarioXEvaluacioXEntidad result = usuarioXEvaluacioXEntidadRepository.save(usuarioXEvaluacioXEntidad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usuarioXEvaluacioXEntidad.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /usuario-x-evaluacio-x-entidads} : get all the usuarioXEvaluacioXEntidads.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usuarioXEvaluacioXEntidads in body.
     */
    @GetMapping("/usuario-x-evaluacio-x-entidads")
    public List<UsuarioXEvaluacioXEntidad> getAllUsuarioXEvaluacioXEntidads() {
        log.debug("REST request to get all UsuarioXEvaluacioXEntidads");
        return usuarioXEvaluacioXEntidadRepository.findAll();
    }

    /**
     * {@code GET  /usuario-x-evaluacio-x-entidads/:id} : get the "id" usuarioXEvaluacioXEntidad.
     *
     * @param id the id of the usuarioXEvaluacioXEntidad to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usuarioXEvaluacioXEntidad, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usuario-x-evaluacio-x-entidads/{id}")
    public ResponseEntity<UsuarioXEvaluacioXEntidad> getUsuarioXEvaluacioXEntidad(@PathVariable Long id) {
        log.debug("REST request to get UsuarioXEvaluacioXEntidad : {}", id);
        Optional<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usuarioXEvaluacioXEntidad);
    }

    /**
     * {@code DELETE  /usuario-x-evaluacio-x-entidads/:id} : delete the "id" usuarioXEvaluacioXEntidad.
     *
     * @param id the id of the usuarioXEvaluacioXEntidad to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usuario-x-evaluacio-x-entidads/{id}")
    public ResponseEntity<Void> deleteUsuarioXEvaluacioXEntidad(@PathVariable Long id) {
        log.debug("REST request to delete UsuarioXEvaluacioXEntidad : {}", id);
        usuarioXEvaluacioXEntidadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
