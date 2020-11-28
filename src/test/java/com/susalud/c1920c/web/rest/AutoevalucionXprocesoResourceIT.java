package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.AutoevalucionXproceso;
import com.susalud.c1920c.repository.AutoevalucionXprocesoRepository;

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
 * Integration tests for the {@link AutoevalucionXprocesoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class AutoevalucionXprocesoResourceIT {

    private static final Integer DEFAULT_ID_AUTOEVALUCION_XPROCESO = 1;
    private static final Integer UPDATED_ID_AUTOEVALUCION_XPROCESO = 2;

    private static final LocalDate DEFAULT_AP_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AP_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SP_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SP_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_AP_COMENTARIO = "AAAAAAAAAA";
    private static final String UPDATED_AP_COMENTARIO = "BBBBBBBBBB";

    private static final Integer DEFAULT_AP_PUNTUACION = 1;
    private static final Integer UPDATED_AP_PUNTUACION = 2;

    private static final Integer DEFAULT_ID_AUTOEVALUCION = 1;
    private static final Integer UPDATED_ID_AUTOEVALUCION = 2;

    private static final Integer DEFAULT_ID_MES = 1;
    private static final Integer UPDATED_ID_MES = 2;

    private static final Integer DEFAULT_ID_USUARIOS_XEVALUACION = 1;
    private static final Integer UPDATED_ID_USUARIOS_XEVALUACION = 2;

    private static final Integer DEFAULT_ID_ESTADO = 1;
    private static final Integer UPDATED_ID_ESTADO = 2;

    private static final Integer DEFAULT_ID_VERIFICADOR = 1;
    private static final Integer UPDATED_ID_VERIFICADOR = 2;

    @Autowired
    private AutoevalucionXprocesoRepository autoevalucionXprocesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutoevalucionXprocesoMockMvc;

    private AutoevalucionXproceso autoevalucionXproceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AutoevalucionXproceso createEntity(EntityManager em) {
        AutoevalucionXproceso autoevalucionXproceso = new AutoevalucionXproceso()
            .idAutoevalucionXproceso(DEFAULT_ID_AUTOEVALUCION_XPROCESO)
            .apFechaCreacion(DEFAULT_AP_FECHA_CREACION)
            .spFechaModificacion(DEFAULT_SP_FECHA_MODIFICACION)
            .apComentario(DEFAULT_AP_COMENTARIO)
            .apPuntuacion(DEFAULT_AP_PUNTUACION)
            .idAutoevalucion(DEFAULT_ID_AUTOEVALUCION)
            .idMes(DEFAULT_ID_MES)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .idEstado(DEFAULT_ID_ESTADO)
            .idVerificador(DEFAULT_ID_VERIFICADOR);
        return autoevalucionXproceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AutoevalucionXproceso createUpdatedEntity(EntityManager em) {
        AutoevalucionXproceso autoevalucionXproceso = new AutoevalucionXproceso()
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .apFechaCreacion(UPDATED_AP_FECHA_CREACION)
            .spFechaModificacion(UPDATED_SP_FECHA_MODIFICACION)
            .apComentario(UPDATED_AP_COMENTARIO)
            .apPuntuacion(UPDATED_AP_PUNTUACION)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR);
        return autoevalucionXproceso;
    }

    @BeforeEach
    public void initTest() {
        autoevalucionXproceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createAutoevalucionXproceso() throws Exception {
        int databaseSizeBeforeCreate = autoevalucionXprocesoRepository.findAll().size();
        // Create the AutoevalucionXproceso
        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isCreated());

        // Validate the AutoevalucionXproceso in the database
        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeCreate + 1);
        AutoevalucionXproceso testAutoevalucionXproceso = autoevalucionXprocesoList.get(autoevalucionXprocesoList.size() - 1);
        assertThat(testAutoevalucionXproceso.getIdAutoevalucionXproceso()).isEqualTo(DEFAULT_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testAutoevalucionXproceso.getApFechaCreacion()).isEqualTo(DEFAULT_AP_FECHA_CREACION);
        assertThat(testAutoevalucionXproceso.getSpFechaModificacion()).isEqualTo(DEFAULT_SP_FECHA_MODIFICACION);
        assertThat(testAutoevalucionXproceso.getApComentario()).isEqualTo(DEFAULT_AP_COMENTARIO);
        assertThat(testAutoevalucionXproceso.getApPuntuacion()).isEqualTo(DEFAULT_AP_PUNTUACION);
        assertThat(testAutoevalucionXproceso.getIdAutoevalucion()).isEqualTo(DEFAULT_ID_AUTOEVALUCION);
        assertThat(testAutoevalucionXproceso.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testAutoevalucionXproceso.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testAutoevalucionXproceso.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
        assertThat(testAutoevalucionXproceso.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void createAutoevalucionXprocesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = autoevalucionXprocesoRepository.findAll().size();

        // Create the AutoevalucionXproceso with an existing ID
        autoevalucionXproceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        // Validate the AutoevalucionXproceso in the database
        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdAutoevalucionXprocesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdAutoevalucionXproceso(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setApFechaCreacion(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSpFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setSpFechaModificacion(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApComentarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setApComentario(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApPuntuacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setApPuntuacion(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdAutoevalucion(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdMes(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdUsuariosXevaluacion(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdEstado(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = autoevalucionXprocesoRepository.findAll().size();
        // set the field null
        autoevalucionXproceso.setIdVerificador(null);

        // Create the AutoevalucionXproceso, which fails.


        restAutoevalucionXprocesoMockMvc.perform(post("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAutoevalucionXprocesos() throws Exception {
        // Initialize the database
        autoevalucionXprocesoRepository.saveAndFlush(autoevalucionXproceso);

        // Get all the autoevalucionXprocesoList
        restAutoevalucionXprocesoMockMvc.perform(get("/api/autoevalucion-xprocesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autoevalucionXproceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idAutoevalucionXproceso").value(hasItem(DEFAULT_ID_AUTOEVALUCION_XPROCESO)))
            .andExpect(jsonPath("$.[*].apFechaCreacion").value(hasItem(DEFAULT_AP_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].spFechaModificacion").value(hasItem(DEFAULT_SP_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].apComentario").value(hasItem(DEFAULT_AP_COMENTARIO)))
            .andExpect(jsonPath("$.[*].apPuntuacion").value(hasItem(DEFAULT_AP_PUNTUACION)))
            .andExpect(jsonPath("$.[*].idAutoevalucion").value(hasItem(DEFAULT_ID_AUTOEVALUCION)))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getAutoevalucionXproceso() throws Exception {
        // Initialize the database
        autoevalucionXprocesoRepository.saveAndFlush(autoevalucionXproceso);

        // Get the autoevalucionXproceso
        restAutoevalucionXprocesoMockMvc.perform(get("/api/autoevalucion-xprocesos/{id}", autoevalucionXproceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autoevalucionXproceso.getId().intValue()))
            .andExpect(jsonPath("$.idAutoevalucionXproceso").value(DEFAULT_ID_AUTOEVALUCION_XPROCESO))
            .andExpect(jsonPath("$.apFechaCreacion").value(DEFAULT_AP_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.spFechaModificacion").value(DEFAULT_SP_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.apComentario").value(DEFAULT_AP_COMENTARIO))
            .andExpect(jsonPath("$.apPuntuacion").value(DEFAULT_AP_PUNTUACION))
            .andExpect(jsonPath("$.idAutoevalucion").value(DEFAULT_ID_AUTOEVALUCION))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingAutoevalucionXproceso() throws Exception {
        // Get the autoevalucionXproceso
        restAutoevalucionXprocesoMockMvc.perform(get("/api/autoevalucion-xprocesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAutoevalucionXproceso() throws Exception {
        // Initialize the database
        autoevalucionXprocesoRepository.saveAndFlush(autoevalucionXproceso);

        int databaseSizeBeforeUpdate = autoevalucionXprocesoRepository.findAll().size();

        // Update the autoevalucionXproceso
        AutoevalucionXproceso updatedAutoevalucionXproceso = autoevalucionXprocesoRepository.findById(autoevalucionXproceso.getId()).get();
        // Disconnect from session so that the updates on updatedAutoevalucionXproceso are not directly saved in db
        em.detach(updatedAutoevalucionXproceso);
        updatedAutoevalucionXproceso
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .apFechaCreacion(UPDATED_AP_FECHA_CREACION)
            .spFechaModificacion(UPDATED_SP_FECHA_MODIFICACION)
            .apComentario(UPDATED_AP_COMENTARIO)
            .apPuntuacion(UPDATED_AP_PUNTUACION)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR);

        restAutoevalucionXprocesoMockMvc.perform(put("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAutoevalucionXproceso)))
            .andExpect(status().isOk());

        // Validate the AutoevalucionXproceso in the database
        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeUpdate);
        AutoevalucionXproceso testAutoevalucionXproceso = autoevalucionXprocesoList.get(autoevalucionXprocesoList.size() - 1);
        assertThat(testAutoevalucionXproceso.getIdAutoevalucionXproceso()).isEqualTo(UPDATED_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testAutoevalucionXproceso.getApFechaCreacion()).isEqualTo(UPDATED_AP_FECHA_CREACION);
        assertThat(testAutoevalucionXproceso.getSpFechaModificacion()).isEqualTo(UPDATED_SP_FECHA_MODIFICACION);
        assertThat(testAutoevalucionXproceso.getApComentario()).isEqualTo(UPDATED_AP_COMENTARIO);
        assertThat(testAutoevalucionXproceso.getApPuntuacion()).isEqualTo(UPDATED_AP_PUNTUACION);
        assertThat(testAutoevalucionXproceso.getIdAutoevalucion()).isEqualTo(UPDATED_ID_AUTOEVALUCION);
        assertThat(testAutoevalucionXproceso.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testAutoevalucionXproceso.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testAutoevalucionXproceso.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
        assertThat(testAutoevalucionXproceso.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingAutoevalucionXproceso() throws Exception {
        int databaseSizeBeforeUpdate = autoevalucionXprocesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutoevalucionXprocesoMockMvc.perform(put("/api/autoevalucion-xprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(autoevalucionXproceso)))
            .andExpect(status().isBadRequest());

        // Validate the AutoevalucionXproceso in the database
        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAutoevalucionXproceso() throws Exception {
        // Initialize the database
        autoevalucionXprocesoRepository.saveAndFlush(autoevalucionXproceso);

        int databaseSizeBeforeDelete = autoevalucionXprocesoRepository.findAll().size();

        // Delete the autoevalucionXproceso
        restAutoevalucionXprocesoMockMvc.perform(delete("/api/autoevalucion-xprocesos/{id}", autoevalucionXproceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AutoevalucionXproceso> autoevalucionXprocesoList = autoevalucionXprocesoRepository.findAll();
        assertThat(autoevalucionXprocesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
