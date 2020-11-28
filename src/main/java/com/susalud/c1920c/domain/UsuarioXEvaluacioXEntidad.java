package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UsuarioXEvaluacioXEntidad.
 */
@Entity
@Table(name = "usuario_x_evaluacio_x_entidad")
public class UsuarioXEvaluacioXEntidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_usuario_x_evaluacio_x_entidad", nullable = false)
    private Integer idUsuarioXEvaluacioXEntidad;

    @NotNull
    @Column(name = "uee_fecha_creacion", nullable = false)
    private LocalDate ueeFechaCreacion;

    @NotNull
    @Column(name = "uee_fecha_modificacion", nullable = false)
    private LocalDate ueeFechaModificacion;

    @NotNull
    @Column(name = "id_usuarios_xevaluacion", nullable = false)
    private Integer idUsuariosXevaluacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUsuarioXEvaluacioXEntidad() {
        return idUsuarioXEvaluacioXEntidad;
    }

    public UsuarioXEvaluacioXEntidad idUsuarioXEvaluacioXEntidad(Integer idUsuarioXEvaluacioXEntidad) {
        this.idUsuarioXEvaluacioXEntidad = idUsuarioXEvaluacioXEntidad;
        return this;
    }

    public void setIdUsuarioXEvaluacioXEntidad(Integer idUsuarioXEvaluacioXEntidad) {
        this.idUsuarioXEvaluacioXEntidad = idUsuarioXEvaluacioXEntidad;
    }

    public LocalDate getUeeFechaCreacion() {
        return ueeFechaCreacion;
    }

    public UsuarioXEvaluacioXEntidad ueeFechaCreacion(LocalDate ueeFechaCreacion) {
        this.ueeFechaCreacion = ueeFechaCreacion;
        return this;
    }

    public void setUeeFechaCreacion(LocalDate ueeFechaCreacion) {
        this.ueeFechaCreacion = ueeFechaCreacion;
    }

    public LocalDate getUeeFechaModificacion() {
        return ueeFechaModificacion;
    }

    public UsuarioXEvaluacioXEntidad ueeFechaModificacion(LocalDate ueeFechaModificacion) {
        this.ueeFechaModificacion = ueeFechaModificacion;
        return this;
    }

    public void setUeeFechaModificacion(LocalDate ueeFechaModificacion) {
        this.ueeFechaModificacion = ueeFechaModificacion;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public UsuarioXEvaluacioXEntidad idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuarioXEvaluacioXEntidad)) {
            return false;
        }
        return id != null && id.equals(((UsuarioXEvaluacioXEntidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsuarioXEvaluacioXEntidad{" +
            "id=" + getId() +
            ", idUsuarioXEvaluacioXEntidad=" + getIdUsuarioXEvaluacioXEntidad() +
            ", ueeFechaCreacion='" + getUeeFechaCreacion() + "'" +
            ", ueeFechaModificacion='" + getUeeFechaModificacion() + "'" +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            "}";
    }
}
