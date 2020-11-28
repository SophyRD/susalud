package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Periodo.
 */
@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_periodo", nullable = false)
    private Integer idPeriodo;

    @NotNull
    @Column(name = "p_periodo", nullable = false)
    private String pPeriodo;

    @NotNull
    @Column(name = "mes_id_mes", nullable = false)
    private Integer mesIdMes;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public Periodo idPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
        return this;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getpPeriodo() {
        return pPeriodo;
    }

    public Periodo pPeriodo(String pPeriodo) {
        this.pPeriodo = pPeriodo;
        return this;
    }

    public void setpPeriodo(String pPeriodo) {
        this.pPeriodo = pPeriodo;
    }

    public Integer getMesIdMes() {
        return mesIdMes;
    }

    public Periodo mesIdMes(Integer mesIdMes) {
        this.mesIdMes = mesIdMes;
        return this;
    }

    public void setMesIdMes(Integer mesIdMes) {
        this.mesIdMes = mesIdMes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Periodo)) {
            return false;
        }
        return id != null && id.equals(((Periodo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Periodo{" +
            "id=" + getId() +
            ", idPeriodo=" + getIdPeriodo() +
            ", pPeriodo='" + getpPeriodo() + "'" +
            ", mesIdMes=" + getMesIdMes() +
            "}";
    }
}
