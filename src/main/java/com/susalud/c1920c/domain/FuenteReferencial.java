package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A FuenteReferencial.
 */
@Entity
@Table(name = "fuente_referencial")
public class FuenteReferencial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_fuente_referencial", nullable = false)
    private Integer idFuenteReferencial;

    @NotNull
    @Column(name = "fr_nombre", nullable = false)
    private String frNombre;

    @NotNull
    @Column(name = "fr_fecha_creacion", nullable = false)
    private LocalDate frFechaCreacion;

    @NotNull
    @Column(name = "fr_fecha_modificacion", nullable = false)
    private LocalDate frFechaModificacion;

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
    @Column(name = "id_fuente_referencia_x_item", nullable = false)
    private Integer idFuenteReferenciaXItem;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFuenteReferencial() {
        return idFuenteReferencial;
    }

    public FuenteReferencial idFuenteReferencial(Integer idFuenteReferencial) {
        this.idFuenteReferencial = idFuenteReferencial;
        return this;
    }

    public void setIdFuenteReferencial(Integer idFuenteReferencial) {
        this.idFuenteReferencial = idFuenteReferencial;
    }

    public String getFrNombre() {
        return frNombre;
    }

    public FuenteReferencial frNombre(String frNombre) {
        this.frNombre = frNombre;
        return this;
    }

    public void setFrNombre(String frNombre) {
        this.frNombre = frNombre;
    }

    public LocalDate getFrFechaCreacion() {
        return frFechaCreacion;
    }

    public FuenteReferencial frFechaCreacion(LocalDate frFechaCreacion) {
        this.frFechaCreacion = frFechaCreacion;
        return this;
    }

    public void setFrFechaCreacion(LocalDate frFechaCreacion) {
        this.frFechaCreacion = frFechaCreacion;
    }

    public LocalDate getFrFechaModificacion() {
        return frFechaModificacion;
    }

    public FuenteReferencial frFechaModificacion(LocalDate frFechaModificacion) {
        this.frFechaModificacion = frFechaModificacion;
        return this;
    }

    public void setFrFechaModificacion(LocalDate frFechaModificacion) {
        this.frFechaModificacion = frFechaModificacion;
    }

    public Integer getIdAutoevalucionXproceso() {
        return idAutoevalucionXproceso;
    }

    public FuenteReferencial idAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
        return this;
    }

    public void setIdAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
    }

    public Integer getIdAutoevalucion() {
        return idAutoevalucion;
    }

    public FuenteReferencial idAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
        return this;
    }

    public void setIdAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public FuenteReferencial idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public FuenteReferencial idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public FuenteReferencial idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public FuenteReferencial idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }

    public Integer getIdFuenteReferenciaXItem() {
        return idFuenteReferenciaXItem;
    }

    public FuenteReferencial idFuenteReferenciaXItem(Integer idFuenteReferenciaXItem) {
        this.idFuenteReferenciaXItem = idFuenteReferenciaXItem;
        return this;
    }

    public void setIdFuenteReferenciaXItem(Integer idFuenteReferenciaXItem) {
        this.idFuenteReferenciaXItem = idFuenteReferenciaXItem;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FuenteReferencial)) {
            return false;
        }
        return id != null && id.equals(((FuenteReferencial) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FuenteReferencial{" +
            "id=" + getId() +
            ", idFuenteReferencial=" + getIdFuenteReferencial() +
            ", frNombre='" + getFrNombre() + "'" +
            ", frFechaCreacion='" + getFrFechaCreacion() + "'" +
            ", frFechaModificacion='" + getFrFechaModificacion() + "'" +
            ", idAutoevalucionXproceso=" + getIdAutoevalucionXproceso() +
            ", idAutoevalucion=" + getIdAutoevalucion() +
            ", idMes=" + getIdMes() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", idEstado=" + getIdEstado() +
            ", idVerificador=" + getIdVerificador() +
            ", idFuenteReferenciaXItem=" + getIdFuenteReferenciaXItem() +
            "}";
    }
}
