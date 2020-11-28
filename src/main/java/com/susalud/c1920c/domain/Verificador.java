package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Verificador.
 */
@Entity
@Table(name = "verificador")
public class Verificador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_verificador", nullable = false)
    private Integer idVerificador;

    @NotNull
    @Column(name = "v_1_fecha_creacion", nullable = false)
    private LocalDate v1FechaCreacion;

    @NotNull
    @Column(name = "v_1_fecha_modificacion", nullable = false)
    private LocalDate v1FechaModificacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public Verificador idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }

    public LocalDate getv1FechaCreacion() {
        return v1FechaCreacion;
    }

    public Verificador v1FechaCreacion(LocalDate v1FechaCreacion) {
        this.v1FechaCreacion = v1FechaCreacion;
        return this;
    }

    public void setv1FechaCreacion(LocalDate v1FechaCreacion) {
        this.v1FechaCreacion = v1FechaCreacion;
    }

    public LocalDate getv1FechaModificacion() {
        return v1FechaModificacion;
    }

    public Verificador v1FechaModificacion(LocalDate v1FechaModificacion) {
        this.v1FechaModificacion = v1FechaModificacion;
        return this;
    }

    public void setv1FechaModificacion(LocalDate v1FechaModificacion) {
        this.v1FechaModificacion = v1FechaModificacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Verificador)) {
            return false;
        }
        return id != null && id.equals(((Verificador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Verificador{" +
            "id=" + getId() +
            ", idVerificador=" + getIdVerificador() +
            ", v1FechaCreacion='" + getv1FechaCreacion() + "'" +
            ", v1FechaModificacion='" + getv1FechaModificacion() + "'" +
            "}";
    }
}
