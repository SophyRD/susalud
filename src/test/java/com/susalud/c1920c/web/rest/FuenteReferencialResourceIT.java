package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.FuenteReferencial;
import com.susalud.c1920c.repository.FuenteReferencialRepository;

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
 * Integration tests for the {@link FuenteReferencialResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FuenteReferencialResourceIT {

    private static final Integer DEFAULT_ID_FUENTE_REFERENCIAL = 1;
    private static final Integer UPDATED_ID_FUENTE_REFERENCIAL = 2;

    private static final String DEFAULT_FR_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_FR_NOMBRE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FR_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FR_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FR_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FR_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

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

    private static final Integer DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM = 1;
    private static final Integer UPDATED_ID_FUENTE_REFERENCIA_X_ITEM = 2;

    @Autowired
    private FuenteReferencialRepository fuenteReferencialRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFuenteReferencialMockMvc;

    private FuenteReferencial fuenteReferencial;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FuenteReferencial createEntity(EntityManager em) {
        FuenteReferencial fuenteReferencial = new FuenteReferencial()
            .idFuenteReferencial(DEFAULT_ID_FUENTE_REFERENCIAL)
            .frNombre(DEFAULT_FR_NOMBRE)
            .frFechaCreacion(DEFAULT_FR_FECHA_CREACION)
            .frFechaModificacion(DEFAULT_FR_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(DEFAULT_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(DEFAULT_ID_AUTOEVALUCION)
            .idMes(DEFAULT_ID_MES)
            .idUsuariosXevaluacion(DEFAULT_ID_USUARIOS_XEVALUACION)
            .idEstado(DEFAULT_ID_ESTADO)
            .idVerificador(DEFAULT_ID_VERIFICADOR)
            .idFuenteReferenciaXItem(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM);
        return fuenteReferencial;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FuenteReferencial createUpdatedEntity(EntityManager em) {
        FuenteReferencial fuenteReferencial = new FuenteReferencial()
            .idFuenteReferencial(UPDATED_ID_FUENTE_REFERENCIAL)
            .frNombre(UPDATED_FR_NOMBRE)
            .frFechaCreacion(UPDATED_FR_FECHA_CREACION)
            .frFechaModificacion(UPDATED_FR_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idFuenteReferenciaXItem(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM);
        return fuenteReferencial;
    }

    @BeforeEach
    public void initTest() {
        fuenteReferencial = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuenteReferencial() throws Exception {
        int databaseSizeBeforeCreate = fuenteReferencialRepository.findAll().size();
        // Create the FuenteReferencial
        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isCreated());

        // Validate the FuenteReferencial in the database
        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeCreate + 1);
        FuenteReferencial testFuenteReferencial = fuenteReferencialList.get(fuenteReferencialList.size() - 1);
        assertThat(testFuenteReferencial.getIdFuenteReferencial()).isEqualTo(DEFAULT_ID_FUENTE_REFERENCIAL);
        assertThat(testFuenteReferencial.getFrNombre()).isEqualTo(DEFAULT_FR_NOMBRE);
        assertThat(testFuenteReferencial.getFrFechaCreacion()).isEqualTo(DEFAULT_FR_FECHA_CREACION);
        assertThat(testFuenteReferencial.getFrFechaModificacion()).isEqualTo(DEFAULT_FR_FECHA_MODIFICACION);
        assertThat(testFuenteReferencial.getIdAutoevalucionXproceso()).isEqualTo(DEFAULT_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testFuenteReferencial.getIdAutoevalucion()).isEqualTo(DEFAULT_ID_AUTOEVALUCION);
        assertThat(testFuenteReferencial.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testFuenteReferencial.getIdUsuariosXevaluacion()).isEqualTo(DEFAULT_ID_USUARIOS_XEVALUACION);
        assertThat(testFuenteReferencial.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
        assertThat(testFuenteReferencial.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
        assertThat(testFuenteReferencial.getIdFuenteReferenciaXItem()).isEqualTo(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM);
    }

    @Test
    @Transactional
    public void createFuenteReferencialWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fuenteReferencialRepository.findAll().size();

        // Create the FuenteReferencial with an existing ID
        fuenteReferencial.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        // Validate the FuenteReferencial in the database
        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdFuenteReferencialIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdFuenteReferencial(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setFrNombre(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setFrFechaCreacion(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setFrFechaModificacion(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionXprocesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdAutoevalucionXproceso(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdAutoevalucionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdAutoevalucion(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdMes(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdUsuariosXevaluacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdUsuariosXevaluacion(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdEstado(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdVerificador(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdFuenteReferenciaXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferencialRepository.findAll().size();
        // set the field null
        fuenteReferencial.setIdFuenteReferenciaXItem(null);

        // Create the FuenteReferencial, which fails.


        restFuenteReferencialMockMvc.perform(post("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuenteReferencials() throws Exception {
        // Initialize the database
        fuenteReferencialRepository.saveAndFlush(fuenteReferencial);

        // Get all the fuenteReferencialList
        restFuenteReferencialMockMvc.perform(get("/api/fuente-referencials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fuenteReferencial.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFuenteReferencial").value(hasItem(DEFAULT_ID_FUENTE_REFERENCIAL)))
            .andExpect(jsonPath("$.[*].frNombre").value(hasItem(DEFAULT_FR_NOMBRE)))
            .andExpect(jsonPath("$.[*].frFechaCreacion").value(hasItem(DEFAULT_FR_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].frFechaModificacion").value(hasItem(DEFAULT_FR_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].idAutoevalucionXproceso").value(hasItem(DEFAULT_ID_AUTOEVALUCION_XPROCESO)))
            .andExpect(jsonPath("$.[*].idAutoevalucion").value(hasItem(DEFAULT_ID_AUTOEVALUCION)))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].idUsuariosXevaluacion").value(hasItem(DEFAULT_ID_USUARIOS_XEVALUACION)))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)))
            .andExpect(jsonPath("$.[*].idFuenteReferenciaXItem").value(hasItem(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM)));
    }
    
    @Test
    @Transactional
    public void getFuenteReferencial() throws Exception {
        // Initialize the database
        fuenteReferencialRepository.saveAndFlush(fuenteReferencial);

        // Get the fuenteReferencial
        restFuenteReferencialMockMvc.perform(get("/api/fuente-referencials/{id}", fuenteReferencial.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fuenteReferencial.getId().intValue()))
            .andExpect(jsonPath("$.idFuenteReferencial").value(DEFAULT_ID_FUENTE_REFERENCIAL))
            .andExpect(jsonPath("$.frNombre").value(DEFAULT_FR_NOMBRE))
            .andExpect(jsonPath("$.frFechaCreacion").value(DEFAULT_FR_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.frFechaModificacion").value(DEFAULT_FR_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.idAutoevalucionXproceso").value(DEFAULT_ID_AUTOEVALUCION_XPROCESO))
            .andExpect(jsonPath("$.idAutoevalucion").value(DEFAULT_ID_AUTOEVALUCION))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.idUsuariosXevaluacion").value(DEFAULT_ID_USUARIOS_XEVALUACION))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR))
            .andExpect(jsonPath("$.idFuenteReferenciaXItem").value(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM));
    }
    @Test
    @Transactional
    public void getNonExistingFuenteReferencial() throws Exception {
        // Get the fuenteReferencial
        restFuenteReferencialMockMvc.perform(get("/api/fuente-referencials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuenteReferencial() throws Exception {
        // Initialize the database
        fuenteReferencialRepository.saveAndFlush(fuenteReferencial);

        int databaseSizeBeforeUpdate = fuenteReferencialRepository.findAll().size();

        // Update the fuenteReferencial
        FuenteReferencial updatedFuenteReferencial = fuenteReferencialRepository.findById(fuenteReferencial.getId()).get();
        // Disconnect from session so that the updates on updatedFuenteReferencial are not directly saved in db
        em.detach(updatedFuenteReferencial);
        updatedFuenteReferencial
            .idFuenteReferencial(UPDATED_ID_FUENTE_REFERENCIAL)
            .frNombre(UPDATED_FR_NOMBRE)
            .frFechaCreacion(UPDATED_FR_FECHA_CREACION)
            .frFechaModificacion(UPDATED_FR_FECHA_MODIFICACION)
            .idAutoevalucionXproceso(UPDATED_ID_AUTOEVALUCION_XPROCESO)
            .idAutoevalucion(UPDATED_ID_AUTOEVALUCION)
            .idMes(UPDATED_ID_MES)
            .idUsuariosXevaluacion(UPDATED_ID_USUARIOS_XEVALUACION)
            .idEstado(UPDATED_ID_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR)
            .idFuenteReferenciaXItem(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM);

        restFuenteReferencialMockMvc.perform(put("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuenteReferencial)))
            .andExpect(status().isOk());

        // Validate the FuenteReferencial in the database
        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeUpdate);
        FuenteReferencial testFuenteReferencial = fuenteReferencialList.get(fuenteReferencialList.size() - 1);
        assertThat(testFuenteReferencial.getIdFuenteReferencial()).isEqualTo(UPDATED_ID_FUENTE_REFERENCIAL);
        assertThat(testFuenteReferencial.getFrNombre()).isEqualTo(UPDATED_FR_NOMBRE);
        assertThat(testFuenteReferencial.getFrFechaCreacion()).isEqualTo(UPDATED_FR_FECHA_CREACION);
        assertThat(testFuenteReferencial.getFrFechaModificacion()).isEqualTo(UPDATED_FR_FECHA_MODIFICACION);
        assertThat(testFuenteReferencial.getIdAutoevalucionXproceso()).isEqualTo(UPDATED_ID_AUTOEVALUCION_XPROCESO);
        assertThat(testFuenteReferencial.getIdAutoevalucion()).isEqualTo(UPDATED_ID_AUTOEVALUCION);
        assertThat(testFuenteReferencial.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testFuenteReferencial.getIdUsuariosXevaluacion()).isEqualTo(UPDATED_ID_USUARIOS_XEVALUACION);
        assertThat(testFuenteReferencial.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
        assertThat(testFuenteReferencial.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
        assertThat(testFuenteReferencial.getIdFuenteReferenciaXItem()).isEqualTo(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM);
    }

    @Test
    @Transactional
    public void updateNonExistingFuenteReferencial() throws Exception {
        int databaseSizeBeforeUpdate = fuenteReferencialRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFuenteReferencialMockMvc.perform(put("/api/fuente-referencials")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferencial)))
            .andExpect(status().isBadRequest());

        // Validate the FuenteReferencial in the database
        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFuenteReferencial() throws Exception {
        // Initialize the database
        fuenteReferencialRepository.saveAndFlush(fuenteReferencial);

        int databaseSizeBeforeDelete = fuenteReferencialRepository.findAll().size();

        // Delete the fuenteReferencial
        restFuenteReferencialMockMvc.perform(delete("/api/fuente-referencials/{id}", fuenteReferencial.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FuenteReferencial> fuenteReferencialList = fuenteReferencialRepository.findAll();
        assertThat(fuenteReferencialList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
