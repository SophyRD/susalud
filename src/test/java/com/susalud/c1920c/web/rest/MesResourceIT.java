package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Mes;
import com.susalud.c1920c.repository.MesRepository;

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
 * Integration tests for the {@link MesResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MesResourceIT {

    private static final Integer DEFAULT_ID_MES = 1;
    private static final Integer UPDATED_ID_MES = 2;

    private static final Integer DEFAULT_M_MES = 1;
    private static final Integer UPDATED_M_MES = 2;

    private static final Integer DEFAULT_M_ANO = 1;
    private static final Integer UPDATED_M_ANO = 2;

    @Autowired
    private MesRepository mesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMesMockMvc;

    private Mes mes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mes createEntity(EntityManager em) {
        Mes mes = new Mes()
            .idMes(DEFAULT_ID_MES)
            .mMes(DEFAULT_M_MES)
            .mAno(DEFAULT_M_ANO);
        return mes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mes createUpdatedEntity(EntityManager em) {
        Mes mes = new Mes()
            .idMes(UPDATED_ID_MES)
            .mMes(UPDATED_M_MES)
            .mAno(UPDATED_M_ANO);
        return mes;
    }

    @BeforeEach
    public void initTest() {
        mes = createEntity(em);
    }

    @Test
    @Transactional
    public void createMes() throws Exception {
        int databaseSizeBeforeCreate = mesRepository.findAll().size();
        // Create the Mes
        restMesMockMvc.perform(post("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isCreated());

        // Validate the Mes in the database
        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeCreate + 1);
        Mes testMes = mesList.get(mesList.size() - 1);
        assertThat(testMes.getIdMes()).isEqualTo(DEFAULT_ID_MES);
        assertThat(testMes.getmMes()).isEqualTo(DEFAULT_M_MES);
        assertThat(testMes.getmAno()).isEqualTo(DEFAULT_M_ANO);
    }

    @Test
    @Transactional
    public void createMesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mesRepository.findAll().size();

        // Create the Mes with an existing ID
        mes.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMesMockMvc.perform(post("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isBadRequest());

        // Validate the Mes in the database
        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesRepository.findAll().size();
        // set the field null
        mes.setIdMes(null);

        // Create the Mes, which fails.


        restMesMockMvc.perform(post("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isBadRequest());

        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmMesIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesRepository.findAll().size();
        // set the field null
        mes.setmMes(null);

        // Create the Mes, which fails.


        restMesMockMvc.perform(post("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isBadRequest());

        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkmAnoIsRequired() throws Exception {
        int databaseSizeBeforeTest = mesRepository.findAll().size();
        // set the field null
        mes.setmAno(null);

        // Create the Mes, which fails.


        restMesMockMvc.perform(post("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isBadRequest());

        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMes() throws Exception {
        // Initialize the database
        mesRepository.saveAndFlush(mes);

        // Get all the mesList
        restMesMockMvc.perform(get("/api/mes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mes.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMes").value(hasItem(DEFAULT_ID_MES)))
            .andExpect(jsonPath("$.[*].mMes").value(hasItem(DEFAULT_M_MES)))
            .andExpect(jsonPath("$.[*].mAno").value(hasItem(DEFAULT_M_ANO)));
    }
    
    @Test
    @Transactional
    public void getMes() throws Exception {
        // Initialize the database
        mesRepository.saveAndFlush(mes);

        // Get the mes
        restMesMockMvc.perform(get("/api/mes/{id}", mes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mes.getId().intValue()))
            .andExpect(jsonPath("$.idMes").value(DEFAULT_ID_MES))
            .andExpect(jsonPath("$.mMes").value(DEFAULT_M_MES))
            .andExpect(jsonPath("$.mAno").value(DEFAULT_M_ANO));
    }
    @Test
    @Transactional
    public void getNonExistingMes() throws Exception {
        // Get the mes
        restMesMockMvc.perform(get("/api/mes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMes() throws Exception {
        // Initialize the database
        mesRepository.saveAndFlush(mes);

        int databaseSizeBeforeUpdate = mesRepository.findAll().size();

        // Update the mes
        Mes updatedMes = mesRepository.findById(mes.getId()).get();
        // Disconnect from session so that the updates on updatedMes are not directly saved in db
        em.detach(updatedMes);
        updatedMes
            .idMes(UPDATED_ID_MES)
            .mMes(UPDATED_M_MES)
            .mAno(UPDATED_M_ANO);

        restMesMockMvc.perform(put("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMes)))
            .andExpect(status().isOk());

        // Validate the Mes in the database
        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeUpdate);
        Mes testMes = mesList.get(mesList.size() - 1);
        assertThat(testMes.getIdMes()).isEqualTo(UPDATED_ID_MES);
        assertThat(testMes.getmMes()).isEqualTo(UPDATED_M_MES);
        assertThat(testMes.getmAno()).isEqualTo(UPDATED_M_ANO);
    }

    @Test
    @Transactional
    public void updateNonExistingMes() throws Exception {
        int databaseSizeBeforeUpdate = mesRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMesMockMvc.perform(put("/api/mes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mes)))
            .andExpect(status().isBadRequest());

        // Validate the Mes in the database
        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMes() throws Exception {
        // Initialize the database
        mesRepository.saveAndFlush(mes);

        int databaseSizeBeforeDelete = mesRepository.findAll().size();

        // Delete the mes
        restMesMockMvc.perform(delete("/api/mes/{id}", mes.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mes> mesList = mesRepository.findAll();
        assertThat(mesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
