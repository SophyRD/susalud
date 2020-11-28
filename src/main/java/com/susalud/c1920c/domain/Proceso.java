package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Proceso.
 */
@Entity
@Table(name = "proceso")
public class Proceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_proceso", nullable = false)
    private Integer idProceso;

    @NotNull
    @Column(name = "pr_descripcion", nullable = false)
    private String prDescripcion;

    @NotNull
    @Column(name = "id_macroproceso", nullable = false)
    private LocalDate idMacroproceso;

    @NotNull
    @Column(name = "id_verificador", nullable = false)
    private Integer idVerificador;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public Proceso idProceso(Integer idProceso) {
        this.idProceso = idProceso;
        return this;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getPrDescripcion() {
        return prDescripcion;
    }

    public Proceso prDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
        return this;
    }

    public void setPrDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
    }

    public LocalDate getIdMacroproceso() {
        return idMacroproceso;
    }

    public Proceso idMacroproceso(LocalDate idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
        return this;
    }

    public void setIdMacroproceso(LocalDate idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public Proceso idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proceso)) {
            return false;
        }
        return id != null && id.equals(((Proceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proceso{" +
            "id=" + getId() +
            ", idProceso=" + getIdProceso() +
            ", prDescripcion='" + getPrDescripcion() + "'" +
            ", idMacroproceso='" + getIdMacroproceso() + "'" +
            ", idVerificador=" + getIdVerificador() +
            "}";
    }
}
