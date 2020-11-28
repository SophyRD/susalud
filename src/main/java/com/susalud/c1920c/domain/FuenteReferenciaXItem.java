package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A FuenteReferenciaXItem.
 */
@Entity
@Table(name = "fuente_referencia_x_item")
public class FuenteReferenciaXItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_fuente_referencia_x_item", nullable = false)
    private Integer idFuenteReferenciaXItem;

    @NotNull
    @Column(name = "fri_fecha_creacion", nullable = false)
    private LocalDate friFechaCreacion;

    @NotNull
    @Column(name = "fri_fecha_modificacion", nullable = false)
    private LocalDate friFechaModificacion;

    @NotNull
    @Column(name = "fri_nombre", nullable = false)
    private String friNombre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdFuenteReferenciaXItem() {
        return idFuenteReferenciaXItem;
    }

    public FuenteReferenciaXItem idFuenteReferenciaXItem(Integer idFuenteReferenciaXItem) {
        this.idFuenteReferenciaXItem = idFuenteReferenciaXItem;
        return this;
    }

    public void setIdFuenteReferenciaXItem(Integer idFuenteReferenciaXItem) {
        this.idFuenteReferenciaXItem = idFuenteReferenciaXItem;
    }

    public LocalDate getFriFechaCreacion() {
        return friFechaCreacion;
    }

    public FuenteReferenciaXItem friFechaCreacion(LocalDate friFechaCreacion) {
        this.friFechaCreacion = friFechaCreacion;
        return this;
    }

    public void setFriFechaCreacion(LocalDate friFechaCreacion) {
        this.friFechaCreacion = friFechaCreacion;
    }

    public LocalDate getFriFechaModificacion() {
        return friFechaModificacion;
    }

    public FuenteReferenciaXItem friFechaModificacion(LocalDate friFechaModificacion) {
        this.friFechaModificacion = friFechaModificacion;
        return this;
    }

    public void setFriFechaModificacion(LocalDate friFechaModificacion) {
        this.friFechaModificacion = friFechaModificacion;
    }

    public String getFriNombre() {
        return friNombre;
    }

    public FuenteReferenciaXItem friNombre(String friNombre) {
        this.friNombre = friNombre;
        return this;
    }

    public void setFriNombre(String friNombre) {
        this.friNombre = friNombre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FuenteReferenciaXItem)) {
            return false;
        }
        return id != null && id.equals(((FuenteReferenciaXItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FuenteReferenciaXItem{" +
            "id=" + getId() +
            ", idFuenteReferenciaXItem=" + getIdFuenteReferenciaXItem() +
            ", friFechaCreacion='" + getFriFechaCreacion() + "'" +
            ", friFechaModificacion='" + getFriFechaModificacion() + "'" +
            ", friNombre='" + getFriNombre() + "'" +
            "}";
    }
}
