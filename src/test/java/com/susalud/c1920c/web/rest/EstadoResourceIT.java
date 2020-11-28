package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Estado;
import com.susalud.c1920c.repository.EstadoRepository;

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
 * Integration tests for the {@link EstadoResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EstadoResourceIT {

    private static final Integer DEFAULT_ID_ESTADO = 1;
    private static final Integer UPDATED_ID_ESTADO = 2;

    private static final String DEFAULT_E_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_E_ESTADO = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_VERIFICADOR = 1;
    private static final Integer UPDATED_ID_VERIFICADOR = 2;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEstadoMockMvc;

    private Estado estado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estado createEntity(EntityManager em) {
        Estado estado = new Estado()
            .idEstado(DEFAULT_ID_ESTADO)
            .eEstado(DEFAULT_E_ESTADO)
            .idVerificador(DEFAULT_ID_VERIFICADOR);
        return estado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Estado createUpdatedEntity(EntityManager em) {
        Estado estado = new Estado()
            .idEstado(UPDATED_ID_ESTADO)
            .eEstado(UPDATED_E_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR);
        return estado;
    }

    @BeforeEach
    public void initTest() {
        estado = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstado() throws Exception {
        int databaseSizeBeforeCreate = estadoRepository.findAll().size();
        // Create the Estado
        restEstadoMockMvc.perform(post("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isCreated());

        // Validate the Estado in the database
        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeCreate + 1);
        Estado testEstado = estadoList.get(estadoList.size() - 1);
        assertThat(testEstado.getIdEstado()).isEqualTo(DEFAULT_ID_ESTADO);
        assertThat(testEstado.geteEstado()).isEqualTo(DEFAULT_E_ESTADO);
        assertThat(testEstado.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void createEstadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoRepository.findAll().size();

        // Create the Estado with an existing ID
        estado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoMockMvc.perform(post("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isBadRequest());

        // Validate the Estado in the database
        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoRepository.findAll().size();
        // set the field null
        estado.setIdEstado(null);

        // Create the Estado, which fails.


        restEstadoMockMvc.perform(post("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isBadRequest());

        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkeEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoRepository.findAll().size();
        // set the field null
        estado.seteEstado(null);

        // Create the Estado, which fails.


        restEstadoMockMvc.perform(post("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isBadRequest());

        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoRepository.findAll().size();
        // set the field null
        estado.setIdVerificador(null);

        // Create the Estado, which fails.


        restEstadoMockMvc.perform(post("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isBadRequest());

        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstados() throws Exception {
        // Initialize the database
        estadoRepository.saveAndFlush(estado);

        // Get all the estadoList
        restEstadoMockMvc.perform(get("/api/estados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estado.getId().intValue())))
            .andExpect(jsonPath("$.[*].idEstado").value(hasItem(DEFAULT_ID_ESTADO)))
            .andExpect(jsonPath("$.[*].eEstado").value(hasItem(DEFAULT_E_ESTADO)))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getEstado() throws Exception {
        // Initialize the database
        estadoRepository.saveAndFlush(estado);

        // Get the estado
        restEstadoMockMvc.perform(get("/api/estados/{id}", estado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(estado.getId().intValue()))
            .andExpect(jsonPath("$.idEstado").value(DEFAULT_ID_ESTADO))
            .andExpect(jsonPath("$.eEstado").value(DEFAULT_E_ESTADO))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingEstado() throws Exception {
        // Get the estado
        restEstadoMockMvc.perform(get("/api/estados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstado() throws Exception {
        // Initialize the database
        estadoRepository.saveAndFlush(estado);

        int databaseSizeBeforeUpdate = estadoRepository.findAll().size();

        // Update the estado
        Estado updatedEstado = estadoRepository.findById(estado.getId()).get();
        // Disconnect from session so that the updates on updatedEstado are not directly saved in db
        em.detach(updatedEstado);
        updatedEstado
            .idEstado(UPDATED_ID_ESTADO)
            .eEstado(UPDATED_E_ESTADO)
            .idVerificador(UPDATED_ID_VERIFICADOR);

        restEstadoMockMvc.perform(put("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedEstado)))
            .andExpect(status().isOk());

        // Validate the Estado in the database
        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeUpdate);
        Estado testEstado = estadoList.get(estadoList.size() - 1);
        assertThat(testEstado.getIdEstado()).isEqualTo(UPDATED_ID_ESTADO);
        assertThat(testEstado.geteEstado()).isEqualTo(UPDATED_E_ESTADO);
        assertThat(testEstado.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingEstado() throws Exception {
        int databaseSizeBeforeUpdate = estadoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoMockMvc.perform(put("/api/estados")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(estado)))
            .andExpect(status().isBadRequest());

        // Validate the Estado in the database
        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstado() throws Exception {
        // Initialize the database
        estadoRepository.saveAndFlush(estado);

        int databaseSizeBeforeDelete = estadoRepository.findAll().size();

        // Delete the estado
        restEstadoMockMvc.perform(delete("/api/estados/{id}", estado.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Estado> estadoList = estadoRepository.findAll();
        assertThat(estadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
