package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.UsuariosXevaluacion;
import com.susalud.c1920c.repository.UsuariosXevaluacionRepository;

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
 * Integration tests for the {@link UsuariosXevaluacionResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsuariosXevaluacionResourceIT {

    private static final Integer DEFAULT_ID_USUARIOS_XEVALUACION = 1;
    private static final Integer UPDATED_ID_USUARIOS_XEVALUACION = 2;

    private static final LocalDate DEFAULT_U_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_U_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_U_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_U_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UsuariosXevaluacionRepository usuariosXevaluacionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuariosXevaluacionMockMvc;

    private UsuariosXevaluacion usuariosXevaluacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuariosXevaluacion createEntity(EntityManager em) {
        UsuariosXevaluacion usuariosXevaluacion = new UsuariosXevaluacion()
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .uFechaModificacion(DEFAULT_U_FECHA_MODIFICACION)
            .uFechaCreacion(DEFAULT_U_FECHA_CREACION);
        return usuariosXevaluacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuariosXevaluacion createUpdatedEntity(EntityManager em) {
        UsuariosXevaluacion usuariosXevaluacion = new UsuariosXevaluacion()
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .uFechaModificacion(UPDATED_U_FECHA_MODIFICACION)
            .uFechaCreacion(UPDATED_U_FECHA_CREACION);
        return usuariosXevaluacion;
    }

    @BeforeEach
    public void initTest() {
        usuariosXevaluacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuariosXevaluacion() throws Exception {
        int databaseSizeBeforeCreate = usuariosXevaluacionRepository.findAll().size();
        // Create the UsuariosXevaluacion
        restUsuariosXevaluacionMockMvc.perform(post("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isCreated());

        // Validate the UsuariosXevaluacion in the database
        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeCreate + 1);
        UsuariosXevaluacion testUsuariosXevaluacion = usuariosXevaluacionList.get(usuariosXevaluacionList.size() - 1);
        assertThat(testUsuariosXevaluacion.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testUsuariosXevaluacion.getuFechaModificacion()).isEqualTo(DEFAULT_U_FECHA_MODIFICACION);
        assertThat(testUsuariosXevaluacion.getuFechaCreacion()).isEqualTo(DEFAULT_U_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void createUsuariosXevaluacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuariosXevaluacionRepository.findAll().size();

        // Create the UsuariosXevaluacion with an existing ID
        usuariosXevaluacion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuariosXevaluacionMockMvc.perform(post("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isBadRequest());

        // Validate the UsuariosXevaluacion in the database
        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosXevaluacionRepository.findAll().size();
        // set the field null
        usuariosXevaluacion.setIdUsuariosXevaluacion(null);

        // Create the UsuariosXevaluacion, which fails.


        restUsuariosXevaluacionMockMvc.perform(post("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isBadRequest());

        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkuFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosXevaluacionRepository.findAll().size();
        // set the field null
        usuariosXevaluacion.setuFechaModificacion(null);

        // Create the UsuariosXevaluacion, which fails.


        restUsuariosXevaluacionMockMvc.perform(post("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isBadRequest());

        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkuFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosXevaluacionRepository.findAll().size();
        // set the field null
        usuariosXevaluacion.setuFechaCreacion(null);

        // Create the UsuariosXevaluacion, which fails.


        restUsuariosXevaluacionMockMvc.perform(post("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isBadRequest());

        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsuariosXevaluacions() throws Exception {
        // Initialize the database
        usuariosXevaluacionRepository.saveAndFlush(usuariosXevaluacion);

        // Get all the usuariosXevaluacionList
        restUsuariosXevaluacionMockMvc.perform(get("/api/usuarios-xevaluacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuariosXevaluacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].uFechaModificacion").value(hasItem(DEFAULT_U_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].uFechaCreacion").value(hasItem(DEFAULT_U_FECHA_CREACION.toString())));
    }
    
    @Test
    @Transactional
    public void getUsuariosXevaluacion() throws Exception {
        // Initialize the database
        usuariosXevaluacionRepository.saveAndFlush(usuariosXevaluacion);

        // Get the usuariosXevaluacion
        restUsuariosXevaluacionMockMvc.perform(get("/api/usuarios-xevaluacions/{id}", usuariosXevaluacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuariosXevaluacion.getId().intValue()))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.uFechaModificacion").value(DEFAULT_U_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.uFechaCreacion").value(DEFAULT_U_FECHA_CREACION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingUsuariosXevaluacion() throws Exception {
        // Get the usuariosXevaluacion
        restUsuariosXevaluacionMockMvc.perform(get("/api/usuarios-xevaluacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuariosXevaluacion() throws Exception {
        // Initialize the database
        usuariosXevaluacionRepository.saveAndFlush(usuariosXevaluacion);

        int databaseSizeBeforeUpdate = usuariosXevaluacionRepository.findAll().size();

        // Update the usuariosXevaluacion
        UsuariosXevaluacion updatedUsuariosXevaluacion = usuariosXevaluacionRepository.findById(usuariosXevaluacion.getId()).get();
        // Disconnect from session so that the updates on updatedUsuariosXevaluacion are not directly saved in db
        em.detach(updatedUsuariosXevaluacion);
        updatedUsuariosXevaluacion
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .uFechaModificacion(UPDATED_U_FECHA_MODIFICACION)
            .uFechaCreacion(UPDATED_U_FECHA_CREACION);

        restUsuariosXevaluacionMockMvc.perform(put("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsuariosXevaluacion)))
            .andExpect(status().isOk());

        // Validate the UsuariosXevaluacion in the database
        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeUpdate);
        UsuariosXevaluacion testUsuariosXevaluacion = usuariosXevaluacionList.get(usuariosXevaluacionList.size() - 1);
        assertThat(testUsuariosXevaluacion.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testUsuariosXevaluacion.getuFechaModificacion()).isEqualTo(UPDATED_U_FECHA_MODIFICACION);
        assertThat(testUsuariosXevaluacion.getuFechaCreacion()).isEqualTo(UPDATED_U_FECHA_CREACION);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuariosXevaluacion() throws Exception {
        int databaseSizeBeforeUpdate = usuariosXevaluacionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuariosXevaluacionMockMvc.perform(put("/api/usuarios-xevaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuariosXevaluacion)))
            .andExpect(status().isBadRequest());

        // Validate the UsuariosXevaluacion in the database
        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuariosXevaluacion() throws Exception {
        // Initialize the database
        usuariosXevaluacionRepository.saveAndFlush(usuariosXevaluacion);

        int databaseSizeBeforeDelete = usuariosXevaluacionRepository.findAll().size();

        // Delete the usuariosXevaluacion
        restUsuariosXevaluacionMockMvc.perform(delete("/api/usuarios-xevaluacions/{id}", usuariosXevaluacion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsuariosXevaluacion> usuariosXevaluacionList = usuariosXevaluacionRepository.findAll();
        assertThat(usuariosXevaluacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
