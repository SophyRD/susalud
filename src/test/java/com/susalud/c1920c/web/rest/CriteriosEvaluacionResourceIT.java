package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.CriteriosEvaluacion;
import com.susalud.c1920c.repository.CriteriosEvaluacionRepository;

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
 * Integration tests for the {@link CriteriosEvaluacionResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CriteriosEvaluacionResourceIT {

    private static final Integer DEFAULT_ID_CRITERIOS_EVALUACION = 1;
    private static final Integer UPDATED_ID_CRITERIOS_EVALUACION = 2;

    private static final String DEFAULT_CE_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_CE_NOMBRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CE_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CE_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CE_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CE_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

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

    private static final Integer DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM = 1;
    private static final Integer UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM = 2;

    @Autowired
    private CriteriosEvaluacionRepository criteriosEvaluacionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCriteriosEvaluacionMockMvc;

    private CriteriosEvaluacion criteriosEvaluacion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriteriosEvaluacion createEntity(EntityManager em) {
        CriteriosEvaluacion criteriosEvaluacion = new CriteriosEvaluacion()
            .idCriteriosEvaluacion(DEFAULT_ID_CRITERIOS_EVALUACION)
            .ceNombre(DEFAULT_CE_NOMBRE)
            .ceFechaCreacion(DEFAULT_CE_FECHA_CREACION)
            .ceFechaModificacion(DEFAULT_CE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(DEFAULT_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(DEFAULT_ID_AUTOEVALUCION)
            .idMes(DEFAULT_ID_MES)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .idEstado(DEFAULT_ID_ESTADO)
            .idVerificador(DEFAULT_ID_VERIFICADOR)
            .idCriteriosEvaluacionXItem(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM);
        return criteriosEvaluacion;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriteriosEvaluacion createUpdatedEntity(EntityManager em) {
        CriteriosEvaluacion criteriosEvaluacion = new CriteriosEvaluacion()
            .idCriteriosEvaluacion(UPDATED_ID_CRITERIOS_EVALUACION)
            .ceNombre(UPDATED_CE_NOMBRE)
            .ceFechaCreacion(UPDATED_CE_FECHA_CREACION)
            .ceFechaModificacion(UPDATED_CE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idCriteriosEvaluacionXItem(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM);
        return criteriosEvaluacion;
    }

    @BeforeEach
    public void initTest() {
        criteriosEvaluacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createCriteriosEvaluacion() throws Exception {
        int databaseSizeBeforeCreate = criteriosEvaluacionRepository.findAll().size();
        // Create the CriteriosEvaluacion
        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isCreated());

        // Validate the CriteriosEvaluacion in the database
        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeCreate + 1);
        CriteriosEvaluacion testCriteriosEvaluacion = criteriosEvaluacionList.get(criteriosEvaluacionList.size() - 1);
        assertThat(testCriteriosEvaluacion.getIdCriteriosEvaluacion()).isEqualTo(DEFAULT_ID_CRITERIOS_EVALUACION);
        assertThat(testCriteriosEvaluacion.getCeNombre()).isEqualTo(DEFAULT_CE_NOMBRE);
        assertThat(testCriteriosEvaluacion.getCeFechaCreacion()).isEqualTo(DEFAULT_CE_FECHA_CREACION);
        assertThat(testCriteriosEvaluacion.getCeFechaModificacion()).isEqualTo(DEFAULT_CE_FECHA_MODIFICACION);
        assertThat(testCriteriosEvaluacion.getIdAutoevalucionXproceso()).isEqualTo(DEFAULT_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testCriteriosEvaluacion.getIdAutoevalucion()).isEqualTo(DEFAULT_ID_AUTOEVALUCION);
        assertThat(testCriteriosEvaluacion.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testCriteriosEvaluacion.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testCriteriosEvaluacion.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
        assertThat(testCriteriosEvaluacion.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
        assertThat(testCriteriosEvaluacion.getIdCriteriosEvaluacionXItem()).isEqualTo(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM);
    }

    @Test
    @Transactional
    public void createCriteriosEvaluacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = criteriosEvaluacionRepository.findAll().size();

        // Create the CriteriosEvaluacion with an existing ID
        criteriosEvaluacion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        // Validate the CriteriosEvaluacion in the database
        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdCriteriosEvaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdCriteriosEvaluacion(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setCeNombre(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setCeFechaCreacion(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setCeFechaModificacion(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionXprocesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdAutoevalucionXproceso(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdAutoevalucion(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdMes(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdUsuariosXevaluacion(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdEstado(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdVerificador(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdCriteriosEvaluacionXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionRepository.findAll().size();
        // set the field null
        criteriosEvaluacion.setIdCriteriosEvaluacionXItem(null);

        // Create the CriteriosEvaluacion, which fails.


        restCriteriosEvaluacionMockMvc.perform(post("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCriteriosEvaluacions() throws Exception {
        // Initialize the database
        criteriosEvaluacionRepository.saveAndFlush(criteriosEvaluacion);

        // Get all the criteriosEvaluacionList
        restCriteriosEvaluacionMockMvc.perform(get("/api/criterios-evaluacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(criteriosEvaluacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCriteriosEvaluacion").value(hasItem(DEFAULT_ID_CRITERIOS_EVALUACION)))
            .andExpect(jsonPath("$.[*].ceNombre").value(hasItem(DEFAULT_CE_NOMBRE)))
            .andExpect(jsonPath("$.[*].ceFechaCreacion").value(hasItem(DEFAULT_CE_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].ceFechaModificacion").value(hasItem(DEFAULT_CE_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].idAutoevalucionXproceso").value(hasItem(DEFAULT_ID_AUTOEVALUCION_XPROCESO)))
            .andExpect(jsonPath("$.[*].idAutoevalucion").value(hasItem(DEFAULT_ID_AUTOEVALUCION)))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)))
            .andExpect(jsonPath("$.[*].idCriteriosEvaluacionXItem").value(hasItem(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM)));
    }
    
    @Test
    @Transactional
    public void getCriteriosEvaluacion() throws Exception {
        // Initialize the database
        criteriosEvaluacionRepository.saveAndFlush(criteriosEvaluacion);

        // Get the criteriosEvaluacion
        restCriteriosEvaluacionMockMvc.perform(get("/api/criterios-evaluacions/{id}", criteriosEvaluacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(criteriosEvaluacion.getId().intValue()))
            .andExpect(jsonPath("$.idCriteriosEvaluacion").value(DEFAULT_ID_CRITERIOS_EVALUACION))
            .andExpect(jsonPath("$.ceNombre").value(DEFAULT_CE_NOMBRE))
            .andExpect(jsonPath("$.ceFechaCreacion").value(DEFAULT_CE_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.ceFechaModificacion").value(DEFAULT_CE_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.idAutoevalucionXproceso").value(DEFAULT_ID_AUTOEVALUCION_XPROCESO))
            .andExpect(jsonPath("$.idAutoevalucion").value(DEFAULT_ID_AUTOEVALUCION))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR))
            .andExpect(jsonPath("$.idCriteriosEvaluacionXItem").value(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM));
    }
    @Test
    @Transactional
    public void getNonExistingCriteriosEvaluacion() throws Exception {
        // Get the criteriosEvaluacion
        restCriteriosEvaluacionMockMvc.perform(get("/api/criterios-evaluacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCriteriosEvaluacion() throws Exception {
        // Initialize the database
        criteriosEvaluacionRepository.saveAndFlush(criteriosEvaluacion);

        int databaseSizeBeforeUpdate = criteriosEvaluacionRepository.findAll().size();

        // Update the criteriosEvaluacion
        CriteriosEvaluacion updatedCriteriosEvaluacion = criteriosEvaluacionRepository.findById(criteriosEvaluacion.getId()).get();
        // Disconnect from session so that the updates on updatedCriteriosEvaluacion are not directly saved in db
        em.detach(updatedCriteriosEvaluacion);
        updatedCriteriosEvaluacion
            .idCriteriosEvaluacion(UPDATED_ID_CRITERIOS_EVALUACION)
            .ceNombre(UPDATED_CE_NOMBRE)
            .ceFechaCreacion(UPDATED_CE_FECHA_CREACION)
            .ceFechaModificacion(UPDATED_CE_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idCriteriosEvaluacionXItem(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM);

        restCriteriosEvaluacionMockMvc.perform(put("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCriteriosEvaluacion)))
            .andExpect(status().isOk());

        // Validate the CriteriosEvaluacion in the database
        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeUpdate);
        CriteriosEvaluacion testCriteriosEvaluacion = criteriosEvaluacionList.get(criteriosEvaluacionList.size() - 1);
        assertThat(testCriteriosEvaluacion.getIdCriteriosEvaluacion()).isEqualTo(UPDATED_ID_CRITERIOS_EVALUACION);
        assertThat(testCriteriosEvaluacion.getCeNombre()).isEqualTo(UPDATED_CE_NOMBRE);
        assertThat(testCriteriosEvaluacion.getCeFechaCreacion()).isEqualTo(UPDATED_CE_FECHA_CREACION);
        assertThat(testCriteriosEvaluacion.getCeFechaModificacion()).isEqualTo(UPDATED_CE_FECHA_MODIFICACION);
        assertThat(testCriteriosEvaluacion.getIdAutoevalucionXproceso()).isEqualTo(UPDATED_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testCriteriosEvaluacion.getIdAutoevalucion()).isEqualTo(UPDATED_ID_AUTOEVALUCION);
        assertThat(testCriteriosEvaluacion.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testCriteriosEvaluacion.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testCriteriosEvaluacion.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
        assertThat(testCriteriosEvaluacion.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
        assertThat(testCriteriosEvaluacion.getIdCriteriosEvaluacionXItem()).isEqualTo(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM);
    }

    @Test
    @Transactional
    public void updateNonExistingCriteriosEvaluacion() throws Exception {
        int databaseSizeBeforeUpdate = criteriosEvaluacionRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriteriosEvaluacionMockMvc.perform(put("/api/criterios-evaluacions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacion)))
            .andExpect(status().isBadRequest());

        // Validate the CriteriosEvaluacion in the database
        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCriteriosEvaluacion() throws Exception {
        // Initialize the database
        criteriosEvaluacionRepository.saveAndFlush(criteriosEvaluacion);

        int databaseSizeBeforeDelete = criteriosEvaluacionRepository.findAll().size();

        // Delete the criteriosEvaluacion
        restCriteriosEvaluacionMockMvc.perform(delete("/api/criterios-evaluacions/{id}", criteriosEvaluacion.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CriteriosEvaluacion> criteriosEvaluacionList = criteriosEvaluacionRepository.findAll();
        assertThat(criteriosEvaluacionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
