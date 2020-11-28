package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProyectoApp;
import com.mycompany.myapp.domain.Macroproceso;
import com.mycompany.myapp.repository.MacroprocesoRepository;

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
 * Integration tests for the {@link MacroprocesoResource} REST controller.
 */
@SpringBootTest(classes = ProyectoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MacroprocesoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private MacroprocesoRepository macroprocesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMacroprocesoMockMvc;

    private Macroproceso macroproceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Macroproceso createEntity(EntityManager em) {
        Macroproceso macroproceso = new Macroproceso()
            .descripcion(DEFAULT_DESCRIPCION);
        return macroproceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Macroproceso createUpdatedEntity(EntityManager em) {
        Macroproceso macroproceso = new Macroproceso()
            .descripcion(UPDATED_DESCRIPCION);
        return macroproceso;
    }

    @BeforeEach
    public void initTest() {
        macroproceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createMacroproceso() throws Exception {
        int databaseSizeBeforeCreate = macroprocesoRepository.findAll().size();
        // Create the Macroproceso
        restMacroprocesoMockMvc.perform(post("/api/macroprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroproceso)))
            .andExpect(status().isCreated());

        // Validate the Macroproceso in the database
        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeCreate + 1);
        Macroproceso testMacroproceso = macroprocesoList.get(macroprocesoList.size() - 1);
        assertThat(testMacroproceso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createMacroprocesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = macroprocesoRepository.findAll().size();

        // Create the Macroproceso with an existing ID
        macroproceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMacroprocesoMockMvc.perform(post("/api/macroprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroproceso)))
            .andExpect(status().isBadRequest());

        // Validate the Macroproceso in the database
        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = macroprocesoRepository.findAll().size();
        // set the field null
        macroproceso.setDescripcion(null);

        // Create the Macroproceso, which fails.


        restMacroprocesoMockMvc.perform(post("/api/macroprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroproceso)))
            .andExpect(status().isBadRequest());

        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMacroprocesos() throws Exception {
        // Initialize the database
        macroprocesoRepository.saveAndFlush(macroproceso);

        // Get all the macroprocesoList
        restMacroprocesoMockMvc.perform(get("/api/macroprocesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(macroproceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getMacroproceso() throws Exception {
        // Initialize the database
        macroprocesoRepository.saveAndFlush(macroproceso);

        // Get the macroproceso
        restMacroprocesoMockMvc.perform(get("/api/macroprocesos/{id}", macroproceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(macroproceso.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }
    @Test
    @Transactional
    public void getNonExistingMacroproceso() throws Exception {
        // Get the macroproceso
        restMacroprocesoMockMvc.perform(get("/api/macroprocesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMacroproceso() throws Exception {
        // Initialize the database
        macroprocesoRepository.saveAndFlush(macroproceso);

        int databaseSizeBeforeUpdate = macroprocesoRepository.findAll().size();

        // Update the macroproceso
        Macroproceso updatedMacroproceso = macroprocesoRepository.findById(macroproceso.getId()).get();
        // Disconnect from session so that the updates on updatedMacroproceso are not directly saved in db
        em.detach(updatedMacroproceso);
        updatedMacroproceso
            .descripcion(UPDATED_DESCRIPCION);

        restMacroprocesoMockMvc.perform(put("/api/macroprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMacroproceso)))
            .andExpect(status().isOk());

        // Validate the Macroproceso in the database
        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeUpdate);
        Macroproceso testMacroproceso = macroprocesoList.get(macroprocesoList.size() - 1);
        assertThat(testMacroproceso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingMacroproceso() throws Exception {
        int databaseSizeBeforeUpdate = macroprocesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMacroprocesoMockMvc.perform(put("/api/macroprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroproceso)))
            .andExpect(status().isBadRequest());

        // Validate the Macroproceso in the database
        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMacroproceso() throws Exception {
        // Initialize the database
        macroprocesoRepository.saveAndFlush(macroproceso);

        int databaseSizeBeforeDelete = macroprocesoRepository.findAll().size();

        // Delete the macroproceso
        restMacroprocesoMockMvc.perform(delete("/api/macroprocesos/{id}", macroproceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Macroproceso> macroprocesoList = macroprocesoRepository.findAll();
        assertThat(macroprocesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
