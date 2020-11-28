package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.UsuarioXEvaluacioXEntidad;
import com.susalud.c1920c.repository.UsuarioXEvaluacioXEntidadRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UsuarioXEvaluacioXEntidadResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsuarioXEvaluacioXEntidadResourceIT {

    private static final Integer DEFAULT_ID_USUARIO_X_EVALUACIO_X_ENTIDAD = 1;
    private static final Integer UPDATED_ID_USUARIO_X_EVALUACIO_X_ENTIDAD = 2;

    private static final LocalDate DEFAULT_UEE_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UEE_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UEE_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UEE_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ID_USUARIOS_XEVALUACION = 1;
    private static final Integer UPDATED_ID_USUARIOS_XEVALUACION = 2;

    @Autowired
    private UsuarioXEvaluacioXEntidadRepository usuarioXEvaluacioXEntidadRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuarioXEvaluacioXEntidadMockMvc;

    private UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuarioXEvaluacioXEntidad createEntity(EntityManager em) {
        UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad = new UsuarioXEvaluacioXEntidad()
            .idUsuarioXEvaluacioXEntidad(DEFAULT_ID_USUARIO_X_EVALUACIO_X_ENTIDAD)
            .ueeFechaCreacion(DEFAULT_UEE_FECHA_CREACION)
            .ueeFechaModificacion(DEFAULT_UEE_FECHA_MODIFICACION)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION);
        return usuarioXEvaluacioXEntidad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuarioXEvaluacioXEntidad createUpdatedEntity(EntityManager em) {
        UsuarioXEvaluacioXEntidad usuarioXEvaluacioXEntidad = new UsuarioXEvaluacioXEntidad()
            .idUsuarioXEvaluacioXEntidad(UPDATED_ID_USUARIO_X_EVALUACIO_X_ENTIDAD)
            .ueeFechaCreacion(UPDATED_UEE_FECHA_CREACION)
            .ueeFechaModificacion(UPDATED_UEE_FECHA_MODIFICACION)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION);
        return usuarioXEvaluacioXEntidad;
    }

    @BeforeEach
    public void initTest() {
        usuarioXEvaluacioXEntidad = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarioXEvaluacioXEntidad() throws Exception {
        int databaseSizeBeforeCreate = usuarioXEvaluacioXEntidadRepository.findAll().size();
        // Create the UsuarioXEvaluacioXEntidad
        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isCreated());

        // Validate the UsuarioXEvaluacioXEntidad in the database
        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeCreate + 1);
        UsuarioXEvaluacioXEntidad testUsuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidadList.get(usuarioXEvaluacioXEntidadList.size() - 1);
        assertThat(testUsuarioXEvaluacioXEntidad.getIdUsuarioXEvaluacioXEntidad()).isEqualTo(DEFAULT_ID_USUARIO_X_EVALUACIO_X_ENTIDAD);
        assertThat(testUsuarioXEvaluacioXEntidad.getUeeFechaCreacion()).isEqualTo(DEFAULT_UEE_FECHA_CREACION);
        assertThat(testUsuarioXEvaluacioXEntidad.getUeeFechaModificacion()).isEqualTo(DEFAULT_UEE_FECHA_MODIFICACION);
        assertThat(testUsuarioXEvaluacioXEntidad.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
    }

    @Test
    @Transactional
    public void createUsuarioXEvaluacioXEntidadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuarioXEvaluacioXEntidadRepository.findAll().size();

        // Create the UsuarioXEvaluacioXEntidad with an existing ID
        usuarioXEvaluacioXEntidad.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        // Validate the UsuarioXEvaluacioXEntidad in the database
        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioXEvaluacioXEntidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuarioXEvaluacioXEntidadRepository.findAll().size();
        // set the field null
        usuarioXEvaluacioXEntidad.setIdUsuarioXEvaluacioXEntidad(null);

        // Create the UsuarioXEvaluacioXEntidad, which fails.


        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUeeFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuarioXEvaluacioXEntidadRepository.findAll().size();
        // set the field null
        usuarioXEvaluacioXEntidad.setUeeFechaCreacion(null);

        // Create the UsuarioXEvaluacioXEntidad, which fails.


        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUeeFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuarioXEvaluacioXEntidadRepository.findAll().size();
        // set the field null
        usuarioXEvaluacioXEntidad.setUeeFechaModificacion(null);

        // Create the UsuarioXEvaluacioXEntidad, which fails.


        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuarioXEvaluacioXEntidadRepository.findAll().size();
        // set the field null
        usuarioXEvaluacioXEntidad.setIdUsuariosXevaluacion(null);

        // Create the UsuarioXEvaluacioXEntidad, which fails.


        restUsuarioXEvaluacioXEntidadMockMvc.perform(post("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsuarioXEvaluacioXEntidads() throws Exception {
        // Initialize the database
        usuarioXEvaluacioXEntidadRepository.saveAndFlush(usuarioXEvaluacioXEntidad);

        // Get all the usuarioXEvaluacioXEntidadList
        restUsuarioXEvaluacioXEntidadMockMvc.perform(get("/api/usuario-x-evaluacio-x-entidads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarioXEvaluacioXEntidad.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUsuarioXEvaluacioXEntidad").value(hasItem(DEFAULT_ID_USUARIO_X_EVALUACIO_X_ENTIDAD)))
            .andExpect(jsonPath("$.[*].ueeFechaCreacion").value(hasItem(DEFAULT_UEE_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].ueeFechaModificacion").value(hasItem(DEFAULT_UEE_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)));
    }
    
    @Test
    @Transactional
    public void getUsuarioXEvaluacioXEntidad() throws Exception {
        // Initialize the database
        usuarioXEvaluacioXEntidadRepository.saveAndFlush(usuarioXEvaluacioXEntidad);

        // Get the usuarioXEvaluacioXEntidad
        restUsuarioXEvaluacioXEntidadMockMvc.perform(get("/api/usuario-x-evaluacio-x-entidads/{id}", usuarioXEvaluacioXEntidad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuarioXEvaluacioXEntidad.getId().intValue()))
            .andExpect(jsonPath("$.idUsuarioXEvaluacioXEntidad").value(DEFAULT_ID_USUARIO_X_EVALUACIO_X_ENTIDAD))
            .andExpect(jsonPath("$.ueeFechaCreacion").value(DEFAULT_UEE_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.ueeFechaModificacion").value(DEFAULT_UEE_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION));
    }
    @Test
    @Transactional
    public void getNonExistingUsuarioXEvaluacioXEntidad() throws Exception {
        // Get the usuarioXEvaluacioXEntidad
        restUsuarioXEvaluacioXEntidadMockMvc.perform(get("/api/usuario-x-evaluacio-x-entidads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarioXEvaluacioXEntidad() throws Exception {
        // Initialize the database
        usuarioXEvaluacioXEntidadRepository.saveAndFlush(usuarioXEvaluacioXEntidad);

        int databaseSizeBeforeUpdate = usuarioXEvaluacioXEntidadRepository.findAll().size();

        // Update the usuarioXEvaluacioXEntidad
        UsuarioXEvaluacioXEntidad updatedUsuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidadRepository.findById(usuarioXEvaluacioXEntidad.getId()).get();
        // Disconnect from session so that the updates on updatedUsuarioXEvaluacioXEntidad are not directly saved in db
        em.detach(updatedUsuarioXEvaluacioXEntidad);
        updatedUsuarioXEvaluacioXEntidad
            .idUsuarioXEvaluacioXEntidad(UPDATED_ID_USUARIO_X_EVALUACIO_X_ENTIDAD)
            .ueeFechaCreacion(UPDATED_UEE_FECHA_CREACION)
            .ueeFechaModificacion(UPDATED_UEE_FECHA_MODIFICACION)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION);

        restUsuarioXEvaluacioXEntidadMockMvc.perform(put("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsuarioXEvaluacioXEntidad)))
            .andExpect(status().isOk());

        // Validate the UsuarioXEvaluacioXEntidad in the database
        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeUpdate);
        UsuarioXEvaluacioXEntidad testUsuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidadList.get(usuarioXEvaluacioXEntidadList.size() - 1);
        assertThat(testUsuarioXEvaluacioXEntidad.getIdUsuarioXEvaluacioXEntidad()).isEqualTo(UPDATED_ID_USUARIO_X_EVALUACIO_X_ENTIDAD);
        assertThat(testUsuarioXEvaluacioXEntidad.getUeeFechaCreacion()).isEqualTo(UPDATED_UEE_FECHA_CREACION);
        assertThat(testUsuarioXEvaluacioXEntidad.getUeeFechaModificacion()).isEqualTo(UPDATED_UEE_FECHA_MODIFICACION);
        assertThat(testUsuarioXEvaluacioXEntidad.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarioXEvaluacioXEntidad() throws Exception {
        int databaseSizeBeforeUpdate = usuarioXEvaluacioXEntidadRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuarioXEvaluacioXEntidadMockMvc.perform(put("/api/usuario-x-evaluacio-x-entidads")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarioXEvaluacioXEntidad)))
            .andExpect(status().isBadRequest());

        // Validate the UsuarioXEvaluacioXEntidad in the database
        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuarioXEvaluacioXEntidad() throws Exception {
        // Initialize the database
        usuarioXEvaluacioXEntidadRepository.saveAndFlush(usuarioXEvaluacioXEntidad);

        int databaseSizeBeforeDelete = usuarioXEvaluacioXEntidadRepository.findAll().size();

        // Delete the usuarioXEvaluacioXEntidad
        restUsuarioXEvaluacioXEntidadMockMvc.perform(delete("/api/usuario-x-evaluacio-x-entidads/{id}", usuarioXEvaluacioXEntidad.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsuarioXEvaluacioXEntidad> usuarioXEvaluacioXEntidadList = usuarioXEvaluacioXEntidadRepository.findAll();
        assertThat(usuarioXEvaluacioXEntidadList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
