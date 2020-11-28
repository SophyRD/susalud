package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Autoevalucion;
import com.susalud.c1920c.repository.AutoevalucionRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AutoevalucionResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AutoevalucionResourceIT {

    private static final Integer DEFAULT_ID_AUTOEVALUCION = 1;
    private static final Integer UPDATED_ID_AUTOEVALUCION = 2;

    private static final String DEFAULT_A_AVANCE = "AAAAAAAAAA";
    private static final String UPDATED_A_AVANCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_MES = 1;
    private static final Integer UPDATED_ID_MES = 2;

    private static final Integer DEFAULT_ID_USUARIOS_XEVALUACION = 1;
    private static final Integer UPDATED_ID_USUARIOS_XEVALUACION = 2;

    private static final Integer DEFAULT_ID_ESTADO = 1;
    private static final Integer UPDATED_ID_ESTADO = 2;

    @Autowired
    private AutoevalucionRepository autoevalucionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutoevalucionMockMvc;

    private Autoevalucion autoevalucion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autoevalucion createEntity(EntityManager em) {
        Autoevalucion autoevalucion = new Autoevalucion()
            .idAutoevalucion(DEFAULT_ID_AUTOEVALUCION)
            .aAvance(DEFAULT_A_AVANCE)
            .idMes(DEFAULT_ID_MES)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .idEstado(DEFAULT_ID_ESTADO);
        return autoevalucion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autoevalucion createUpdatedEntity(EntityManager em) {
        Autoevalucion autoevalucion = new Autoevalucion()
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .aAvance(UPDATED_A_AVANCE)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO);
        return autoevalucion;
    }

    @BeforeEach
    public void initTest() {
        autoevalucion = createEntity(em);
    }

    @Test
    @Transactional
    public void createAutoevalucion() throws Exception {
        int databaseSizeBeforeCreate = autoevalucionRepository.findAll().size();
        // Create the Autoevalucion
        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isCreated());

        // Validate the Autoevalucion in the database
        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeCreate + 1);
        Autoevalucion testAutoevalucion = autoevalucionList.get(autoevalucionList.size() - 1);
        assertThat(testAutoevalucion.getIdAutoevalucion()).isEqualTo(DEFAULT_ID_AUTOEVALUCION);
        assertThat(testAutoevalucion.getaAvance()).isEqualTo(DEFAULT_A_AVANCE);
        assertThat(testAutoevalucion.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testAutoevalucion.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testAutoevalucion.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
    }

    @Test
    @Transactional
    public void createAutoevalucionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = autoevalucionRepository.findAll().size();

        // Create the Autoevalucion with an existing ID
        autoevalucion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        // Validate the Autoevalucion in the database
        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdAutoevalucionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionRepository.findAll().size();
        // set the field null
        autoevalucion.setIdAutoevalucion(null);

        // Create the Autoevalucion, which fails.


        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkaAvanceIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionRepository.findAll().size();
        // set the field null
        autoevalucion.setaAvance(null);

        // Create the Autoevalucion, which fails.


        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionRepository.findAll().size();
        // set the field null
        autoevalucion.setIdMes(null);

        // Create the Autoevalucion, which fails.


        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionRepository.findAll().size();
        // set the field null
        autoevalucion.setIdUsuariosXevaluacion(null);

        // Create the Autoevalucion, which fails.


        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionRepository.findAll().size();
        // set the field null
        autoevalucion.setIdEstado(null);

        // Create the Autoevalucion, which fails.


        restAutoevalucionMockMvc.perform(post("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAutoevalucions() throws Exception {
        // Initialize the database
        autoevalucionRepository.saveAndFlush(autoevalucion);

        // Get all the autoevalucionList
        restAutoevalucionMockMvc.perform(get("/api/autoevalucions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autoevalucion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAutoevalucion").value(hasItem(DEFAULT_ID_AUTOEVALUCION)))
            .andExpect(jsonPath("$.[*].aAvance").value(hasItem(DEFAULT_A_AVANCE)))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getAutoevalucion() throws Exception {
        // Initialize the database
        autoevalucionRepository.saveAndFlush(autoevalucion);

        // Get the autoevalucion
        restAutoevalucionMockMvc.perform(get("/api/autoevalucions/{id}", autoevalucion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autoevalucion.getId().intValue()))
            .andExpect(jsonPath("$.idAutoevalucion").value(DEFAULT_ID_AUTOEVALUCION))
            .andExpect(jsonPath("$.aAvance").value(DEFAULT_A_AVANCE))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingAutoevalucion() throws Exception {
        // Get the autoevalucion
        restAutoevalucionMockMvc.perform(get("/api/autoevalucions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAutoevalucion() throws Exception {
        // Initialize the database
        autoevalucionRepository.saveAndFlush(autoevalucion);

        int databaseSizeBeforeUpdate = autoevalucionRepository.findAll().size();

        // Update the autoevalucion
        Autoevalucion updatedAutoevalucion = autoevalucionRepository.findById(autoevalucion.getId()).get();
        // Disconnect from session so that the updates on updatedAutoevalucion are not directly saved in db
        em.detach(updatedAutoevalucion);
        updatedAutoevalucion
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .aAvance(UPDATED_A_AVANCE)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO);

        restAutoevalucionMockMvc.perform(put("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAutoevalucion)))
            .andExpect(status().isOk());

        // Validate the Autoevalucion in the database
        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeUpdate);
        Autoevalucion testAutoevalucion = autoevalucionList.get(autoevalucionList.size() - 1);
        assertThat(testAutoevalucion.getIdAutoevalucion()).isEqualTo(UPDATED_ID_AUTOEVALUCION);
        assertThat(testAutoevalucion.getaAvance()).isEqualTo(UPDATED_A_AVANCE);
        assertThat(testAutoevalucion.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testAutoevalucion.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testAutoevalucion.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingAutoevalucion() throws Exception {
        int databaseSizeBeforeUpdate = autoevalucionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutoevalucionMockMvc.perform(put("/api/autoevalucions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucion)))
            .andExpect(status().isBadRequest());

        // Validate the Autoevalucion in the database
        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAutoevalucion() throws Exception {
        // Initialize the database
        autoevalucionRepository.saveAndFlush(autoevalucion);

        int databaseSizeBeforeDelete = autoevalucionRepository.findAll().size();

        // Delete the autoevalucion
        restAutoevalucionMockMvc.perform(delete("/api/autoevalucions/{id}", autoevalucion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Autoevalucion> autoevalucionList = autoevalucionRepository.findAll();
        assertThat(autoevalucionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
