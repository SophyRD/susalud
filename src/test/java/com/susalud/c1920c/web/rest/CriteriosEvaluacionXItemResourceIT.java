package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.CriteriosEvaluacionXItem;
import com.susalud.c1920c.repository.CriteriosEvaluacionXItemRepository;

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
 * Integration tests for the {@link CriteriosEvaluacionXItemResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CriteriosEvaluacionXItemResourceIT {

    private static final Integer DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM = 1;
    private static final Integer UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM = 2;

    private static final LocalDate DEFAULT_CEI_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CEI_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CEI_FECHA_MODIFICACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CEI_FECHA_MODIFICACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CEI_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_CEI_NOMBRE = "BBBBBBBBBB";

    @Autowired
    private CriteriosEvaluacionXItemRepository criteriosEvaluacionXItemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCriteriosEvaluacionXItemMockMvc;

    private CriteriosEvaluacionXItem criteriosEvaluacionXItem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriteriosEvaluacionXItem createEntity(EntityManager em) {
        CriteriosEvaluacionXItem criteriosEvaluacionXItem = new CriteriosEvaluacionXItem()
            .idCriteriosEvaluacionXItem(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM)
            .ceiFechaCreacion(DEFAULT_CEI_FECHA_CREACION)
            .ceiFechaModificacion(DEFAULT_CEI_FECHA_MODIFICACION)
            .ceiNombre(DEFAULT_CEI_NOMBRE);
        return criteriosEvaluacionXItem;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CriteriosEvaluacionXItem createUpdatedEntity(EntityManager em) {
        CriteriosEvaluacionXItem criteriosEvaluacionXItem = new CriteriosEvaluacionXItem()
            .idCriteriosEvaluacionXItem(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM)
            .ceiFechaCreacion(UPDATED_CEI_FECHA_CREACION)
            .ceiFechaModificacion(UPDATED_CEI_FECHA_MODIFICACION)
            .ceiNombre(UPDATED_CEI_NOMBRE);
        return criteriosEvaluacionXItem;
    }

    @BeforeEach
    public void initTest() {
        criteriosEvaluacionXItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createCriteriosEvaluacionXItem() throws Exception {
        int databaseSizeBeforeCreate = criteriosEvaluacionXItemRepository.findAll().size();
        // Create the CriteriosEvaluacionXItem
        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isCreated());

        // Validate the CriteriosEvaluacionXItem in the database
        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeCreate + 1);
        CriteriosEvaluacionXItem testCriteriosEvaluacionXItem = criteriosEvaluacionXItemList.get(criteriosEvaluacionXItemList.size() - 1);
        assertThat(testCriteriosEvaluacionXItem.getIdCriteriosEvaluacionXItem()).isEqualTo(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM);
        assertThat(testCriteriosEvaluacionXItem.getCeiFechaCreacion()).isEqualTo(DEFAULT_CEI_FECHA_CREACION);
        assertThat(testCriteriosEvaluacionXItem.getCeiFechaModificacion()).isEqualTo(DEFAULT_CEI_FECHA_MODIFICACION);
        assertThat(testCriteriosEvaluacionXItem.getCeiNombre()).isEqualTo(DEFAULT_CEI_NOMBRE);
    }

    @Test
    @Transactional
    public void createCriteriosEvaluacionXItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = criteriosEvaluacionXItemRepository.findAll().size();

        // Create the CriteriosEvaluacionXItem with an existing ID
        criteriosEvaluacionXItem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        // Validate the CriteriosEvaluacionXItem in the database
        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdCriteriosEvaluacionXItemIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionXItemRepository.findAll().size();
        // set the field null
        criteriosEvaluacionXItem.setIdCriteriosEvaluacionXItem(null);

        // Create the CriteriosEvaluacionXItem, which fails.


        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeiFechaCreacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionXItemRepository.findAll().size();
        // set the field null
        criteriosEvaluacionXItem.setCeiFechaCreacion(null);

        // Create the CriteriosEvaluacionXItem, which fails.


        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeiFechaModificacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionXItemRepository.findAll().size();
        // set the field null
        criteriosEvaluacionXItem.setCeiFechaModificacion(null);

        // Create the CriteriosEvaluacionXItem, which fails.


        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCeiNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = criteriosEvaluacionXItemRepository.findAll().size();
        // set the field null
        criteriosEvaluacionXItem.setCeiNombre(null);

        // Create the CriteriosEvaluacionXItem, which fails.


        restCriteriosEvaluacionXItemMockMvc.perform(post("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCriteriosEvaluacionXItems() throws Exception {
        // Initialize the database
        criteriosEvaluacionXItemRepository.saveAndFlush(criteriosEvaluacionXItem);

        // Get all the criteriosEvaluacionXItemList
        restCriteriosEvaluacionXItemMockMvc.perform(get("/api/criterios-evaluacion-x-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(criteriosEvaluacionXItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].idCriteriosEvaluacionXItem").value(hasItem(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM)))
            .andExpect(jsonPath("$.[*].ceiFechaCreacion").value(hasItem(DEFAULT_CEI_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].ceiFechaModificacion").value(hasItem(DEFAULT_CEI_FECHA_MODIFICACION.toString())))
            .andExpect(jsonPath("$.[*].ceiNombre").value(hasItem(DEFAULT_CEI_NOMBRE)));
    }
    
    @Test
    @Transactional
    public void getCriteriosEvaluacionXItem() throws Exception {
        // Initialize the database
        criteriosEvaluacionXItemRepository.saveAndFlush(criteriosEvaluacionXItem);

        // Get the criteriosEvaluacionXItem
        restCriteriosEvaluacionXItemMockMvc.perform(get("/api/criterios-evaluacion-x-items/{id}", criteriosEvaluacionXItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(criteriosEvaluacionXItem.getId().intValue()))
            .andExpect(jsonPath("$.idCriteriosEvaluacionXItem").value(DEFAULT_ID_CRITERIOS_EVALUACION_X_ITEM))
            .andExpect(jsonPath("$.ceiFechaCreacion").value(DEFAULT_CEI_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.ceiFechaModificacion").value(DEFAULT_CEI_FECHA_MODIFICACION.toString()))
            .andExpect(jsonPath("$.ceiNombre").value(DEFAULT_CEI_NOMBRE));
    }
    @Test
    @Transactional
    public void getNonExistingCriteriosEvaluacionXItem() throws Exception {
        // Get the criteriosEvaluacionXItem
        restCriteriosEvaluacionXItemMockMvc.perform(get("/api/criterios-evaluacion-x-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCriteriosEvaluacionXItem() throws Exception {
        // Initialize the database
        criteriosEvaluacionXItemRepository.saveAndFlush(criteriosEvaluacionXItem);

        int databaseSizeBeforeUpdate = criteriosEvaluacionXItemRepository.findAll().size();

        // Update the criteriosEvaluacionXItem
        CriteriosEvaluacionXItem updatedCriteriosEvaluacionXItem = criteriosEvaluacionXItemRepository.findById(criteriosEvaluacionXItem.getId()).get();
        // Disconnect from session so that the updates on updatedCriteriosEvaluacionXItem are not directly saved in db
        em.detach(updatedCriteriosEvaluacionXItem);
        updatedCriteriosEvaluacionXItem
            .idCriteriosEvaluacionXItem(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM)
            .ceiFechaCreacion(UPDATED_CEI_FECHA_CREACION)
            .ceiFechaModificacion(UPDATED_CEI_FECHA_MODIFICACION)
            .ceiNombre(UPDATED_CEI_NOMBRE);

        restCriteriosEvaluacionXItemMockMvc.perform(put("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedCriteriosEvaluacionXItem)))
            .andExpect(status().isOk());

        // Validate the CriteriosEvaluacionXItem in the database
        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeUpdate);
        CriteriosEvaluacionXItem testCriteriosEvaluacionXItem = criteriosEvaluacionXItemList.get(criteriosEvaluacionXItemList.size() - 1);
        assertThat(testCriteriosEvaluacionXItem.getIdCriteriosEvaluacionXItem()).isEqualTo(UPDATED_ID_CRITERIOS_EVALUACION_X_ITEM);
        assertThat(testCriteriosEvaluacionXItem.getCeiFechaCreacion()).isEqualTo(UPDATED_CEI_FECHA_CREACION);
        assertThat(testCriteriosEvaluacionXItem.getCeiFechaModificacion()).isEqualTo(UPDATED_CEI_FECHA_MODIFICACION);
        assertThat(testCriteriosEvaluacionXItem.getCeiNombre()).isEqualTo(UPDATED_CEI_NOMBRE);
    }

    @Test
    @Transactional
    public void updateNonExistingCriteriosEvaluacionXItem() throws Exception {
        int databaseSizeBeforeUpdate = criteriosEvaluacionXItemRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCriteriosEvaluacionXItemMockMvc.perform(put("/api/criterios-evaluacion-x-items")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(criteriosEvaluacionXItem)))
            .andExpect(status().isBadRequest());

        // Validate the CriteriosEvaluacionXItem in the database
        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCriteriosEvaluacionXItem() throws Exception {
        // Initialize the database
        criteriosEvaluacionXItemRepository.saveAndFlush(criteriosEvaluacionXItem);

        int databaseSizeBeforeDelete = criteriosEvaluacionXItemRepository.findAll().size();

        // Delete the criteriosEvaluacionXItem
        restCriteriosEvaluacionXItemMockMvc.perform(delete("/api/criterios-evaluacion-x-items/{id}", criteriosEvaluacionXItem.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CriteriosEvaluacionXItem> criteriosEvaluacionXItemList = criteriosEvaluacionXItemRepository.findAll();
        assertThat(criteriosEvaluacionXItemList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
