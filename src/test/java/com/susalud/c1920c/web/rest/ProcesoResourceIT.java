package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Proceso;
import com.susalud.c1920c.repository.ProcesoRepository;

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
 * Integration tests for the {@link ProcesoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProcesoResourceIT {

    private static final Integer DEFAULT_ID_PROCESO = 1;
    private static final Integer UPDATED_ID_PROCESO = 2;

    private static final String DEFAULT_PR_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_PR_DESCRIPCION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ID_MACROPROCESO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ID_MACROPROCESO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ID_VERIFICADOR = 1;
    private static final Integer UPDATED_ID_VERIFICADOR = 2;

    @Autowired
    private ProcesoRepository procesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProcesoMockMvc;

    private Proceso proceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proceso createEntity(EntityManager em) {
        Proceso proceso = new Proceso()
            .idProceso(DEFAULT_ID_PROCESO)
            .prDescripcion(DEFAULT_PR_DESCRIPCION)
            .idMacroproceso(DEFAULT_ID_MACROPROCESO)
            .idVerificador(DEFAULT_ID_VERIFICADOR);
        return proceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Proceso createUpdatedEntity(EntityManager em) {
        Proceso proceso = new Proceso()
            .idProceso(UPDATED_ID_PROCESO)
            .prDescripcion(UPDATED_PR_DESCRIPCION)
            .idMacroproceso(UPDATED_ID_MACROPROCESO)
            .idVerificador(UPDATED_ID_VERIFICADOR);
        return proceso;
    }

    @BeforeEach
    public void initTest() {
        proceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createProceso() throws Exception {
        int databaseSizeBeforeCreate = procesoRepository.findAll().size();
        // Create the Proceso
        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isCreated());

        // Validate the Proceso in the database
        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeCreate + 1);
        Proceso testProceso = procesoList.get(procesoList.size() - 1);
        assertThat(testProceso.getIdProceso()).isEqualTo(DEFAULT_ID_PROCESO);
        assertThat(testProceso.getPrDescripcion()).isEqualTo(DEFAULT_PR_DESCRIPCION);
        assertThat(testProceso.getIdMacroproceso()).isEqualTo(DEFAULT_ID_MACROPROCESO);
        assertThat(testProceso.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void createProcesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = procesoRepository.findAll().size();

        // Create the Proceso with an existing ID
        proceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        // Validate the Proceso in the database
        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdProcesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = procesoRepository.findAll().size();
        // set the field null
        proceso.setIdProceso(null);

        // Create the Proceso, which fails.


        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = procesoRepository.findAll().size();
        // set the field null
        proceso.setPrDescripcion(null);

        // Create the Proceso, which fails.


        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdMacroprocesoIsRequired() throws Exception {
        int databaseSizeBeforeTest = procesoRepository.findAll().size();
        // set the field null
        proceso.setIdMacroproceso(null);

        // Create the Proceso, which fails.


        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = procesoRepository.findAll().size();
        // set the field null
        proceso.setIdVerificador(null);

        // Create the Proceso, which fails.


        restProcesoMockMvc.perform(post("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProcesos() throws Exception {
        // Initialize the database
        procesoRepository.saveAndFlush(proceso);

        // Get all the procesoList
        restProcesoMockMvc.perform(get("/api/procesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(proceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idProceso").value(hasItem(DEFAULT_ID_PROCESO)))
            .andExpect(jsonPath("$.[*].prDescripcion").value(hasItem(DEFAULT_PR_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].idMacroproceso").value(hasItem(DEFAULT_ID_MACROPROCESO.toString())))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getProceso() throws Exception {
        // Initialize the database
        procesoRepository.saveAndFlush(proceso);

        // Get the proceso
        restProcesoMockMvc.perform(get("/api/procesos/{id}", proceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(proceso.getId().intValue()))
            .andExpect(jsonPath("$.idProceso").value(DEFAULT_ID_PROCESO))
            .andExpect(jsonPath("$.prDescripcion").value(DEFAULT_PR_DESCRIPCION))
            .andExpect(jsonPath("$.idMacroproceso").value(DEFAULT_ID_MACROPROCESO.toString()))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingProceso() throws Exception {
        // Get the proceso
        restProcesoMockMvc.perform(get("/api/procesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProceso() throws Exception {
        // Initialize the database
        procesoRepository.saveAndFlush(proceso);

        int databaseSizeBeforeUpdate = procesoRepository.findAll().size();

        // Update the proceso
        Proceso updatedProceso = procesoRepository.findById(proceso.getId()).get();
        // Disconnect from session so that the updates on updatedProceso are not directly saved in db
        em.detach(updatedProceso);
        updatedProceso
            .idProceso(UPDATED_ID_PROCESO)
            .prDescripcion(UPDATED_PR_DESCRIPCION)
            .idMacroproceso(UPDATED_ID_MACROPROCESO)
            .idVerificador(UPDATED_ID_VERIFICADOR);

        restProcesoMockMvc.perform(put("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProceso)))
            .andExpect(status().isOk());

        // Validate the Proceso in the database
        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeUpdate);
        Proceso testProceso = procesoList.get(procesoList.size() - 1);
        assertThat(testProceso.getIdProceso()).isEqualTo(UPDATED_ID_PROCESO);
        assertThat(testProceso.getPrDescripcion()).isEqualTo(UPDATED_PR_DESCRIPCION);
        assertThat(testProceso.getIdMacroproceso()).isEqualTo(UPDATED_ID_MACROPROCESO);
        assertThat(testProceso.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingProceso() throws Exception {
        int databaseSizeBeforeUpdate = procesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProcesoMockMvc.perform(put("/api/procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(proceso)))
            .andExpect(status().isBadRequest());

        // Validate the Proceso in the database
        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProceso() throws Exception {
        // Initialize the database
        procesoRepository.saveAndFlush(proceso);

        int databaseSizeBeforeDelete = procesoRepository.findAll().size();

        // Delete the proceso
        restProcesoMockMvc.perform(delete("/api/procesos/{id}", proceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Proceso> procesoList = procesoRepository.findAll();
        assertThat(procesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
