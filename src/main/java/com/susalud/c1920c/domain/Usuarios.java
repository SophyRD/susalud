package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Usuarios.
 */
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull
    @Column(name = "us_usuario", nullable = false)
    private String usUsuario;

    @NotNull
    @Column(name = "us_contrasena", nullable = false)
    private String usContrasena;

    @NotNull
    @Column(name = "usuarios_id_usuarios", nullable = false)
    private Integer usuariosIdUsuarios;

    @NotNull
    @Column(name = "usuarios_perfil_id_perfil", nullable = false)
    private Integer usuariosPerfilIdPerfil;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Usuarios idUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsUsuario() {
        return usUsuario;
    }

    public Usuarios usUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
        return this;
    }

    public void setUsUsuario(String usUsuario) {
        this.usUsuario = usUsuario;
    }

    public String getUsContrasena() {
        return usContrasena;
    }

    public Usuarios usContrasena(String usContrasena) {
        this.usContrasena = usContrasena;
        return this;
    }

    public void setUsContrasena(String usContrasena) {
        this.usContrasena = usContrasena;
    }

    public Integer getUsuariosIdUsuarios() {
        return usuariosIdUsuarios;
    }

    public Usuarios usuariosIdUsuarios(Integer usuariosIdUsuarios) {
        this.usuariosIdUsuarios = usuariosIdUsuarios;
        return this;
    }

    public void setUsuariosIdUsuarios(Integer usuariosIdUsuarios) {
        this.usuariosIdUsuarios = usuariosIdUsuarios;
    }

    public Integer getUsuariosPerfilIdPerfil() {
        return usuariosPerfilIdPerfil;
    }

    public Usuarios usuariosPerfilIdPerfil(Integer usuariosPerfilIdPerfil) {
        this.usuariosPerfilIdPerfil = usuariosPerfilIdPerfil;
        return this;
    }

    public void setUsuariosPerfilIdPerfil(Integer usuariosPerfilIdPerfil) {
        this.usuariosPerfilIdPerfil = usuariosPerfilIdPerfil;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Usuarios)) {
            return false;
        }
        return id != null && id.equals(((Usuarios) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Usuarios{" +
            "id=" + getId() +
            ", idUsuario=" + getIdUsuario() +
            ", usUsuario='" + getUsUsuario() + "'" +
            ", usContrasena='" + getUsContrasena() + "'" +
            ", usuariosIdUsuarios=" + getUsuariosIdUsuarios() +
            ", usuariosPerfilIdPerfil=" + getUsuariosPerfilIdPerfil() +
            "}";
    }
}
