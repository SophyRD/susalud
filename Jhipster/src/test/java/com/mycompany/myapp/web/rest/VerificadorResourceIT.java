package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProyectoApp;
import com.mycompany.myapp.domain.Verificador;
import com.mycompany.myapp.repository.VerificadorRepository;

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
 * Integration tests for the {@link VerificadorResource} REST controller.
 */
@SpringBootTest(classes = ProyectoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VerificadorResourceIT {

    private static final Integer DEFAULT_CODIGO = 1;
    private static final Integer UPDATED_CODIGO = 2;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    @Autowired
    private VerificadorRepository verificadorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVerificadorMockMvc;

    private Verificador verificador;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verificador createEntity(EntityManager em) {
        Verificador verificador = new Verificador()
            .codigo(DEFAULT_CODIGO)
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO);
        return verificador;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Verificador createUpdatedEntity(EntityManager em) {
        Verificador verificador = new Verificador()
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);
        return verificador;
    }

    @BeforeEach
    public void initTest() {
        verificador = createEntity(em);
    }

    @Test
    @Transactional
    public void createVerificador() throws Exception {
        int databaseSizeBeforeCreate = verificadorRepository.findAll().size();
        // Create the Verificador
        restVerificadorMockMvc.perform(post("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isCreated());

        // Validate the Verificador in the database
        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeCreate + 1);
        Verificador testVerificador = verificadorList.get(verificadorList.size() - 1);
        assertThat(testVerificador.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testVerificador.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testVerificador.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createVerificadorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = verificadorRepository.findAll().size();

        // Create the Verificador with an existing ID
        verificador.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVerificadorMockMvc.perform(post("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isBadRequest());

        // Validate the Verificador in the database
        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = verificadorRepository.findAll().size();
        // set the field null
        verificador.setCodigo(null);

        // Create the Verificador, which fails.


        restVerificadorMockMvc.perform(post("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isBadRequest());

        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = verificadorRepository.findAll().size();
        // set the field null
        verificador.setDescripcion(null);

        // Create the Verificador, which fails.


        restVerificadorMockMvc.perform(post("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isBadRequest());

        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = verificadorRepository.findAll().size();
        // set the field null
        verificador.setEstado(null);

        // Create the Verificador, which fails.


        restVerificadorMockMvc.perform(post("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isBadRequest());

        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVerificadors() throws Exception {
        // Initialize the database
        verificadorRepository.saveAndFlush(verificador);

        // Get all the verificadorList
        restVerificadorMockMvc.perform(get("/api/verificadors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(verificador.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)));
    }
    
    @Test
    @Transactional
    public void getVerificador() throws Exception {
        // Initialize the database
        verificadorRepository.saveAndFlush(verificador);

        // Get the verificador
        restVerificadorMockMvc.perform(get("/api/verificadors/{id}", verificador.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(verificador.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO));
    }
    @Test
    @Transactional
    public void getNonExistingVerificador() throws Exception {
        // Get the verificador
        restVerificadorMockMvc.perform(get("/api/verificadors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVerificador() throws Exception {
        // Initialize the database
        verificadorRepository.saveAndFlush(verificador);

        int databaseSizeBeforeUpdate = verificadorRepository.findAll().size();

        // Update the verificador
        Verificador updatedVerificador = verificadorRepository.findById(verificador.getId()).get();
        // Disconnect from session so that the updates on updatedVerificador are not directly saved in db
        em.detach(updatedVerificador);
        updatedVerificador
            .codigo(UPDATED_CODIGO)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);

        restVerificadorMockMvc.perform(put("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVerificador)))
            .andExpect(status().isOk());

        // Validate the Verificador in the database
        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeUpdate);
        Verificador testVerificador = verificadorList.get(verificadorList.size() - 1);
        assertThat(testVerificador.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testVerificador.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testVerificador.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingVerificador() throws Exception {
        int databaseSizeBeforeUpdate = verificadorRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVerificadorMockMvc.perform(put("/api/verificadors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(verificador)))
            .andExpect(status().isBadRequest());

        // Validate the Verificador in the database
        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVerificador() throws Exception {
        // Initialize the database
        verificadorRepository.saveAndFlush(verificador);

        int databaseSizeBeforeDelete = verificadorRepository.findAll().size();

        // Delete the verificador
        restVerificadorMockMvc.perform(delete("/api/verificadors/{id}", verificador.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Verificador> verificadorList = verificadorRepository.findAll();
        assertThat(verificadorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
