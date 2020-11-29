package com.uniminuto.susalud.domain;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "macro_proceso")
public class MacroProceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_macroproceso")
    private Integer idMacroproceso;

    @Column(name = "ma_fecha_creacion")
    private String maFechaCreacion;

    @Column(name = "verificador_id_ferificador")
    private Integer verificadorIdFerificador;

    @OneToMany(mappedBy = "macroProceso")
    private Set<Proceso> idMacroprocesos = new HashSet<>();

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

    public MacroProceso idMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
        return this;
    }

    public void setIdMacroproceso(Integer idMacroproceso) {
        this.idMacroproceso = idMacroproceso;
    }

    public String getMaFechaCreacion() {
        return maFechaCreacion;
    }

    public MacroProceso maFechaCreacion(String maFechaCreacion) {
        this.maFechaCreacion = maFechaCreacion;
        return this;
    }

    public void setMaFechaCreacion(String maFechaCreacion) {
        this.maFechaCreacion = maFechaCreacion;
    }

    public Integer getVerificadorIdFerificador() {
        return verificadorIdFerificador;
    }

    public MacroProceso verificadorIdFerificador(Integer verificadorIdFerificador) {
        this.verificadorIdFerificador = verificadorIdFerificador;
        return this;
    }

    public void setVerificadorIdFerificador(Integer verificadorIdFerificador) {
        this.verificadorIdFerificador = verificadorIdFerificador;
    }

    public Set<Proceso> getIdMacroprocesos() {
        return idMacroprocesos;
    }

    public MacroProceso idMacroprocesos(Set<Proceso> procesos) {
        this.idMacroprocesos = procesos;
        return this;
    }

    public MacroProceso addIdMacroproceso(Proceso proceso) {
        this.idMacroprocesos.add(proceso);
        proceso.setMacroProceso(this);
        return this;
    }

    public MacroProceso removeIdMacroproceso(Proceso proceso) {
        this.idMacroprocesos.remove(proceso);
        proceso.setMacroProceso(null);
        return this;
    }

    public void setIdMacroprocesos(Set<Proceso> procesos) {
        this.idMacroprocesos = procesos;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MacroProceso)) {
            return false;
        }
        return id != null && id.equals(((MacroProceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MacroProceso{" +
            "id=" + getId() +
            ", idMacroproceso=" + getIdMacroproceso() +
            ", maFechaCreacion='" + getMaFechaCreacion() + "'" +
            ", verificadorIdFerificador=" + getVerificadorIdFerificador() +
            "}";
    }
}
