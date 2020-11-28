package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CriteriosEvaluacion.
 */
@Entity
@Table(name = "criterios_evaluacion")
public class CriteriosEvaluacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_criterios_evaluacion", nullable = false)
    private Integer idCriteriosEvaluacion;

    @NotNull
    @Column(name = "ce_nombre", nullable = false)
    private String ceNombre;

    @NotNull
    @Column(name = "ce_fecha_creacion", nullable = false)
    private LocalDate ceFechaCreacion;

    @NotNull
    @Column(name = "ce_fecha_modificacion", nullable = false)
    private LocalDate ceFechaModificacion;

    @NotNull
    @Column(name = "id_autoevalucion_xproceso", nullable = false)
    private Integer idAutoevalucionXproceso;

    @NotNull
    @Column(name = "id_autoevalucion", nullable = false)
    private Integer idAutoevalucion;

    @NotNull
    @Column(name = "id_mes", nullable = false)
    private Integer idMes;

    @NotNull
    @Column(name = "id_usuarios_xevaluacion", nullable = false)
    private Integer idUsuariosXevaluacion;

    @NotNull
    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    @NotNull
    @Column(name = "id_verificador", nullable = false)
    private Integer idVerificador;

    @NotNull
    @Column(name = "id_criterios_evaluacion_x_item", nullable = false)
    private Integer idCriteriosEvaluacionXItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCriteriosEvaluacion() {
        return idCriteriosEvaluacion;
    }

    public CriteriosEvaluacion idCriteriosEvaluacion(Integer idCriteriosEvaluacion) {
        this.idCriteriosEvaluacion = idCriteriosEvaluacion;
        return this;
    }

    public void setIdCriteriosEvaluacion(Integer idCriteriosEvaluacion) {
        this.idCriteriosEvaluacion = idCriteriosEvaluacion;
    }

    public String getCeNombre() {
        return ceNombre;
    }

    public CriteriosEvaluacion ceNombre(String ceNombre) {
        this.ceNombre = ceNombre;
        return this;
    }

    public void setCeNombre(String ceNombre) {
        this.ceNombre = ceNombre;
    }

    public LocalDate getCeFechaCreacion() {
        return ceFechaCreacion;
    }

    public CriteriosEvaluacion ceFechaCreacion(LocalDate ceFechaCreacion) {
        this.ceFechaCreacion = ceFechaCreacion;
        return this;
    }

    public void setCeFechaCreacion(LocalDate ceFechaCreacion) {
        this.ceFechaCreacion = ceFechaCreacion;
    }

    public LocalDate getCeFechaModificacion() {
        return ceFechaModificacion;
    }

    public CriteriosEvaluacion ceFechaModificacion(LocalDate ceFechaModificacion) {
        this.ceFechaModificacion = ceFechaModificacion;
        return this;
    }

    public void setCeFechaModificacion(LocalDate ceFechaModificacion) {
        this.ceFechaModificacion = ceFechaModificacion;
    }

    public Integer getIdAutoevalucionXproceso() {
        return idAutoevalucionXproceso;
    }

    public CriteriosEvaluacion idAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
        return this;
    }

    public void setIdAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
    }

    public Integer getIdAutoevalucion() {
        return idAutoevalucion;
    }

    public CriteriosEvaluacion idAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
        return this;
    }

    public void setIdAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public CriteriosEvaluacion idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public CriteriosEvaluacion idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public CriteriosEvaluacion idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public CriteriosEvaluacion idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }

    public Integer getIdCriteriosEvaluacionXItem() {
        return idCriteriosEvaluacionXItem;
    }

    public CriteriosEvaluacion idCriteriosEvaluacionXItem(Integer idCriteriosEvaluacionXItem) {
        this.idCriteriosEvaluacionXItem = idCriteriosEvaluacionXItem;
        return this;
    }

    public void setIdCriteriosEvaluacionXItem(Integer idCriteriosEvaluacionXItem) {
        this.idCriteriosEvaluacionXItem = idCriteriosEvaluacionXItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CriteriosEvaluacion)) {
            return false;
        }
        return id != null && id.equals(((CriteriosEvaluacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CriteriosEvaluacion{" +
            "id=" + getId() +
            ", idCriteriosEvaluacion=" + getIdCriteriosEvaluacion() +
            ", ceNombre='" + getCeNombre() + "'" +
            ", ceFechaCreacion='" + getCeFechaCreacion() + "'" +
            ", ceFechaModificacion='" + getCeFechaModificacion() + "'" +
            ", idAutoevalucionXproceso=" + getIdAutoevalucionXproceso() +
            ", idAutoevalucion=" + getIdAutoevalucion() +
            ", idMes=" + getIdMes() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", idEstado=" + getIdEstado() +
            ", idVerificador=" + getIdVerificador() +
            ", idCriteriosEvaluacionXItem=" + getIdCriteriosEvaluacionXItem() +
            "}";
    }
}
