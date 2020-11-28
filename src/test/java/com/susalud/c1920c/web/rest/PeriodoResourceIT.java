package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Periodo;
import com.susalud.c1920c.repository.PeriodoRepository;

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
 * Integration tests for the {@link PeriodoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PeriodoResourceIT {

    private static final Integer DEFAULT_ID_PERIODO = 1;
    private static final Integer UPDATED_ID_PERIODO = 2;

    private static final String DEFAULT_P_PERIODO = "AAAAAAAAAA";
    private static final String UPDATED_P_PERIODO = "BBBBBBBBBB";

    private static final Integer DEFAULT_MES_ID_MES = 1;
    private static final Integer UPDATED_MES_ID_MES = 2;

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPeriodoMockMvc;

    private Periodo periodo;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periodo createEntity(EntityManager em) {
        Periodo periodo = new Periodo()
            .idPeriodo(DEFAULT_ID_PERIODO)
            .pPeriodo(DEFAULT_P_PERIODO)
            .mesIdMes(DEFAULT_MES_ID_MES);
        return periodo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Periodo createUpdatedEntity(EntityManager em) {
        Periodo periodo = new Periodo()
            .idPeriodo(UPDATED_ID_PERIODO)
            .pPeriodo(UPDATED_P_PERIODO)
            .mesIdMes(UPDATED_MES_ID_MES);
        return periodo;
    }

    @BeforeEach
    public void initTest() {
        periodo = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriodo() throws Exception {
        int databaseSizeBeforeCreate = periodoRepository.findAll().size();
        // Create the Periodo
        restPeriodoMockMvc.perform(post("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isCreated());

        // Validate the Periodo in the database
        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeCreate + 1);
        Periodo testPeriodo = periodoList.get(periodoList.size() - 1);
        assertThat(testPeriodo.getIdPeriodo()).isEqualTo(DEFAULT_ID_PERIODO);
        assertThat(testPeriodo.getpPeriodo()).isEqualTo(DEFAULT_P_PERIODO);
        assertThat(testPeriodo.getMesIdMes()).isEqualTo(DEFAULT_MES_ID_MES);
    }

    @Test
    @Transactional
    public void createPeriodoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = periodoRepository.findAll().size();

        // Create the Periodo with an existing ID
        periodo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPeriodoMockMvc.perform(post("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isBadRequest());

        // Validate the Periodo in the database
        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoRepository.findAll().size();
        // set the field null
        periodo.setIdPeriodo(null);

        // Create the Periodo, which fails.


        restPeriodoMockMvc.perform(post("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isBadRequest());

        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkpPeriodoIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoRepository.findAll().size();
        // set the field null
        periodo.setpPeriodo(null);

        // Create the Periodo, which fails.


        restPeriodoMockMvc.perform(post("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isBadRequest());

        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMesIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = periodoRepository.findAll().size();
        // set the field null
        periodo.setMesIdMes(null);

        // Create the Periodo, which fails.


        restPeriodoMockMvc.perform(post("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isBadRequest());

        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeriodos() throws Exception {
        // Initialize the database
        periodoRepository.saveAndFlush(periodo);

        // Get all the periodoList
        restPeriodoMockMvc.perform(get("/api/periodos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(periodo.getId().intValue())))
            .andExpect(jsonPath("$.[*].idPeriodo").value(hasItem(DEFAULT_ID_PERIODO)))
            .andExpect(jsonPath("$.[*].pPeriodo").value(hasItem(DEFAULT_P_PERIODO)))
            .andExpect(jsonPath("$.[*].mesIdMes").value(hasItem(DEFAULT_MES_ID_MES)));
    }
    
    @Test
    @Transactional
    public void getPeriodo() throws Exception {
        // Initialize the database
        periodoRepository.saveAndFlush(periodo);

        // Get the periodo
        restPeriodoMockMvc.perform(get("/api/periodos/{id}", periodo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(periodo.getId().intValue()))
            .andExpect(jsonPath("$.idPeriodo").value(DEFAULT_ID_PERIODO))
            .andExpect(jsonPath("$.pPeriodo").value(DEFAULT_P_PERIODO))
            .andExpect(jsonPath("$.mesIdMes").value(DEFAULT_MES_ID_MES));
    }
    @Test
    @Transactional
    public void getNonExistingPeriodo() throws Exception {
        // Get the periodo
        restPeriodoMockMvc.perform(get("/api/periodos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriodo() throws Exception {
        // Initialize the database
        periodoRepository.saveAndFlush(periodo);

        int databaseSizeBeforeUpdate = periodoRepository.findAll().size();

        // Update the periodo
        Periodo updatedPeriodo = periodoRepository.findById(periodo.getId()).get();
        // Disconnect from session so that the updates on updatedPeriodo are not directly saved in db
        em.detach(updatedPeriodo);
        updatedPeriodo
            .idPeriodo(UPDATED_ID_PERIODO)
            .pPeriodo(UPDATED_P_PERIODO)
            .mesIdMes(UPDATED_MES_ID_MES);

        restPeriodoMockMvc.perform(put("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPeriodo)))
            .andExpect(status().isOk());

        // Validate the Periodo in the database
        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeUpdate);
        Periodo testPeriodo = periodoList.get(periodoList.size() - 1);
        assertThat(testPeriodo.getIdPeriodo()).isEqualTo(UPDATED_ID_PERIODO);
        assertThat(testPeriodo.getpPeriodo()).isEqualTo(UPDATED_P_PERIODO);
        assertThat(testPeriodo.getMesIdMes()).isEqualTo(UPDATED_MES_ID_MES);
    }

    @Test
    @Transactional
    public void updateNonExistingPeriodo() throws Exception {
        int databaseSizeBeforeUpdate = periodoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPeriodoMockMvc.perform(put("/api/periodos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(periodo)))
            .andExpect(status().isBadRequest());

        // Validate the Periodo in the database
        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePeriodo() throws Exception {
        // Initialize the database
        periodoRepository.saveAndFlush(periodo);

        int databaseSizeBeforeDelete = periodoRepository.findAll().size();

        // Delete the periodo
        restPeriodoMockMvc.perform(delete("/api/periodos/{id}", periodo.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Periodo> periodoList = periodoRepository.findAll();
        assertThat(periodoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
