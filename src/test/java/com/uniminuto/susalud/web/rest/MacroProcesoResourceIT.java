package com.uniminuto.susalud.web.rest;

import com.uniminuto.susalud.SusaludApp;
import com.uniminuto.susalud.domain.MacroProceso;
import com.uniminuto.susalud.repository.MacroProcesoRepository;

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
 * Integration tests for the {@link MacroProcesoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MacroProcesoResourceIT {

    private static final Integer DEFAULT_ID_MACROPROCESO = 1;
    private static final Integer UPDATED_ID_MACROPROCESO = 2;

    private static final String DEFAULT_MA_FECHA_CREACION = "AAAAAAAAAA";
    private static final String UPDATED_MA_FECHA_CREACION = "BBBBBBBBBB";

    private static final Integer DEFAULT_VERIFICADOR_ID_FERIFICADOR = 1;
    private static final Integer UPDATED_VERIFICADOR_ID_FERIFICADOR = 2;

    @Autowired
    private MacroProcesoRepository macroProcesoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMacroProcesoMockMvc;

    private MacroProceso macroProceso;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MacroProceso createEntity(EntityManager em) {
        MacroProceso macroProceso = new MacroProceso()
            .idMacroproceso(DEFAULT_ID_MACROPROCESO)
            .maFechaCreacion(DEFAULT_MA_FECHA_CREACION)
            .verificadorIdFerificador(DEFAULT_VERIFICADOR_ID_FERIFICADOR);
        return macroProceso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MacroProceso createUpdatedEntity(EntityManager em) {
        MacroProceso macroProceso = new MacroProceso()
            .idMacroproceso(UPDATED_ID_MACROPROCESO)
            .maFechaCreacion(UPDATED_MA_FECHA_CREACION)
            .verificadorIdFerificador(UPDATED_VERIFICADOR_ID_FERIFICADOR);
        return macroProceso;
    }

    @BeforeEach
    public void initTest() {
        macroProceso = createEntity(em);
    }

    @Test
    @Transactional
    public void createMacroProceso() throws Exception {
        int databaseSizeBeforeCreate = macroProcesoRepository.findAll().size();
        // Create the MacroProceso
        restMacroProcesoMockMvc.perform(post("/api/macro-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroProceso)))
            .andExpect(status().isCreated());

        // Validate the MacroProceso in the database
        List<MacroProceso> macroProcesoList = macroProcesoRepository.findAll();
        assertThat(macroProcesoList).hasSize(databaseSizeBeforeCreate + 1);
        MacroProceso testMacroProceso = macroProcesoList.get(macroProcesoList.size() - 1);
        assertThat(testMacroProceso.getIdMacroproceso()).isEqualTo(DEFAULT_ID_MACROPROCESO);
        assertThat(testMacroProceso.getMaFechaCreacion()).isEqualTo(DEFAULT_MA_FECHA_CREACION);
        assertThat(testMacroProceso.getVerificadorIdFerificador()).isEqualTo(DEFAULT_VERIFICADOR_ID_FERIFICADOR);
    }

    @Test
    @Transactional
    public void createMacroProcesoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = macroProcesoRepository.findAll().size();

        // Create the MacroProceso with an existing ID
        macroProceso.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMacroProcesoMockMvc.perform(post("/api/macro-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroProceso)))
            .andExpect(status().isBadRequest());

        // Validate the MacroProceso in the database
        List<MacroProceso> macroProcesoList = macroProcesoRepository.findAll();
        assertThat(macroProcesoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMacroProcesos() throws Exception {
        // Initialize the database
        macroProcesoRepository.saveAndFlush(macroProceso);

        // Get all the macroProcesoList
        restMacroProcesoMockMvc.perform(get("/api/macro-procesos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(macroProceso.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMacroproceso").value(hasItem(DEFAULT_ID_MACROPROCESO)))
            .andExpect(jsonPath("$.[*].maFechaCreacion").value(hasItem(DEFAULT_MA_FECHA_CREACION)))
            .andExpect(jsonPath("$.[*].verificadorIdFerificador").value(hasItem(DEFAULT_VERIFICADOR_ID_FERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getMacroProceso() throws Exception {
        // Initialize the database
        macroProcesoRepository.saveAndFlush(macroProceso);

        // Get the macroProceso
        restMacroProcesoMockMvc.perform(get("/api/macro-procesos/{id}", macroProceso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(macroProceso.getId().intValue()))
            .andExpect(jsonPath("$.idMacroproceso").value(DEFAULT_ID_MACROPROCESO))
            .andExpect(jsonPath("$.maFechaCreacion").value(DEFAULT_MA_FECHA_CREACION))
            .andExpect(jsonPath("$.verificadorIdFerificador").value(DEFAULT_VERIFICADOR_ID_FERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingMacroProceso() throws Exception {
        // Get the macroProceso
        restMacroProcesoMockMvc.perform(get("/api/macro-procesos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMacroProceso() throws Exception {
        // Initialize the database
        macroProcesoRepository.saveAndFlush(macroProceso);

        int databaseSizeBeforeUpdate = macroProcesoRepository.findAll().size();

        // Update the macroProceso
        MacroProceso updatedMacroProceso = macroProcesoRepository.findById(macroProceso.getId()).get();
        // Disconnect from session so that the updates on updatedMacroProceso are not directly saved in db
        em.detach(updatedMacroProceso);
        updatedMacroProceso
            .idMacroproceso(UPDATED_ID_MACROPROCESO)
            .maFechaCreacion(UPDATED_MA_FECHA_CREACION)
            .verificadorIdFerificador(UPDATED_VERIFICADOR_ID_FERIFICADOR);

        restMacroProcesoMockMvc.perform(put("/api/macro-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMacroProceso)))
            .andExpect(status().isOk());

        // Validate the MacroProceso in the database
        List<MacroProceso> macroProcesoList = macroProcesoRepository.findAll();
        assertThat(macroProcesoList).hasSize(databaseSizeBeforeUpdate);
        MacroProceso testMacroProceso = macroProcesoList.get(macroProcesoList.size() - 1);
        assertThat(testMacroProceso.getIdMacroproceso()).isEqualTo(UPDATED_ID_MACROPROCESO);
        assertThat(testMacroProceso.getMaFechaCreacion()).isEqualTo(UPDATED_MA_FECHA_CREACION);
        assertThat(testMacroProceso.getVerificadorIdFerificador()).isEqualTo(UPDATED_VERIFICADOR_ID_FERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMacroProceso() throws Exception {
        int databaseSizeBeforeUpdate = macroProcesoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMacroProcesoMockMvc.perform(put("/api/macro-procesos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(macroProceso)))
            .andExpect(status().isBadRequest());

        // Validate the MacroProceso in the database
        List<MacroProceso> macroProcesoList = macroProcesoRepository.findAll();
        assertThat(macroProcesoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMacroProceso() throws Exception {
        // Initialize the database
        macroProcesoRepository.saveAndFlush(macroProceso);

        int databaseSizeBeforeDelete = macroProcesoRepository.findAll().size();

        // Delete the macroProceso
        restMacroProcesoMockMvc.perform(delete("/api/macro-procesos/{id}", macroProceso.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MacroProceso> macroProcesoList = macroProcesoRepository.findAll();
        assertThat(macroProcesoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
