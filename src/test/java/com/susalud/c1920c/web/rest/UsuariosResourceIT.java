package com.susalud.c1920c.web.rest;

import com.susalud.c1920c.SusaludApp;
import com.susalud.c1920c.domain.Usuarios;
import com.susalud.c1920c.repository.UsuariosRepository;

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
 * Integration tests for the {@link UsuariosResource} REST controller.
 */
@SpringBootTest(classes = SusaludApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UsuariosResourceIT {

    private static final Integer DEFAULT_ID_USUARIO = 1;
    private static final Integer UPDATED_ID_USUARIO = 2;

    private static final String DEFAULT_US_USUARIO = "AAAAAAAAAA";
    private static final String UPDATED_US_USUARIO = "BBBBBBBBBB";

    private static final String DEFAULT_US_CONTRASENA = "AAAAAAAAAA";
    private static final String UPDATED_US_CONTRASENA = "BBBBBBBBBB";

    private static final Integer DEFAULT_USUARIOS_ID_USUARIOS = 1;
    private static final Integer UPDATED_USUARIOS_ID_USUARIOS = 2;

    private static final Integer DEFAULT_USUARIOS_PERFIL_ID_PERFIL = 1;
    private static final Integer UPDATED_USUARIOS_PERFIL_ID_PERFIL = 2;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUsuariosMockMvc;

    private Usuarios usuarios;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .idUsuario(DEFAULT_ID_USUARIO)
            .usUsuario(DEFAULT_US_USUARIO)
            .usContrasena(DEFAULT_US_CONTRASENA)
            .usuariosIdUsuarios(DEFAULT_USUARIOS_ID_USUARIOS)
            .usuariosPerfilIdPerfil(DEFAULT_USUARIOS_PERFIL_ID_PERFIL);
        return usuarios;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Usuarios createUpdatedEntity(EntityManager em) {
        Usuarios usuarios = new Usuarios()
            .idUsuario(UPDATED_ID_USUARIO)
            .usUsuario(UPDATED_US_USUARIO)
            .usContrasena(UPDATED_US_CONTRASENA)
            .usuariosIdUsuarios(UPDATED_USUARIOS_ID_USUARIOS)
            .usuariosPerfilIdPerfil(UPDATED_USUARIOS_PERFIL_ID_PERFIL);
        return usuarios;
    }

    @BeforeEach
    public void initTest() {
        usuarios = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarios() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();
        // Create the Usuarios
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isCreated());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate + 1);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testUsuarios.getUsUsuario()).isEqualTo(DEFAULT_US_USUARIO);
        assertThat(testUsuarios.getUsContrasena()).isEqualTo(DEFAULT_US_CONTRASENA);
        assertThat(testUsuarios.getUsuariosIdUsuarios()).isEqualTo(DEFAULT_USUARIOS_ID_USUARIOS);
        assertThat(testUsuarios.getUsuariosPerfilIdPerfil()).isEqualTo(DEFAULT_USUARIOS_PERFIL_ID_PERFIL);
    }

    @Test
    @Transactional
    public void createUsuariosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuariosRepository.findAll().size();

        // Create the Usuarios with an existing ID
        usuarios.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkIdUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setIdUsuario(null);

        // Create the Usuarios, which fails.


        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsUsuarioIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setUsUsuario(null);

        // Create the Usuarios, which fails.


        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsContrasenaIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setUsContrasena(null);

        // Create the Usuarios, which fails.


        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsuariosIdUsuariosIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setUsuariosIdUsuarios(null);

        // Create the Usuarios, which fails.


        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsuariosPerfilIdPerfilIsRequired() throws Exception {
        int databaseSizeBeforeTest = usuariosRepository.findAll().size();
        // set the field null
        usuarios.setUsuariosPerfilIdPerfil(null);

        // Create the Usuarios, which fails.


        restUsuariosMockMvc.perform(post("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get all the usuariosList
        restUsuariosMockMvc.perform(get("/api/usuarios?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarios.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO)))
            .andExpect(jsonPath("$.[*].usUsuario").value(hasItem(DEFAULT_US_USUARIO)))
            .andExpect(jsonPath("$.[*].usContrasena").value(hasItem(DEFAULT_US_CONTRASENA)))
            .andExpect(jsonPath("$.[*].usuariosIdUsuarios").value(hasItem(DEFAULT_USUARIOS_ID_USUARIOS)))
            .andExpect(jsonPath("$.[*].usuariosPerfilIdPerfil").value(hasItem(DEFAULT_USUARIOS_PERFIL_ID_PERFIL)));
    }
    
    @Test
    @Transactional
    public void getUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", usuarios.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(usuarios.getId().intValue()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO))
            .andExpect(jsonPath("$.usUsuario").value(DEFAULT_US_USUARIO))
            .andExpect(jsonPath("$.usContrasena").value(DEFAULT_US_CONTRASENA))
            .andExpect(jsonPath("$.usuariosIdUsuarios").value(DEFAULT_USUARIOS_ID_USUARIOS))
            .andExpect(jsonPath("$.usuariosPerfilIdPerfil").value(DEFAULT_USUARIOS_PERFIL_ID_PERFIL));
    }
    @Test
    @Transactional
    public void getNonExistingUsuarios() throws Exception {
        // Get the usuarios
        restUsuariosMockMvc.perform(get("/api/usuarios/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // Update the usuarios
        Usuarios updatedUsuarios = usuariosRepository.findById(usuarios.getId()).get();
        // Disconnect from session so that the updates on updatedUsuarios are not directly saved in db
        em.detach(updatedUsuarios);
        updatedUsuarios
            .idUsuario(UPDATED_ID_USUARIO)
            .usUsuario(UPDATED_US_USUARIO)
            .usContrasena(UPDATED_US_CONTRASENA)
            .usuariosIdUsuarios(UPDATED_USUARIOS_ID_USUARIOS)
            .usuariosPerfilIdPerfil(UPDATED_USUARIOS_PERFIL_ID_PERFIL);

        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUsuarios)))
            .andExpect(status().isOk());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
        Usuarios testUsuarios = usuariosList.get(usuariosList.size() - 1);
        assertThat(testUsuarios.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testUsuarios.getUsUsuario()).isEqualTo(UPDATED_US_USUARIO);
        assertThat(testUsuarios.getUsContrasena()).isEqualTo(UPDATED_US_CONTRASENA);
        assertThat(testUsuarios.getUsuariosIdUsuarios()).isEqualTo(UPDATED_USUARIOS_ID_USUARIOS);
        assertThat(testUsuarios.getUsuariosPerfilIdPerfil()).isEqualTo(UPDATED_USUARIOS_PERFIL_ID_PERFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarios() throws Exception {
        int databaseSizeBeforeUpdate = usuariosRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuariosMockMvc.perform(put("/api/usuarios")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(usuarios)))
            .andExpect(status().isBadRequest());

        // Validate the Usuarios in the database
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuarios() throws Exception {
        // Initialize the database
        usuariosRepository.saveAndFlush(usuarios);

        int databaseSizeBeforeDelete = usuariosRepository.findAll().size();

        // Delete the usuarios
        restUsuariosMockMvc.perform(delete("/api/usuarios/{id}", usuarios.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Usuarios> usuariosList = usuariosRepository.findAll();
        assertThat(usuariosList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
