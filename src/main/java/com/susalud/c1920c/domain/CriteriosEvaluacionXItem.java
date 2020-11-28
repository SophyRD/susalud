package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CriteriosEvaluacionXItem.
 */
@Entity
@Table(name = "criterios_evaluacion_x_item")
public class CriteriosEvaluacionXItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_criterios_evaluacion_x_item", nullable = false)
    private Integer idCriteriosEvaluacionXItem;

    @NotNull
    @Column(name = "cei_fecha_creacion", nullable = false)
    private LocalDate ceiFechaCreacion;

    @NotNull
    @Column(name = "cei_fecha_modificacion", nullable = false)
    private LocalDate ceiFechaModificacion;

    @NotNull
    @Column(name = "cei_nombre", nullable = false)
    private String ceiNombre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdCriteriosEvaluacionXItem() {
        return idCriteriosEvaluacionXItem;
    }

    public CriteriosEvaluacionXItem idCriteriosEvaluacionXItem(Integer idCriteriosEvaluacionXItem) {
        this.idCriteriosEvaluacionXItem = idCriteriosEvaluacionXItem;
        return this;
    }

    public void setIdCriteriosEvaluacionXItem(Integer idCriteriosEvaluacionXItem) {
        this.idCriteriosEvaluacionXItem = idCriteriosEvaluacionXItem;
    }

    public LocalDate getCeiFechaCreacion() {
        return ceiFechaCreacion;
    }

    public CriteriosEvaluacionXItem ceiFechaCreacion(LocalDate ceiFechaCreacion) {
        this.ceiFechaCreacion = ceiFechaCreacion;
        return this;
    }

    public void setCeiFechaCreacion(LocalDate ceiFechaCreacion) {
        this.ceiFechaCreacion = ceiFechaCreacion;
    }

    public LocalDate getCeiFechaModificacion() {
        return ceiFechaModificacion;
    }

    public CriteriosEvaluacionXItem ceiFechaModificacion(LocalDate ceiFechaModificacion) {
        this.ceiFechaModificacion = ceiFechaModificacion;
        return this;
    }

    public void setCeiFechaModificacion(LocalDate ceiFechaModificacion) {
        this.ceiFechaModificacion = ceiFechaModificacion;
    }

    public String getCeiNombre() {
        return ceiNombre;
    }

    public CriteriosEvaluacionXItem ceiNombre(String ceiNombre) {
        this.ceiNombre = ceiNombre;
        return this;
    }

    public void setCeiNombre(String ceiNombre) {
        this.ceiNombre = ceiNombre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CriteriosEvaluacionXItem)) {
            return false;
        }
        return id != null && id.equals(((CriteriosEvaluacionXItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CriteriosEvaluacionXItem{" +
            "id=" + getId() +
            ", idCriteriosEvaluacionXItem=" + getIdCriteriosEvaluacionXItem() +
            ", ceiFechaCreacion='" + getCeiFechaCreacion() + "'" +
            ", ceiFechaModificacion='" + getCeiFechaModificacion() + "'" +
            ", ceiNombre='" + getCeiNombre() + "'" +
            "}";
    }
}
