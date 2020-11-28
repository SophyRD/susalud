package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.TecnicasEvaluativas;
import com.susalud.c1920c.repository.TecnicasEvaluativasRepository;

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
 * Integration tests for the {@link TecnicasEvaluativasResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TecnicasEvaluativasResourceIT {

    private static final Integer DEFAULT_ID_TECNICAS_EVALUATIVAS = 1;
    private static final Integer UPDATED_ID_TECNICAS_EVALUATIVAS = 2;

    private static final String DEFAULT_TE_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_TE_NOMBRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TE_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TE_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TE_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TE_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ID_AUTOEVALUCION_XPROCESO = 1;
    private static final Integer UPDATED_ID_AUTOEVALUCION_XPROCESO = 2;

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

    private static final Integer DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM = 1;
    private static final Integer UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM = 2;

    @Autowired
    private TecnicasEvaluativasRepository tecnicasEvaluativasRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTecnicasEvaluativasMockMvc;

    private TecnicasEvaluativas tecnicasEvaluativas;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecnicasEvaluativas createEntity(EntityManager em) {
        TecnicasEvaluativas tecnicasEvaluativas = new TecnicasEvaluativas()
            .idTecnicasEvaluativas(DEFAULT_ID_TECNICAS_EVALUATIVAS)
            .teNombre(DEFAULT_TE_NOMBRE)
            .teFechaCreacion(DEFAULT_TE_FECHA_CREACION)
            .teFechaModificacion(DEFAULT_TE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(DEFAULT_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(DEFAULT_ID_AUTOEVALUCION)
            .idMes(DEFAULT_ID_MES)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .idEstado(DEFAULT_ID_ESTADO)
            .idVerificador(DEFAULT_ID_VERIFICADOR)
            .idTecnicasEvaluativasXItem(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM);
        return tecnicasEvaluativas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecnicasEvaluativas createUpdatedEntity(EntityManager em) {
        TecnicasEvaluativas tecnicasEvaluativas = new TecnicasEvaluativas()
            .idTecnicasEvaluativas(UPDATED_ID_TECNICAS_EVALUATIVAS)
            .teNombre(UPDATED_TE_NOMBRE)
            .teFechaCreacion(UPDATED_TE_FECHA_CREACION)
            .teFechaModificacion(UPDATED_TE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idTecnicasEvaluativasXItem(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM);
        return tecnicasEvaluativas;
    }

    @BeforeEach
    public void initTest() {
        tecnicasEvaluativas = createEntity(em);
    }

    @Test
    @Transactional
    public void createTecnicasEvaluativas() throws Exception {
        int databaseSizeBeforeCreate = tecnicasEvaluativasRepository.findAll().size();
        // Create the TecnicasEvaluativas
        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isCreated());

        // Validate the TecnicasEvaluativas in the database
        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeCreate + 1);
        TecnicasEvaluativas testTecnicasEvaluativas = tecnicasEvaluativasList.get(tecnicasEvaluativasList.size() - 1);
        assertThat(testTecnicasEvaluativas.getIdTecnicasEvaluativas()).isEqualTo(DEFAULT_ID_TECNICAS_EVALUATIVAS);
        assertThat(testTecnicasEvaluativas.getTeNombre()).isEqualTo(DEFAULT_TE_NOMBRE);
        assertThat(testTecnicasEvaluativas.getTeFechaCreacion()).isEqualTo(DEFAULT_TE_FECHA_CREACION);
        assertThat(testTecnicasEvaluativas.getTeFechaModificacion()).isEqualTo(DEFAULT_TE_FECHA_MODIFICACION);
        assertThat(testTecnicasEvaluativas.getIdAutoevalucionXproceso()).isEqualTo(DEFAULT_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testTecnicasEvaluativas.getIdAutoevalucion()).isEqualTo(DEFAULT_ID_AUTOEVALUCION);
        assertThat(testTecnicasEvaluativas.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testTecnicasEvaluativas.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testTecnicasEvaluativas.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
        assertThat(testTecnicasEvaluativas.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
        assertThat(testTecnicasEvaluativas.getIdTecnicasEvaluativasXItem()).isEqualTo(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM);
    }

    @Test
    @Transactional
    public void createTecnicasEvaluativasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tecnicasEvaluativasRepository.findAll().size();

        // Create the TecnicasEvaluativas with an existing ID
        tecnicasEvaluativas.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        // Validate the TecnicasEvaluativas in the database
        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdTecnicasEvaluativasIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdTecnicasEvaluativas(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setTeNombre(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setTeFechaCreacion(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTeFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setTeFechaModificacion(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionXprocesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdAutoevalucionXproceso(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdAutoevalucion(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdMes(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdUsuariosXevaluacion(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdEstado(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdVerificador(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdTecnicasEvaluativasXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasRepository.findAll().size();
        // set the field null
        tecnicasEvaluativas.setIdTecnicasEvaluativasXItem(null);

        // Create the TecnicasEvaluativas, which fails.


        restTecnicasEvaluativasMockMvc.perform(post("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTecnicasEvaluativas() throws Exception {
        // Initialize the database
        tecnicasEvaluativasRepository.saveAndFlush(tecnicasEvaluativas);

        // Get all the tecnicasEvaluativasList
        restTecnicasEvaluativasMockMvc.perform(get("/api/tecnicas-evaluativas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tecnicasEvaluativas.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTecnicasEvaluativas").value(hasItem(DEFAULT_ID_TECNICAS_EVALUATIVAS)))
            .andExpect(jsonPath("$.[*].teNombre").value(hasItem(DEFAULT_TE_NOMBRE)))
            .andExpect(jsonPath("$.[*].teFechaCreacion").value(hasItem(DEFAULT_TE_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].teFechaModificacion").value(hasItem(DEFAULT_TE_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].idAutoevalucionXproceso").value(hasItem(DEFAULT_ID_AUTOEVALUCION_XPROCESO)))
            .andExpect(jsonPath("$.[*].idAutoevalucion").value(hasItem(DEFAULT_ID_AUTOEVALUCION)))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)))
            .andExpect(jsonPath("$.[*].idTecnicasEvaluativasXItem").value(hasItem(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM)));
    }
    
    @Test
    @Transactional
    public void getTecnicasEvaluativas() throws Exception {
        // Initialize the database
        tecnicasEvaluativasRepository.saveAndFlush(tecnicasEvaluativas);

        // Get the tecnicasEvaluativas
        restTecnicasEvaluativasMockMvc.perform(get("/api/tecnicas-evaluativas/{id}", tecnicasEvaluativas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tecnicasEvaluativas.getId().intValue()))
            .andExpect(jsonPath("$.idTecnicasEvaluativas").value(DEFAULT_ID_TECNICAS_EVALUATIVAS))
            .andExpect(jsonPath("$.teNombre").value(DEFAULT_TE_NOMBRE))
            .andExpect(jsonPath("$.teFechaCreacion").value(DEFAULT_TE_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.teFechaModificacion").value(DEFAULT_TE_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.idAutoevalucionXproceso").value(DEFAULT_ID_AUTOEVALUCION_XPROCESO))
            .andExpect(jsonPath("$.idAutoevalucion").value(DEFAULT_ID_AUTOEVALUCION))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR))
            .andExpect(jsonPath("$.idTecnicasEvaluativasXItem").value(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM));
    }
    @Test
    @Transactional
    public void getNonExistingTecnicasEvaluativas() throws Exception {
        // Get the tecnicasEvaluativas
        restTecnicasEvaluativasMockMvc.perform(get("/api/tecnicas-evaluativas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTecnicasEvaluativas() throws Exception {
        // Initialize the database
        tecnicasEvaluativasRepository.saveAndFlush(tecnicasEvaluativas);

        int databaseSizeBeforeUpdate = tecnicasEvaluativasRepository.findAll().size();

        // Update the tecnicasEvaluativas
        TecnicasEvaluativas updatedTecnicasEvaluativas = tecnicasEvaluativasRepository.findById(tecnicasEvaluativas.getId()).get();
        // Disconnect from session so that the updates on updatedTecnicasEvaluativas are not directly saved in db
        em.detach(updatedTecnicasEvaluativas);
        updatedTecnicasEvaluativas
            .idTecnicasEvaluativas(UPDATED_ID_TECNICAS_EVALUATIVAS)
            .teNombre(UPDATED_TE_NOMBRE)
            .teFechaCreacion(UPDATED_TE_FECHA_CREACION)
            .teFechaModificacion(UPDATED_TE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idTecnicasEvaluativasXItem(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM);

        restTecnicasEvaluativasMockMvc.perform(put("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTecnicasEvaluativas)))
            .andExpect(status().isOk());

        // Validate the TecnicasEvaluativas in the database
        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeUpdate);
        TecnicasEvaluativas testTecnicasEvaluativas = tecnicasEvaluativasList.get(tecnicasEvaluativasList.size() - 1);
        assertThat(testTecnicasEvaluativas.getIdTecnicasEvaluativas()).isEqualTo(UPDATED_ID_TECNICAS_EVALUATIVAS);
        assertThat(testTecnicasEvaluativas.getTeNombre()).isEqualTo(UPDATED_TE_NOMBRE);
        assertThat(testTecnicasEvaluativas.getTeFechaCreacion()).isEqualTo(UPDATED_TE_FECHA_CREACION);
        assertThat(testTecnicasEvaluativas.getTeFechaModificacion()).isEqualTo(UPDATED_TE_FECHA_MODIFICACION);
        assertThat(testTecnicasEvaluativas.getIdAutoevalucionXproceso()).isEqualTo(UPDATED_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testTecnicasEvaluativas.getIdAutoevalucion()).isEqualTo(UPDATED_ID_AUTOEVALUCION);
        assertThat(testTecnicasEvaluativas.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testTecnicasEvaluativas.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testTecnicasEvaluativas.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
        assertThat(testTecnicasEvaluativas.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
        assertThat(testTecnicasEvaluativas.getIdTecnicasEvaluativasXItem()).isEqualTo(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM);
    }

    @Test
    @Transactional
    public void updateNonExistingTecnicasEvaluativas() throws Exception {
        int databaseSizeBeforeUpdate = tecnicasEvaluativasRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecnicasEvaluativasMockMvc.perform(put("/api/tecnicas-evaluativas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativas)))
            .andExpect(status().isBadRequest());

        // Validate the TecnicasEvaluativas in the database
        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTecnicasEvaluativas() throws Exception {
        // Initialize the database
        tecnicasEvaluativasRepository.saveAndFlush(tecnicasEvaluativas);

        int databaseSizeBeforeDelete = tecnicasEvaluativasRepository.findAll().size();

        // Delete the tecnicasEvaluativas
        restTecnicasEvaluativasMockMvc.perform(delete("/api/tecnicas-evaluativas/{id}", tecnicasEvaluativas.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TecnicasEvaluativas> tecnicasEvaluativasList = tecnicasEvaluativasRepository.findAll();
        assertThat(tecnicasEvaluativasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
