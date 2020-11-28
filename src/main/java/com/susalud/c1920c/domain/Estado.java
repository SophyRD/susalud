package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Estado.
 */
@Entity
@Table(name = "estado")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    @NotNull
    @Column(name = "e_estado", nullable = false)
    private String eEstado;

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

    public Integer getIdEstado() {
        return idEstado;
    }

    public Estado idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String geteEstado() {
        return eEstado;
    }

    public Estado eEstado(String eEstado) {
        this.eEstado = eEstado;
        return this;
    }

    public void seteEstado(String eEstado) {
        this.eEstado = eEstado;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public Estado idVerificador(Integer idVerificador) {
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
        if (!(o instanceof Estado)) {
            return false;
        }
        return id != null && id.equals(((Estado) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Estado{" +
            "id=" + getId() +
            ", idEstado=" + getIdEstado() +
            ", eEstado='" + geteEstado() + "'" +
            ", idVerificador=" + getIdVerificador() +
            "}";
    }
}
