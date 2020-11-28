package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Perfil.
 */
@Entity
@Table(name = "perfil")
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_perfil", nullable = false)
    private Integer idPerfil;

    @NotNull
    @Column(name = "pe_descripcion", nullable = false)
    private String peDescripcion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public Perfil idPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
        return this;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPeDescripcion() {
        return peDescripcion;
    }

    public Perfil peDescripcion(String peDescripcion) {
        this.peDescripcion = peDescripcion;
        return this;
    }

    public void setPeDescripcion(String peDescripcion) {
        this.peDescripcion = peDescripcion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Perfil)) {
            return false;
        }
        return id != null && id.equals(((Perfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Perfil{" +
            "id=" + getId() +
            ", idPerfil=" + getIdPerfil() +
            ", peDescripcion='" + getPeDescripcion() + "'" +
            "}";
    }
}
