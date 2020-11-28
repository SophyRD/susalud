package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TecnicasEvaluativas.
 */
@Entity
@Table(name = "tecnicas_evaluativas")
public class TecnicasEvaluativas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_tecnicas_evaluativas", nullable = false)
    private Integer idTecnicasEvaluativas;

    @NotNull
    @Column(name = "te_nombre", nullable = false)
    private String teNombre;

    @NotNull
    @Column(name = "te_fecha_creacion", nullable = false)
    private LocalDate teFechaCreacion;

    @NotNull
    @Column(name = "te_fecha_modificacion", nullable = false)
    private LocalDate teFechaModificacion;

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
    @Column(name = "id_tecnicas_evaluativas_x_item", nullable = false)
    private Integer idTecnicasEvaluativasXItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTecnicasEvaluativas() {
        return idTecnicasEvaluativas;
    }

    public TecnicasEvaluativas idTecnicasEvaluativas(Integer idTecnicasEvaluativas) {
        this.idTecnicasEvaluativas = idTecnicasEvaluativas;
        return this;
    }

    public void setIdTecnicasEvaluativas(Integer idTecnicasEvaluativas) {
        this.idTecnicasEvaluativas = idTecnicasEvaluativas;
    }

    public String getTeNombre() {
        return teNombre;
    }

    public TecnicasEvaluativas teNombre(String teNombre) {
        this.teNombre = teNombre;
        return this;
    }

    public void setTeNombre(String teNombre) {
        this.teNombre = teNombre;
    }

    public LocalDate getTeFechaCreacion() {
        return teFechaCreacion;
    }

    public TecnicasEvaluativas teFechaCreacion(LocalDate teFechaCreacion) {
        this.teFechaCreacion = teFechaCreacion;
        return this;
    }

    public void setTeFechaCreacion(LocalDate teFechaCreacion) {
        this.teFechaCreacion = teFechaCreacion;
    }

    public LocalDate getTeFechaModificacion() {
        return teFechaModificacion;
    }

    public TecnicasEvaluativas teFechaModificacion(LocalDate teFechaModificacion) {
        this.teFechaModificacion = teFechaModificacion;
        return this;
    }

    public void setTeFechaModificacion(LocalDate teFechaModificacion) {
        this.teFechaModificacion = teFechaModificacion;
    }

    public Integer getIdAutoevalucionXproceso() {
        return idAutoevalucionXproceso;
    }

    public TecnicasEvaluativas idAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
        return this;
    }

    public void setIdAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
    }

    public Integer getIdAutoevalucion() {
        return idAutoevalucion;
    }

    public TecnicasEvaluativas idAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
        return this;
    }

    public void setIdAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public TecnicasEvaluativas idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public TecnicasEvaluativas idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public TecnicasEvaluativas idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public TecnicasEvaluativas idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }

    public Integer getIdTecnicasEvaluativasXItem() {
        return idTecnicasEvaluativasXItem;
    }

    public TecnicasEvaluativas idTecnicasEvaluativasXItem(Integer idTecnicasEvaluativasXItem) {
        this.idTecnicasEvaluativasXItem = idTecnicasEvaluativasXItem;
        return this;
    }

    public void setIdTecnicasEvaluativasXItem(Integer idTecnicasEvaluativasXItem) {
        this.idTecnicasEvaluativasXItem = idTecnicasEvaluativasXItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TecnicasEvaluativas)) {
            return false;
        }
        return id != null && id.equals(((TecnicasEvaluativas) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecnicasEvaluativas{" +
            "id=" + getId() +
            ", idTecnicasEvaluativas=" + getIdTecnicasEvaluativas() +
            ", teNombre='" + getTeNombre() + "'" +
            ", teFechaCreacion='" + getTeFechaCreacion() + "'" +
            ", teFechaModificacion='" + getTeFechaModificacion() + "'" +
            ", idAutoevalucionXproceso=" + getIdAutoevalucionXproceso() +
            ", idAutoevalucion=" + getIdAutoevalucion() +
            ", idMes=" + getIdMes() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", idEstado=" + getIdEstado() +
            ", idVerificador=" + getIdVerificador() +
            ", idTecnicasEvaluativasXItem=" + getIdTecnicasEvaluativasXItem() +
            "}";
    }
}
