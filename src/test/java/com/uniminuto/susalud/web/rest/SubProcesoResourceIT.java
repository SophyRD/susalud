package com.uniminuto.susalud.web.rest;

import com.uniminuto.susalud.SusaludApp;
import com.uniminuto.susalud.domain.SubProceso;
import com.uniminuto.susalud.repository.SubProcesoRepository;

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
 * Integration tests for the {@link SubProcesoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SubProcesoResourceIT {

    private static final Integer DEFAULT_ID_SUBPROCESO = 1;
    private static final Integer UPDATED_ID_SUBPROCESO = 2;

    private static final String DEFAULT_SP_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_SP_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROCESO_ID_PROCESO = 1;
    private static final Integer UPDATED_PROCESO_ID_PROCESO = 2;

    private static final Integer DEFAULT_PROCESO_MACROPROCESO_ID_MACROPROCESO = 1;
    private static final Integer UPDATED_PROCESO_MACROPROCESO_ID_MACROPROCESO = 2;

    private static final Integer DEFAULT_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR = 1;
    private static final Integer UPDATED_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR = 2;

    @Autowired
    private SubProcesoRepository subProcesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubProcesoMockMvc;

    private SubProceso subProceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubProceso createEntity(EntityManager em) {
        SubProceso subProceso = new SubProceso()
            .idSubproceso(DEFAULT_ID_SUBPROCESO)
            .spDescripcion(DEFAULT_SP_DESCRIPCION)
            .procesoIdProceso(DEFAULT_PROCESO_ID_PROCESO)
            .procesoMacroprocesoIdMacroproceso(DEFAULT_PROCESO_MACROPROCESO_ID_MACROPROCESO)
            .procesoMacroprocesoVerificadorIdVerificador(DEFAULT_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR);
        return subProceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubProceso createUpdatedEntity(EntityManager em) {
        SubProceso subProceso = new SubProceso()
            .idSubproceso(UPDATED_ID_SUBPROCESO)
            .spDescripcion(UPDATED_SP_DESCRIPCION)
            .procesoIdProceso(UPDATED_PROCESO_ID_PROCESO)
            .procesoMacroprocesoIdMacroproceso(UPDATED_PROCESO_MACROPROCESO_ID_MACROPROCESO)
            .procesoMacroprocesoVerificadorIdVerificador(UPDATED_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR);
        return subProceso;
    }

    @BeforeEach
    public void initTest() {
        subProceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubProceso() throws Exception {
        int databaseSizeBeforeCreate = subProcesoRepository.findAll().size();
        // Create the SubProceso
        restSubProcesoMockMvc.perform(post("/api/sub-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subProceso)))
            .andExpect(status().isCreated());

        // Validate the SubProceso in the database
        List<SubProceso> subProcesoList = subProcesoRepository.findAll();
        assertThat(subProcesoList).hasSize(databaseSizeBeforeCreate + 1);
        SubProceso testSubProceso = subProcesoList.get(subProcesoList.size() - 1);
        assertThat(testSubProceso.getIdSubproceso()).isEqualTo(DEFAULT_ID_SUBPROCESO);
        assertThat(testSubProceso.getSpDescripcion()).isEqualTo(DEFAULT_SP_DESCRIPCION);
        assertThat(testSubProceso.getProcesoIdProceso()).isEqualTo(DEFAULT_PROCESO_ID_PROCESO);
        assertThat(testSubProceso.getProcesoMacroprocesoIdMacroproceso()).isEqualTo(DEFAULT_PROCESO_MACROPROCESO_ID_MACROPROCESO);
        assertThat(testSubProceso.getProcesoMacroprocesoVerificadorIdVerificador()).isEqualTo(DEFAULT_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void createSubProcesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subProcesoRepository.findAll().size();

        // Create the SubProceso with an existing ID
        subProceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubProcesoMockMvc.perform(post("/api/sub-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subProceso)))
            .andExpect(status().isBadRequest());

        // Validate the SubProceso in the database
        List<SubProceso> subProcesoList = subProcesoRepository.findAll();
        assertThat(subProcesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSubProcesos() throws Exception {
        // Initialize the database
        subProcesoRepository.saveAndFlush(subProceso);

        // Get all the subProcesoList
        restSubProcesoMockMvc.perform(get("/api/sub-procesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subProceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idSubproceso").value(hasItem(DEFAULT_ID_SUBPROCESO)))
            .andExpect(jsonPath("$.[*].spDescripcion").value(hasItem(DEFAULT_SP_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].procesoIdProceso").value(hasItem(DEFAULT_PROCESO_ID_PROCESO)))
            .andExpect(jsonPath("$.[*].procesoMacroprocesoIdMacroproceso").value(hasItem(DEFAULT_PROCESO_MACROPROCESO_ID_MACROPROCESO)))
            .andExpect(jsonPath("$.[*].procesoMacroprocesoVerificadorIdVerificador").value(hasItem(DEFAULT_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getSubProceso() throws Exception {
        // Initialize the database
        subProcesoRepository.saveAndFlush(subProceso);

        // Get the subProceso
        restSubProcesoMockMvc.perform(get("/api/sub-procesos/{id}", subProceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subProceso.getId().intValue()))
            .andExpect(jsonPath("$.idSubproceso").value(DEFAULT_ID_SUBPROCESO))
            .andExpect(jsonPath("$.spDescripcion").value(DEFAULT_SP_DESCRIPCION))
            .andExpect(jsonPath("$.procesoIdProceso").value(DEFAULT_PROCESO_ID_PROCESO))
            .andExpect(jsonPath("$.procesoMacroprocesoIdMacroproceso").value(DEFAULT_PROCESO_MACROPROCESO_ID_MACROPROCESO))
            .andExpect(jsonPath("$.procesoMacroprocesoVerificadorIdVerificador").value(DEFAULT_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingSubProceso() throws Exception {
        // Get the subProceso
        restSubProcesoMockMvc.perform(get("/api/sub-procesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubProceso() throws Exception {
        // Initialize the database
        subProcesoRepository.saveAndFlush(subProceso);

        int databaseSizeBeforeUpdate = subProcesoRepository.findAll().size();

        // Update the subProceso
        SubProceso updatedSubProceso = subProcesoRepository.findById(subProceso.getId()).get();
        // Disconnect from session so that the updates on updatedSubProceso are not directly saved in db
        em.detach(updatedSubProceso);
        updatedSubProceso
            .idSubproceso(UPDATED_ID_SUBPROCESO)
            .spDescripcion(UPDATED_SP_DESCRIPCION)
            .procesoIdProceso(UPDATED_PROCESO_ID_PROCESO)
            .procesoMacroprocesoIdMacroproceso(UPDATED_PROCESO_MACROPROCESO_ID_MACROPROCESO)
            .procesoMacroprocesoVerificadorIdVerificador(UPDATED_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR);

        restSubProcesoMockMvc.perform(put("/api/sub-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubProceso)))
            .andExpect(status().isOk());

        // Validate the SubProceso in the database
        List<SubProceso> subProcesoList = subProcesoRepository.findAll();
        assertThat(subProcesoList).hasSize(databaseSizeBeforeUpdate);
        SubProceso testSubProceso = subProcesoList.get(subProcesoList.size() - 1);
        assertThat(testSubProceso.getIdSubproceso()).isEqualTo(UPDATED_ID_SUBPROCESO);
        assertThat(testSubProceso.getSpDescripcion()).isEqualTo(UPDATED_SP_DESCRIPCION);
        assertThat(testSubProceso.getProcesoIdProceso()).isEqualTo(UPDATED_PROCESO_ID_PROCESO);
        assertThat(testSubProceso.getProcesoMacroprocesoIdMacroproceso()).isEqualTo(UPDATED_PROCESO_MACROPROCESO_ID_MACROPROCESO);
        assertThat(testSubProceso.getProcesoMacroprocesoVerificadorIdVerificador()).isEqualTo(UPDATED_PROCESO_MACROPROCESO_VERIFICADOR_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingSubProceso() throws Exception {
        int databaseSizeBeforeUpdate = subProcesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubProcesoMockMvc.perform(put("/api/sub-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(subProceso)))
            .andExpect(status().isBadRequest());

        // Validate the SubProceso in the database
        List<SubProceso> subProcesoList = subProcesoRepository.findAll();
        assertThat(subProcesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubProceso() throws Exception {
        // Initialize the database
        subProcesoRepository.saveAndFlush(subProceso);

        int databaseSizeBeforeDelete = subProcesoRepository.findAll().size();

        // Delete the subProceso
        restSubProcesoMockMvc.perform(delete("/api/sub-procesos/{id}", subProceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SubProceso> subProcesoList = subProcesoRepository.findAll();
        assertThat(subProcesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
