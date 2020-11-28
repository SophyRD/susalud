package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Macroproceso.
 */
@Entity
@Table(name = "macroproceso")
public class Macroproceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_macroproceso", nullable = false)
    private Integer idMacroproceso;

    @NotNull
    @Column(name = "ma_fecha_creacion", nullable = false)
    private LocalDate maFechaCreacion;

    @NotNull
    @Column(name = "ma_fecha_modificacion", nullable = false)
    private LocalDate maFechaModificacion;

    @NotNull
    @Column(name = "verificadorl_id_verificador", nullable = false)
    private Integer verificadorlIdVerificador;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdMacroproceso() {
        return idMacroproceso;
    }

    public Macroproceso idMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
        return this;
    }

    public void setIdMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
    }

    public LocalDate getMaFechaCreacion() {
        return maFechaCreacion;
    }

    public Macroproceso maFechaCreacion(LocalDate maFechaCreacion) {
        this.maFechaCreacion = maFechaCreacion;
        return this;
    }

    public void setMaFechaCreacion(LocalDate maFechaCreacion) {
        this.maFechaCreacion = maFechaCreacion;
    }

    public LocalDate getMaFechaModificacion() {
        return maFechaModificacion;
    }

    public Macroproceso maFechaModificacion(LocalDate maFechaModificacion) {
        this.maFechaModificacion = maFechaModificacion;
        return this;
    }

    public void setMaFechaModificacion(LocalDate maFechaModificacion) {
        this.maFechaModificacion = maFechaModificacion;
    }

    public Integer getVerificadorlIdVerificador() {
        return verificadorlIdVerificador;
    }

    public Macroproceso verificadorlIdVerificador(Integer verificadorlIdVerificador) {
        this.verificadorlIdVerificador = verificadorlIdVerificador;
        return this;
    }

    public void setVerificadorlIdVerificador(Integer verificadorlIdVerificador) {
        this.verificadorlIdVerificador = verificadorlIdVerificador;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Macroproceso)) {
            return false;
        }
        return id != null && id.equals(((Macroproceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Macroproceso{" +
            "id=" + getId() +
            ", idMacroproceso=" + getIdMacroproceso() +
            ", maFechaCreacion='" + getMaFechaCreacion() + "'" +
            ", maFechaModificacion='" + getMaFechaModificacion() + "'" +
            ", verificadorlIdVerificador=" + getVerificadorlIdVerificador() +
            "}";
    }
}
