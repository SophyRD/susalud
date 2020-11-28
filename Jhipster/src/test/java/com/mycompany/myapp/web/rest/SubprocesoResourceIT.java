package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProyectoApp;
import com.mycompany.myapp.domain.Subproceso;
import com.mycompany.myapp.repository.SubprocesoRepository;

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
 * Integration tests for the {@link SubprocesoResource} REST controller.
 */
@SpringBootTest(classes = ProyectoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SubprocesoResourceIT {

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private SubprocesoRepository subprocesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubprocesoMockMvc;

    private Subproceso subproceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subproceso createEntity(EntityManager em) {
        Subproceso subproceso = new Subproceso()
            .descripcion(DEFAULT_DESCRIPCION);
        return subproceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Subproceso createUpdatedEntity(EntityManager em) {
        Subproceso subproceso = new Subproceso()
            .descripcion(UPDATED_DESCRIPCION);
        return subproceso;
    }

    @BeforeEach
    public void initTest() {
        subproceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubproceso() throws Exception {
        int databaseSizeBeforeCreate = subprocesoRepository.findAll().size();
        // Create the Subproceso
        restSubprocesoMockMvc.perform(post("/api/subprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subproceso)))
            .andExpect(status().isCreated());

        // Validate the Subproceso in the database
        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeCreate + 1);
        Subproceso testSubproceso = subprocesoList.get(subprocesoList.size() - 1);
        assertThat(testSubproceso.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createSubprocesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subprocesoRepository.findAll().size();

        // Create the Subproceso with an existing ID
        subproceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubprocesoMockMvc.perform(post("/api/subprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subproceso)))
            .andExpect(status().isBadRequest());

        // Validate the Subproceso in the database
        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = subprocesoRepository.findAll().size();
        // set the field null
        subproceso.setDescripcion(null);

        // Create the Subproceso, which fails.


        restSubprocesoMockMvc.perform(post("/api/subprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subproceso)))
            .andExpect(status().isBadRequest());

        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSubprocesos() throws Exception {
        // Initialize the database
        subprocesoRepository.saveAndFlush(subproceso);

        // Get all the subprocesoList
        restSubprocesoMockMvc.perform(get("/api/subprocesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subproceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }
    
    @Test
    @Transactional
    public void getSubproceso() throws Exception {
        // Initialize the database
        subprocesoRepository.saveAndFlush(subproceso);

        // Get the subproceso
        restSubprocesoMockMvc.perform(get("/api/subprocesos/{id}", subproceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subproceso.getId().intValue()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION));
    }
    @Test
    @Transactional
    public void getNonExistingSubproceso() throws Exception {
        // Get the subproceso
        restSubprocesoMockMvc.perform(get("/api/subprocesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubproceso() throws Exception {
        // Initialize the database
        subprocesoRepository.saveAndFlush(subproceso);

        int databaseSizeBeforeUpdate = subprocesoRepository.findAll().size();

        // Update the subproceso
        Subproceso updatedSubproceso = subprocesoRepository.findById(subproceso.getId()).get();
        // Disconnect from session so that the updates on updatedSubproceso are not directly saved in db
        em.detach(updatedSubproceso);
        updatedSubproceso
            .descripcion(UPDATED_DESCRIPCION);

        restSubprocesoMockMvc.perform(put("/api/subprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubproceso)))
            .andExpect(status().isOk());

        // Validate the Subproceso in the database
        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeUpdate);
        Subproceso testSubproceso = subprocesoList.get(subprocesoList.size() - 1);
        assertThat(testSubproceso.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingSubproceso() throws Exception {
        int databaseSizeBeforeUpdate = subprocesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubprocesoMockMvc.perform(put("/api/subprocesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subproceso)))
            .andExpect(status().isBadRequest());

        // Validate the Subproceso in the database
        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubproceso() throws Exception {
        // Initialize the database
        subprocesoRepository.saveAndFlush(subproceso);

        int databaseSizeBeforeDelete = subprocesoRepository.findAll().size();

        // Delete the subproceso
        restSubprocesoMockMvc.perform(delete("/api/subprocesos/{id}", subproceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Subproceso> subprocesoList = subprocesoRepository.findAll();
        assertThat(subprocesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
