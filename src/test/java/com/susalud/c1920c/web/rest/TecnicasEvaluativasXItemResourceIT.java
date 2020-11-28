package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.TecnicasEvaluativasXItem;
import com.susalud.c1920c.repository.TecnicasEvaluativasXItemRepository;

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
 * Integration tests for the {@link TecnicasEvaluativasXItemResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TecnicasEvaluativasXItemResourceIT {

    private static final Integer DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM = 1;
    private static final Integer UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM = 2;

    private static final LocalDate DEFAULT_TI_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TI_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TI_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TI_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TI_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_TI_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private TecnicasEvaluativasXItemRepository tecnicasEvaluativasXItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTecnicasEvaluativasXItemMockMvc;

    private TecnicasEvaluativasXItem tecnicasEvaluativasXItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecnicasEvaluativasXItem createEntity(EntityManager em) {
        TecnicasEvaluativasXItem tecnicasEvaluativasXItem = new TecnicasEvaluativasXItem()
            .idTecnicasEvaluativasXItem(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM)
            .tiFechaCreacion(DEFAULT_TI_FECHA_CREACION)
            .tiFechaModificacion(DEFAULT_TI_FECHA_MODIFICACION)
            .tiNombre(DEFAULT_TI_NOMBRE);
        return tecnicasEvaluativasXItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TecnicasEvaluativasXItem createUpdatedEntity(EntityManager em) {
        TecnicasEvaluativasXItem tecnicasEvaluativasXItem = new TecnicasEvaluativasXItem()
            .idTecnicasEvaluativasXItem(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM)
            .tiFechaCreacion(UPDATED_TI_FECHA_CREACION)
            .tiFechaModificacion(UPDATED_TI_FECHA_MODIFICACION)
            .tiNombre(UPDATED_TI_NOMBRE);
        return tecnicasEvaluativasXItem;
    }

    @BeforeEach
    public void initTest() {
        tecnicasEvaluativasXItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createTecnicasEvaluativasXItem() throws Exception {
        int databaseSizeBeforeCreate = tecnicasEvaluativasXItemRepository.findAll().size();
        // Create the TecnicasEvaluativasXItem
        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isCreated());

        // Validate the TecnicasEvaluativasXItem in the database
        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeCreate + 1);
        TecnicasEvaluativasXItem testTecnicasEvaluativasXItem = tecnicasEvaluativasXItemList.get(tecnicasEvaluativasXItemList.size() - 1);
        assertThat(testTecnicasEvaluativasXItem.getIdTecnicasEvaluativasXItem()).isEqualTo(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM);
        assertThat(testTecnicasEvaluativasXItem.getTiFechaCreacion()).isEqualTo(DEFAULT_TI_FECHA_CREACION);
        assertThat(testTecnicasEvaluativasXItem.getTiFechaModificacion()).isEqualTo(DEFAULT_TI_FECHA_MODIFICACION);
        assertThat(testTecnicasEvaluativasXItem.getTiNombre()).isEqualTo(DEFAULT_TI_NOMBRE);
    }

    @Test
    @Transactional
    public void createTecnicasEvaluativasXItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tecnicasEvaluativasXItemRepository.findAll().size();

        // Create the TecnicasEvaluativasXItem with an existing ID
        tecnicasEvaluativasXItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        // Validate the TecnicasEvaluativasXItem in the database
        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdTecnicasEvaluativasXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasXItemRepository.findAll().size();
        // set the field null
        tecnicasEvaluativasXItem.setIdTecnicasEvaluativasXItem(null);

        // Create the TecnicasEvaluativasXItem, which fails.


        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTiFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasXItemRepository.findAll().size();
        // set the field null
        tecnicasEvaluativasXItem.setTiFechaCreacion(null);

        // Create the TecnicasEvaluativasXItem, which fails.


        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTiFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasXItemRepository.findAll().size();
        // set the field null
        tecnicasEvaluativasXItem.setTiFechaModificacion(null);

        // Create the TecnicasEvaluativasXItem, which fails.


        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTiNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = tecnicasEvaluativasXItemRepository.findAll().size();
        // set the field null
        tecnicasEvaluativasXItem.setTiNombre(null);

        // Create the TecnicasEvaluativasXItem, which fails.


        restTecnicasEvaluativasXItemMockMvc.perform(post("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTecnicasEvaluativasXItems() throws Exception {
        // Initialize the database
        tecnicasEvaluativasXItemRepository.saveAndFlush(tecnicasEvaluativasXItem);

        // Get all the tecnicasEvaluativasXItemList
        restTecnicasEvaluativasXItemMockMvc.perform(get("/api/tecnicas-evaluativas-x-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tecnicasEvaluativasXItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].idTecnicasEvaluativasXItem").value(hasItem(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM)))
            .andExpect(jsonPath("$.[*].tiFechaCreacion").value(hasItem(DEFAULT_TI_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].tiFechaModificacion").value(hasItem(DEFAULT_TI_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].tiNombre").value(hasItem(DEFAULT_TI_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getTecnicasEvaluativasXItem() throws Exception {
        // Initialize the database
        tecnicasEvaluativasXItemRepository.saveAndFlush(tecnicasEvaluativasXItem);

        // Get the tecnicasEvaluativasXItem
        restTecnicasEvaluativasXItemMockMvc.perform(get("/api/tecnicas-evaluativas-x-items/{id}", tecnicasEvaluativasXItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tecnicasEvaluativasXItem.getId().intValue()))
            .andExpect(jsonPath("$.idTecnicasEvaluativasXItem").value(DEFAULT_ID_TECNICAS_EVALUATIVAS_X_ITEM))
            .andExpect(jsonPath("$.tiFechaCreacion").value(DEFAULT_TI_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.tiFechaModificacion").value(DEFAULT_TI_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.tiNombre").value(DEFAULT_TI_NOMBRE));
    }
    @Test
    @Transactional
    public void getNonExistingTecnicasEvaluativasXItem() throws Exception {
        // Get the tecnicasEvaluativasXItem
        restTecnicasEvaluativasXItemMockMvc.perform(get("/api/tecnicas-evaluativas-x-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTecnicasEvaluativasXItem() throws Exception {
        // Initialize the database
        tecnicasEvaluativasXItemRepository.saveAndFlush(tecnicasEvaluativasXItem);

        int databaseSizeBeforeUpdate = tecnicasEvaluativasXItemRepository.findAll().size();

        // Update the tecnicasEvaluativasXItem
        TecnicasEvaluativasXItem updatedTecnicasEvaluativasXItem = tecnicasEvaluativasXItemRepository.findById(tecnicasEvaluativasXItem.getId()).get();
        // Disconnect from session so that the updates on updatedTecnicasEvaluativasXItem are not directly saved in db
        em.detach(updatedTecnicasEvaluativasXItem);
        updatedTecnicasEvaluativasXItem
            .idTecnicasEvaluativasXItem(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM)
            .tiFechaCreacion(UPDATED_TI_FECHA_CREACION)
            .tiFechaModificacion(UPDATED_TI_FECHA_MODIFICACION)
            .tiNombre(UPDATED_TI_NOMBRE);

        restTecnicasEvaluativasXItemMockMvc.perform(put("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTecnicasEvaluativasXItem)))
            .andExpect(status().isOk());

        // Validate the TecnicasEvaluativasXItem in the database
        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeUpdate);
        TecnicasEvaluativasXItem testTecnicasEvaluativasXItem = tecnicasEvaluativasXItemList.get(tecnicasEvaluativasXItemList.size() - 1);
        assertThat(testTecnicasEvaluativasXItem.getIdTecnicasEvaluativasXItem()).isEqualTo(UPDATED_ID_TECNICAS_EVALUATIVAS_X_ITEM);
        assertThat(testTecnicasEvaluativasXItem.getTiFechaCreacion()).isEqualTo(UPDATED_TI_FECHA_CREACION);
        assertThat(testTecnicasEvaluativasXItem.getTiFechaModificacion()).isEqualTo(UPDATED_TI_FECHA_MODIFICACION);
        assertThat(testTecnicasEvaluativasXItem.getTiNombre()).isEqualTo(UPDATED_TI_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTecnicasEvaluativasXItem() throws Exception {
        int databaseSizeBeforeUpdate = tecnicasEvaluativasXItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTecnicasEvaluativasXItemMockMvc.perform(put("/api/tecnicas-evaluativas-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tecnicasEvaluativasXItem)))
            .andExpect(status().isBadRequest());

        // Validate the TecnicasEvaluativasXItem in the database
        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTecnicasEvaluativasXItem() throws Exception {
        // Initialize the database
        tecnicasEvaluativasXItemRepository.saveAndFlush(tecnicasEvaluativasXItem);

        int databaseSizeBeforeDelete = tecnicasEvaluativasXItemRepository.findAll().size();

        // Delete the tecnicasEvaluativasXItem
        restTecnicasEvaluativasXItemMockMvc.perform(delete("/api/tecnicas-evaluativas-x-items/{id}", tecnicasEvaluativasXItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TecnicasEvaluativasXItem> tecnicasEvaluativasXItemList = tecnicasEvaluativasXItemRepository.findAll();
        assertThat(tecnicasEvaluativasXItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
