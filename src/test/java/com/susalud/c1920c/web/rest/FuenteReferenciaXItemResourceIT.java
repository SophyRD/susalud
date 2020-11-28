package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.FuenteReferenciaXItem;
import com.susalud.c1920c.repository.FuenteReferenciaXItemRepository;

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
 * Integration tests for the {@link FuenteReferenciaXItemResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FuenteReferenciaXItemResourceIT {

    private static final Integer DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM = 1;
    private static final Integer UPDATED_ID_FUENTE_REFERENCIA_X_ITEM = 2;

    private static final LocalDate DEFAULT_FRI_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FRI_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FRI_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FRI_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FRI_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_FRI_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private FuenteReferenciaXItemRepository fuenteReferenciaXItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFuenteReferenciaXItemMockMvc;

    private FuenteReferenciaXItem fuenteReferenciaXItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FuenteReferenciaXItem createEntity(EntityManager em) {
        FuenteReferenciaXItem fuenteReferenciaXItem = new FuenteReferenciaXItem()
            .idFuenteReferenciaXItem(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM)
            .friFechaCreacion(DEFAULT_FRI_FECHA_CREACION)
            .friFechaModificacion(DEFAULT_FRI_FECHA_MODIFICACION)
            .friNombre(DEFAULT_FRI_NOMBRE);
        return fuenteReferenciaXItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FuenteReferenciaXItem createUpdatedEntity(EntityManager em) {
        FuenteReferenciaXItem fuenteReferenciaXItem = new FuenteReferenciaXItem()
            .idFuenteReferenciaXItem(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM)
            .friFechaCreacion(UPDATED_FRI_FECHA_CREACION)
            .friFechaModificacion(UPDATED_FRI_FECHA_MODIFICACION)
            .friNombre(UPDATED_FRI_NOMBRE);
        return fuenteReferenciaXItem;
    }

    @BeforeEach
    public void initTest() {
        fuenteReferenciaXItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createFuenteReferenciaXItem() throws Exception {
        int databaseSizeBeforeCreate = fuenteReferenciaXItemRepository.findAll().size();
        // Create the FuenteReferenciaXItem
        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isCreated());

        // Validate the FuenteReferenciaXItem in the database
        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeCreate + 1);
        FuenteReferenciaXItem testFuenteReferenciaXItem = fuenteReferenciaXItemList.get(fuenteReferenciaXItemList.size() - 1);
        assertThat(testFuenteReferenciaXItem.getIdFuenteReferenciaXItem()).isEqualTo(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM);
        assertThat(testFuenteReferenciaXItem.getFriFechaCreacion()).isEqualTo(DEFAULT_FRI_FECHA_CREACION);
        assertThat(testFuenteReferenciaXItem.getFriFechaModificacion()).isEqualTo(DEFAULT_FRI_FECHA_MODIFICACION);
        assertThat(testFuenteReferenciaXItem.getFriNombre()).isEqualTo(DEFAULT_FRI_NOMBRE);
    }

    @Test
    @Transactional
    public void createFuenteReferenciaXItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fuenteReferenciaXItemRepository.findAll().size();

        // Create the FuenteReferenciaXItem with an existing ID
        fuenteReferenciaXItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        // Validate the FuenteReferenciaXItem in the database
        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdFuenteReferenciaXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferenciaXItemRepository.findAll().size();
        // set the field null
        fuenteReferenciaXItem.setIdFuenteReferenciaXItem(null);

        // Create the FuenteReferenciaXItem, which fails.


        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFriFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferenciaXItemRepository.findAll().size();
        // set the field null
        fuenteReferenciaXItem.setFriFechaCreacion(null);

        // Create the FuenteReferenciaXItem, which fails.


        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFriFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferenciaXItemRepository.findAll().size();
        // set the field null
        fuenteReferenciaXItem.setFriFechaModificacion(null);

        // Create the FuenteReferenciaXItem, which fails.


        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFriNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = fuenteReferenciaXItemRepository.findAll().size();
        // set the field null
        fuenteReferenciaXItem.setFriNombre(null);

        // Create the FuenteReferenciaXItem, which fails.


        restFuenteReferenciaXItemMockMvc.perform(post("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFuenteReferenciaXItems() throws Exception {
        // Initialize the database
        fuenteReferenciaXItemRepository.saveAndFlush(fuenteReferenciaXItem);

        // Get all the fuenteReferenciaXItemList
        restFuenteReferenciaXItemMockMvc.perform(get("/api/fuente-referencia-x-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fuenteReferenciaXItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].idFuenteReferenciaXItem").value(hasItem(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM)))
            .andExpect(jsonPath("$.[*].friFechaCreacion").value(hasItem(DEFAULT_FRI_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].friFechaModificacion").value(hasItem(DEFAULT_FRI_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].friNombre").value(hasItem(DEFAULT_FRI_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getFuenteReferenciaXItem() throws Exception {
        // Initialize the database
        fuenteReferenciaXItemRepository.saveAndFlush(fuenteReferenciaXItem);

        // Get the fuenteReferenciaXItem
        restFuenteReferenciaXItemMockMvc.perform(get("/api/fuente-referencia-x-items/{id}", fuenteReferenciaXItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fuenteReferenciaXItem.getId().intValue()))
            .andExpect(jsonPath("$.idFuenteReferenciaXItem").value(DEFAULT_ID_FUENTE_REFERENCIA_X_ITEM))
            .andExpect(jsonPath("$.friFechaCreacion").value(DEFAULT_FRI_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.friFechaModificacion").value(DEFAULT_FRI_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.friNombre").value(DEFAULT_FRI_NOMBRE));
    }
    @Test
    @Transactional
    public void getNonExistingFuenteReferenciaXItem() throws Exception {
        // Get the fuenteReferenciaXItem
        restFuenteReferenciaXItemMockMvc.perform(get("/api/fuente-referencia-x-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFuenteReferenciaXItem() throws Exception {
        // Initialize the database
        fuenteReferenciaXItemRepository.saveAndFlush(fuenteReferenciaXItem);

        int databaseSizeBeforeUpdate = fuenteReferenciaXItemRepository.findAll().size();

        // Update the fuenteReferenciaXItem
        FuenteReferenciaXItem updatedFuenteReferenciaXItem = fuenteReferenciaXItemRepository.findById(fuenteReferenciaXItem.getId()).get();
        // Disconnect from session so that the updates on updatedFuenteReferenciaXItem are not directly saved in db
        em.detach(updatedFuenteReferenciaXItem);
        updatedFuenteReferenciaXItem
            .idFuenteReferenciaXItem(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM)
            .friFechaCreacion(UPDATED_FRI_FECHA_CREACION)
            .friFechaModificacion(UPDATED_FRI_FECHA_MODIFICACION)
            .friNombre(UPDATED_FRI_NOMBRE);

        restFuenteReferenciaXItemMockMvc.perform(put("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFuenteReferenciaXItem)))
            .andExpect(status().isOk());

        // Validate the FuenteReferenciaXItem in the database
        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeUpdate);
        FuenteReferenciaXItem testFuenteReferenciaXItem = fuenteReferenciaXItemList.get(fuenteReferenciaXItemList.size() - 1);
        assertThat(testFuenteReferenciaXItem.getIdFuenteReferenciaXItem()).isEqualTo(UPDATED_ID_FUENTE_REFERENCIA_X_ITEM);
        assertThat(testFuenteReferenciaXItem.getFriFechaCreacion()).isEqualTo(UPDATED_FRI_FECHA_CREACION);
        assertThat(testFuenteReferenciaXItem.getFriFechaModificacion()).isEqualTo(UPDATED_FRI_FECHA_MODIFICACION);
        assertThat(testFuenteReferenciaXItem.getFriNombre()).isEqualTo(UPDATED_FRI_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingFuenteReferenciaXItem() throws Exception {
        int databaseSizeBeforeUpdate = fuenteReferenciaXItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFuenteReferenciaXItemMockMvc.perform(put("/api/fuente-referencia-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fuenteReferenciaXItem)))
            .andExpect(status().isBadRequest());

        // Validate the FuenteReferenciaXItem in the database
        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFuenteReferenciaXItem() throws Exception {
        // Initialize the database
        fuenteReferenciaXItemRepository.saveAndFlush(fuenteReferenciaXItem);

        int databaseSizeBeforeDelete = fuenteReferenciaXItemRepository.findAll().size();

        // Delete the fuenteReferenciaXItem
        restFuenteReferenciaXItemMockMvc.perform(delete("/api/fuente-referencia-x-items/{id}", fuenteReferenciaXItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FuenteReferenciaXItem> fuenteReferenciaXItemList = fuenteReferenciaXItemRepository.findAll();
        assertThat(fuenteReferenciaXItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
