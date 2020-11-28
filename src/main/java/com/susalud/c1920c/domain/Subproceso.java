package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Subproceso.
 */
@Entity
@Table(name = "subproceso")
public class Subproceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_subproceso", nullable = false)
    private Integer idSubproceso;

    @NotNull
    @Column(name = "sp_descripcion", nullable = false)
    private String spDescripcion;

    @NotNull
    @Column(name = "id_proceso", nullable = false)
    private Integer idProceso;

    @NotNull
    @Column(name = "id_macroproceso", nullable = false)
    private Integer idMacroproceso;

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

    public Integer getIdSubproceso() {
        return idSubproceso;
    }

    public Subproceso idSubproceso(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
        return this;
    }

    public void setIdSubproceso(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public String getSpDescripcion() {
        return spDescripcion;
    }

    public Subproceso spDescripcion(String spDescripcion) {
        this.spDescripcion = spDescripcion;
        return this;
    }

    public void setSpDescripcion(String spDescripcion) {
        this.spDescripcion = spDescripcion;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public Subproceso idProceso(Integer idProceso) {
        this.idProceso = idProceso;
        return this;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public Integer getIdMacroproceso() {
        return idMacroproceso;
    }

    public Subproceso idMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
        return this;
    }

    public void setIdMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public Subproceso idVerificador(Integer idVerificador) {
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
        if (!(o instanceof Subproceso)) {
            return false;
        }
        return id != null && id.equals(((Subproceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subproceso{" +
            "id=" + getId() +
            ", idSubproceso=" + getIdSubproceso() +
            ", spDescripcion='" + getSpDescripcion() + "'" +
            ", idProceso=" + getIdProceso() +
            ", idMacroproceso=" + getIdMacroproceso() +
            ", idVerificador=" + getIdVerificador() +
            "}";
    }
}
