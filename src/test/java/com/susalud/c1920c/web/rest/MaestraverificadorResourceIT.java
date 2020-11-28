package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Maestraverificador;
import com.susalud.c1920c.repository.MaestraverificadorRepository;

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
 * Integration tests for the {@link MaestraverificadorResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MaestraverificadorResourceIT {

    private static final Integer DEFAULT_ID_MAESTRAVERIFICADOR = 1;
    private static final Integer UPDATED_ID_MAESTRAVERIFICADOR = 2;

    private static final LocalDate DEFAULT_MV_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MV_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MV_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MV_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ID_VERIFICADOR = 1;
    private static final Integer UPDATED_ID_VERIFICADOR = 2;

    @Autowired
    private MaestraverificadorRepository maestraverificadorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMaestraverificadorMockMvc;

    private Maestraverificador maestraverificador;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maestraverificador createEntity(EntityManager em) {
        Maestraverificador maestraverificador = new Maestraverificador()
            .idMaestraverificador(DEFAULT_ID_MAESTRAVERIFICADOR)
            .mvFecha(DEFAULT_MV_FECHA)
            .mvFechaModificacion(DEFAULT_MV_FECHA_MODIFICACION)
            .idVerificador(DEFAULT_ID_VERIFICADOR);
        return maestraverificador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maestraverificador createUpdatedEntity(EntityManager em) {
        Maestraverificador maestraverificador = new Maestraverificador()
            .idMaestraverificador(UPDATED_ID_MAESTRAVERIFICADOR)
            .mvFecha(UPDATED_MV_FECHA)
            .mvFechaModificacion(UPDATED_MV_FECHA_MODIFICACION)
            .idVerificador(UPDATED_ID_VERIFICADOR);
        return maestraverificador;
    }

    @BeforeEach
    public void initTest() {
        maestraverificador = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaestraverificador() throws Exception {
        int databaseSizeBeforeCreate = maestraverificadorRepository.findAll().size();
        // Create the Maestraverificador
        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isCreated());

        // Validate the Maestraverificador in the database
        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeCreate + 1);
        Maestraverificador testMaestraverificador = maestraverificadorList.get(maestraverificadorList.size() - 1);
        assertThat(testMaestraverificador.getIdMaestraverificador()).isEqualTo(DEFAULT_ID_MAESTRAVERIFICADOR);
        assertThat(testMaestraverificador.getMvFecha()).isEqualTo(DEFAULT_MV_FECHA);
        assertThat(testMaestraverificador.getMvFechaModificacion()).isEqualTo(DEFAULT_MV_FECHA_MODIFICACION);
        assertThat(testMaestraverificador.getIdVerificador()).isEqualTo(DEFAULT_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void createMaestraverificadorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maestraverificadorRepository.findAll().size();

        // Create the Maestraverificador with an existing ID
        maestraverificador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        // Validate the Maestraverificador in the database
        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdMaestraverificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = maestraverificadorRepository.findAll().size();
        // set the field null
        maestraverificador.setIdMaestraverificador(null);

        // Create the Maestraverificador, which fails.


        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMvFechaIsRequired() throws Exception {
        int databaseSizeBeforeTest = maestraverificadorRepository.findAll().size();
        // set the field null
        maestraverificador.setMvFecha(null);

        // Create the Maestraverificador, which fails.


        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMvFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = maestraverificadorRepository.findAll().size();
        // set the field null
        maestraverificador.setMvFechaModificacion(null);

        // Create the Maestraverificador, which fails.


        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdVerificadorIsRequired() throws Exception {
        int databaseSizeBeforeTest = maestraverificadorRepository.findAll().size();
        // set the field null
        maestraverificador.setIdVerificador(null);

        // Create the Maestraverificador, which fails.


        restMaestraverificadorMockMvc.perform(post("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMaestraverificadors() throws Exception {
        // Initialize the database
        maestraverificadorRepository.saveAndFlush(maestraverificador);

        // Get all the maestraverificadorList
        restMaestraverificadorMockMvc.perform(get("/api/maestraverificadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maestraverificador.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMaestraverificador").value(hasItem(DEFAULT_ID_MAESTRAVERIFICADOR)))
            .andExpect(jsonPath("$.[*].mvFecha").value(hasItem(DEFAULT_MV_FECHA.toString())))
            .andExpect(jsonPath("$.[*].mvFechaModificacion").value(hasItem(DEFAULT_MV_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].idVerificador").value(hasItem(DEFAULT_ID_VERIFICADOR)));
    }
    
    @Test
    @Transactional
    public void getMaestraverificador() throws Exception {
        // Initialize the database
        maestraverificadorRepository.saveAndFlush(maestraverificador);

        // Get the maestraverificador
        restMaestraverificadorMockMvc.perform(get("/api/maestraverificadors/{id}", maestraverificador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(maestraverificador.getId().intValue()))
            .andExpect(jsonPath("$.idMaestraverificador").value(DEFAULT_ID_MAESTRAVERIFICADOR))
            .andExpect(jsonPath("$.mvFecha").value(DEFAULT_MV_FECHA.toString()))
            .andExpect(jsonPath("$.mvFechaModificacion").value(DEFAULT_MV_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.idVerificador").value(DEFAULT_ID_VERIFICADOR));
    }
    @Test
    @Transactional
    public void getNonExistingMaestraverificador() throws Exception {
        // Get the maestraverificador
        restMaestraverificadorMockMvc.perform(get("/api/maestraverificadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaestraverificador() throws Exception {
        // Initialize the database
        maestraverificadorRepository.saveAndFlush(maestraverificador);

        int databaseSizeBeforeUpdate = maestraverificadorRepository.findAll().size();

        // Update the maestraverificador
        Maestraverificador updatedMaestraverificador = maestraverificadorRepository.findById(maestraverificador.getId()).get();
        // Disconnect from session so that the updates on updatedMaestraverificador are not directly saved in db
        em.detach(updatedMaestraverificador);
        updatedMaestraverificador
            .idMaestraverificador(UPDATED_ID_MAESTRAVERIFICADOR)
            .mvFecha(UPDATED_MV_FECHA)
            .mvFechaModificacion(UPDATED_MV_FECHA_MODIFICACION)
            .idVerificador(UPDATED_ID_VERIFICADOR);

        restMaestraverificadorMockMvc.perform(put("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMaestraverificador)))
            .andExpect(status().isOk());

        // Validate the Maestraverificador in the database
        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeUpdate);
        Maestraverificador testMaestraverificador = maestraverificadorList.get(maestraverificadorList.size() - 1);
        assertThat(testMaestraverificador.getIdMaestraverificador()).isEqualTo(UPDATED_ID_MAESTRAVERIFICADOR);
        assertThat(testMaestraverificador.getMvFecha()).isEqualTo(UPDATED_MV_FECHA);
        assertThat(testMaestraverificador.getMvFechaModificacion()).isEqualTo(UPDATED_MV_FECHA_MODIFICACION);
        assertThat(testMaestraverificador.getIdVerificador()).isEqualTo(UPDATED_ID_VERIFICADOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMaestraverificador() throws Exception {
        int databaseSizeBeforeUpdate = maestraverificadorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaestraverificadorMockMvc.perform(put("/api/maestraverificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(maestraverificador)))
            .andExpect(status().isBadRequest());

        // Validate the Maestraverificador in the database
        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaestraverificador() throws Exception {
        // Initialize the database
        maestraverificadorRepository.saveAndFlush(maestraverificador);

        int databaseSizeBeforeDelete = maestraverificadorRepository.findAll().size();

        // Delete the maestraverificador
        restMaestraverificadorMockMvc.perform(delete("/api/maestraverificadors/{id}", maestraverificador.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Maestraverificador> maestraverificadorList = maestraverificadorRepository.findAll();
        assertThat(maestraverificadorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
