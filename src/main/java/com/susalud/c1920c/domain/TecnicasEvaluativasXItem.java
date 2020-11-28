package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A TecnicasEvaluativasXItem.
 */
@Entity
@Table(name = "tecnicas_evaluativas_x_item")
public class TecnicasEvaluativasXItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_tecnicas_evaluativas_x_item", nullable = false)
    private Integer idTecnicasEvaluativasXItem;

    @NotNull
    @Column(name = "ti_fecha_creacion", nullable = false)
    private LocalDate tiFechaCreacion;

    @NotNull
    @Column(name = "ti_fecha_modificacion", nullable = false)
    private LocalDate tiFechaModificacion;

    @NotNull
    @Column(name = "ti_nombre", nullable = false)
    private String tiNombre;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdTecnicasEvaluativasXItem() {
        return idTecnicasEvaluativasXItem;
    }

    public TecnicasEvaluativasXItem idTecnicasEvaluativasXItem(Integer idTecnicasEvaluativasXItem) {
        this.idTecnicasEvaluativasXItem = idTecnicasEvaluativasXItem;
        return this;
    }

    public void setIdTecnicasEvaluativasXItem(Integer idTecnicasEvaluativasXItem) {
        this.idTecnicasEvaluativasXItem = idTecnicasEvaluativasXItem;
    }

    public LocalDate getTiFechaCreacion() {
        return tiFechaCreacion;
    }

    public TecnicasEvaluativasXItem tiFechaCreacion(LocalDate tiFechaCreacion) {
        this.tiFechaCreacion = tiFechaCreacion;
        return this;
    }

    public void setTiFechaCreacion(LocalDate tiFechaCreacion) {
        this.tiFechaCreacion = tiFechaCreacion;
    }

    public LocalDate getTiFechaModificacion() {
        return tiFechaModificacion;
    }

    public TecnicasEvaluativasXItem tiFechaModificacion(LocalDate tiFechaModificacion) {
        this.tiFechaModificacion = tiFechaModificacion;
        return this;
    }

    public void setTiFechaModificacion(LocalDate tiFechaModificacion) {
        this.tiFechaModificacion = tiFechaModificacion;
    }

    public String getTiNombre() {
        return tiNombre;
    }

    public TecnicasEvaluativasXItem tiNombre(String tiNombre) {
        this.tiNombre = tiNombre;
        return this;
    }

    public void setTiNombre(String tiNombre) {
        this.tiNombre = tiNombre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TecnicasEvaluativasXItem)) {
            return false;
        }
        return id != null && id.equals(((TecnicasEvaluativasXItem) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TecnicasEvaluativasXItem{" +
            "id=" + getId() +
            ", idTecnicasEvaluativasXItem=" + getIdTecnicasEvaluativasXItem() +
            ", tiFechaCreacion='" + getTiFechaCreacion() + "'" +
            ", tiFechaModificacion='" + getTiFechaModificacion() + "'" +
            ", tiNombre='" + getTiNombre() + "'" +
            "}";
    }
}
