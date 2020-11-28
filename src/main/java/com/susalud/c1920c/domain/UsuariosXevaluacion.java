package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UsuariosXevaluacion.
 */
@Entity
@Table(name = "usuarios_xevaluacion")
public class UsuariosXevaluacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_usuarios_xevaluacion", nullable = false)
    private Integer idUsuariosXevaluacion;

    @NotNull
    @Column(name = "u_fecha_modificacion", nullable = false)
    private LocalDate uFechaModificacion;

    @NotNull
    @Column(name = "u_fecha_creacion", nullable = false)
    private LocalDate uFechaCreacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public UsuariosXevaluacion idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public LocalDate getuFechaModificacion() {
        return uFechaModificacion;
    }

    public UsuariosXevaluacion uFechaModificacion(LocalDate uFechaModificacion) {
        this.uFechaModificacion = uFechaModificacion;
        return this;
    }

    public void setuFechaModificacion(LocalDate uFechaModificacion) {
        this.uFechaModificacion = uFechaModificacion;
    }

    public LocalDate getuFechaCreacion() {
        return uFechaCreacion;
    }

    public UsuariosXevaluacion uFechaCreacion(LocalDate uFechaCreacion) {
        this.uFechaCreacion = uFechaCreacion;
        return this;
    }

    public void setuFechaCreacion(LocalDate uFechaCreacion) {
        this.uFechaCreacion = uFechaCreacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuariosXevaluacion)) {
            return false;
        }
        return id != null && id.equals(((UsuariosXevaluacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UsuariosXevaluacion{" +
            "id=" + getId() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", uFechaModificacion='" + getuFechaModificacion() + "'" +
            ", uFechaCreacion='" + getuFechaCreacion() + "'" +
            "}";
    }
}
