package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Maestraverificador.
 */
@Entity
@Table(name = "maestraverificador")
public class Maestraverificador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_maestraverificador", nullable = false)
    private Integer idMaestraverificador;

    @NotNull
    @Column(name = "mv_fecha", nullable = false)
    private LocalDate mvFecha;

    @NotNull
    @Column(name = "mv_fecha_modificacion", nullable = false)
    private LocalDate mvFechaModificacion;

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

    public Integer getIdMaestraverificador() {
        return idMaestraverificador;
    }

    public Maestraverificador idMaestraverificador(Integer idMaestraverificador) {
        this.idMaestraverificador = idMaestraverificador;
        return this;
    }

    public void setIdMaestraverificador(Integer idMaestraverificador) {
        this.idMaestraverificador = idMaestraverificador;
    }

    public LocalDate getMvFecha() {
        return mvFecha;
    }

    public Maestraverificador mvFecha(LocalDate mvFecha) {
        this.mvFecha = mvFecha;
        return this;
    }

    public void setMvFecha(LocalDate mvFecha) {
        this.mvFecha = mvFecha;
    }

    public LocalDate getMvFechaModificacion() {
        return mvFechaModificacion;
    }

    public Maestraverificador mvFechaModificacion(LocalDate mvFechaModificacion) {
        this.mvFechaModificacion = mvFechaModificacion;
        return this;
    }

    public void setMvFechaModificacion(LocalDate mvFechaModificacion) {
        this.mvFechaModificacion = mvFechaModificacion;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public Maestraverificador idVerificador(Integer idVerificador) {
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
        if (!(o instanceof Maestraverificador)) {
            return false;
        }
        return id != null && id.equals(((Maestraverificador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Maestraverificador{" +
            "id=" + getId() +
            ", idMaestraverificador=" + getIdMaestraverificador() +
            ", mvFecha='" + getMvFecha() + "'" +
            ", mvFechaModificacion='" + getMvFechaModificacion() + "'" +
            ", idVerificador=" + getIdVerificador() +
            "}";
    }
}
