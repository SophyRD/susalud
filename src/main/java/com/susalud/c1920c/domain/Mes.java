package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Mes.
 */
@Entity
@Table(name = "mes")
public class Mes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_mes", nullable = false)
    private Integer idMes;

    @NotNull
    @Column(name = "m_mes", nullable = false)
    private Integer mMes;

    @NotNull
    @Column(name = "m_ano", nullable = false)
    private Integer mAno;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public Mes idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getmMes() {
        return mMes;
    }

    public Mes mMes(Integer mMes) {
        this.mMes = mMes;
        return this;
    }

    public void setmMes(Integer mMes) {
        this.mMes = mMes;
    }

    public Integer getmAno() {
        return mAno;
    }

    public Mes mAno(Integer mAno) {
        this.mAno = mAno;
        return this;
    }

    public void setmAno(Integer mAno) {
        this.mAno = mAno;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mes)) {
            return false;
        }
        return id != null && id.equals(((Mes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Mes{" +
            "id=" + getId() +
            ", idMes=" + getIdMes() +
            ", mMes=" + getmMes() +
            ", mAno=" + getmAno() +
            "}";
    }
}
