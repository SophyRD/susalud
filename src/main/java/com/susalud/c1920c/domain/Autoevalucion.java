package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Autoevalucion.
 */
@Entity
@Table(name = "autoevalucion")
public class Autoevalucion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_autoevalucion", nullable = false)
    private Integer idAutoevalucion;

    @NotNull
    @Column(name = "a_avance", nullable = false)
    private String aAvance;

    @NotNull
    @Column(name = "id_mes", nullable = false)
    private Integer idMes;

    @NotNull
    @Column(name = "id_usuarios_xevaluacion", nullable = false)
    private Integer idUsuariosXevaluacion;

    @NotNull
    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAutoevalucion() {
        return idAutoevalucion;
    }

    public Autoevalucion idAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
        return this;
    }

    public void setIdAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
    }

    public String getaAvance() {
        return aAvance;
    }

    public Autoevalucion aAvance(String aAvance) {
        this.aAvance = aAvance;
        return this;
    }

    public void setaAvance(String aAvance) {
        this.aAvance = aAvance;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public Autoevalucion idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public Autoevalucion idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public Autoevalucion idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autoevalucion)) {
            return false;
        }
        return id != null && id.equals(((Autoevalucion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Autoevalucion{" +
            "id=" + getId() +
            ", idAutoevalucion=" + getIdAutoevalucion() +
            ", aAvance='" + getaAvance() + "'" +
            ", idMes=" + getIdMes() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", idEstado=" + getIdEstado() +
            "}";
    }
}
