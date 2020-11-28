package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.ProyectoApp;
import com.mycompany.myapp.domain.Remove;
import com.mycompany.myapp.repository.RemoveRepository;

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
 * Integration tests for the {@link RemoveResource} REST controller.
 */
@SpringBootTest(classes = ProyectoApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class RemoveResourceIT {

    @Autowired
    private RemoveRepository removeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRemoveMockMvc;

    private Remove remove;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Remove createEntity(EntityManager em) {
        Remove remove = new Remove();
        return remove;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Remove createUpdatedEntity(EntityManager em) {
        Remove remove = new Remove();
        return remove;
    }

    @BeforeEach
    public void initTest() {
        remove = createEntity(em);
    }

    @Test
    @Transactional
    public void createRemove() throws Exception {
        int databaseSizeBeforeCreate = removeRepository.findAll().size();
        // Create the Remove
        restRemoveMockMvc.perform(post("/api/removes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remove)))
            .andExpect(status().isCreated());

        // Validate the Remove in the database
        List<Remove> removeList = removeRepository.findAll();
        assertThat(removeList).hasSize(databaseSizeBeforeCreate + 1);
        Remove testRemove = removeList.get(removeList.size() - 1);
    }

    @Test
    @Transactional
    public void createRemoveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = removeRepository.findAll().size();

        // Create the Remove with an existing ID
        remove.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRemoveMockMvc.perform(post("/api/removes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remove)))
            .andExpect(status().isBadRequest());

        // Validate the Remove in the database
        List<Remove> removeList = removeRepository.findAll();
        assertThat(removeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllRemoves() throws Exception {
        // Initialize the database
        removeRepository.saveAndFlush(remove);

        // Get all the removeList
        restRemoveMockMvc.perform(get("/api/removes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(remove.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getRemove() throws Exception {
        // Initialize the database
        removeRepository.saveAndFlush(remove);

        // Get the remove
        restRemoveMockMvc.perform(get("/api/removes/{id}", remove.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(remove.getId().intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingRemove() throws Exception {
        // Get the remove
        restRemoveMockMvc.perform(get("/api/removes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRemove() throws Exception {
        // Initialize the database
        removeRepository.saveAndFlush(remove);

        int databaseSizeBeforeUpdate = removeRepository.findAll().size();

        // Update the remove
        Remove updatedRemove = removeRepository.findById(remove.getId()).get();
        // Disconnect from session so that the updates on updatedRemove are not directly saved in db
        em.detach(updatedRemove);

        restRemoveMockMvc.perform(put("/api/removes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedRemove)))
            .andExpect(status().isOk());

        // Validate the Remove in the database
        List<Remove> removeList = removeRepository.findAll();
        assertThat(removeList).hasSize(databaseSizeBeforeUpdate);
        Remove testRemove = removeList.get(removeList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingRemove() throws Exception {
        int databaseSizeBeforeUpdate = removeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRemoveMockMvc.perform(put("/api/removes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(remove)))
            .andExpect(status().isBadRequest());

        // Validate the Remove in the database
        List<Remove> removeList = removeRepository.findAll();
        assertThat(removeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRemove() throws Exception {
        // Initialize the database
        removeRepository.saveAndFlush(remove);

        int databaseSizeBeforeDelete = removeRepository.findAll().size();

        // Delete the remove
        restRemoveMockMvc.perform(delete("/api/removes/{id}", remove.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Remove> removeList = removeRepository.findAll();
        assertThat(removeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
