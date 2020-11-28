package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.domain.UsuariosXevaluacion;
import com.susalud.c1920c.repository.UsuariosXevaluacionRepository;
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
 * REST controller for managing {@link com.susalud.c1920c.domain.UsuariosXevaluacion}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UsuariosXevaluacionResource {

    private final Logger log = LoggerFactory.getLogger(UsuariosXevaluacionResource.class);

    private static final String ENTITY_NAME = "usuariosXevaluacion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UsuariosXevaluacionRepository usuariosXevaluacionRepository;

    public UsuariosXevaluacionResource(UsuariosXevaluacionRepository usuariosXevaluacionRepository) {
        this.usuariosXevaluacionRepository = usuariosXevaluacionRepository;
    }

    /**
     * {@code POST  /usuarios-xevaluacions} : Create a new usuariosXevaluacion.
     *
     * @param usuariosXevaluacion the usuariosXevaluacion to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new usuariosXevaluacion, or with status {@code 400 (Bad Request)} if the usuariosXevaluacion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/usuarios-xevaluacions")
    public ResponseEntity<UsuariosXevaluacion> createUsuariosXevaluacion(@Valid @RequestBody UsuariosXevaluacion usuariosXevaluacion) throws URISyntaxException {
        log.debug("REST request to save UsuariosXevaluacion : {}", usuariosXevaluacion);
        if (usuariosXevaluacion.getId() != null) {
            throw new BadRequestAlertException("A new usuariosXevaluacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UsuariosXevaluacion result = usuariosXevaluacionRepository.save(usuariosXevaluacion);
        return ResponseEntity.created(new URI("/api/usuarios-xevaluacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /usuarios-xevaluacions} : Updates an existing usuariosXevaluacion.
     *
     * @param usuariosXevaluacion the usuariosXevaluacion to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated usuariosXevaluacion,
     * or with status {@code 400 (Bad Request)} if the usuariosXevaluacion is not valid,
     * or with status {@code 500 (Internal Server Error)} if the usuariosXevaluacion couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/usuarios-xevaluacions")
    public ResponseEntity<UsuariosXevaluacion> updateUsuariosXevaluacion(@Valid @RequestBody UsuariosXevaluacion usuariosXevaluacion) throws URISyntaxException {
        log.debug("REST request to update UsuariosXevaluacion : {}", usuariosXevaluacion);
        if (usuariosXevaluacion.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UsuariosXevaluacion result = usuariosXevaluacionRepository.save(usuariosXevaluacion);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, usuariosXevaluacion.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /usuarios-xevaluacions} : get all the usuariosXevaluacions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of usuariosXevaluacions in body.
     */
    @GetMapping("/usuarios-xevaluacions")
    public List<UsuariosXevaluacion> getAllUsuariosXevaluacions() {
        log.debug("REST request to get all UsuariosXevaluacions");
        return usuariosXevaluacionRepository.findAll();
    }

    /**
     * {@code GET  /usuarios-xevaluacions/:id} : get the "id" usuariosXevaluacion.
     *
     * @param id the id of the usuariosXevaluacion to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the usuariosXevaluacion, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/usuarios-xevaluacions/{id}")
    public ResponseEntity<UsuariosXevaluacion> getUsuariosXevaluacion(@PathVariable Long id) {
        log.debug("REST request to get UsuariosXevaluacion : {}", id);
        Optional<UsuariosXevaluacion> usuariosXevaluacion = usuariosXevaluacionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(usuariosXevaluacion);
    }

    /**
     * {@code DELETE  /usuarios-xevaluacions/:id} : delete the "id" usuariosXevaluacion.
     *
     * @param id the id of the usuariosXevaluacion to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/usuarios-xevaluacions/{id}")
    public ResponseEntity<Void> deleteUsuariosXevaluacion(@PathVariable Long id) {
        log.debug("REST request to delete UsuariosXevaluacion : {}", id);
        usuariosXevaluacionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
