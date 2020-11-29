package com.uniminuto.susalud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Proceso.
 */
@Entity
@Table(name = "proceso")
public class Proceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_proceso")
    private Integer idProceso;

    @Column(name = "pr_descripcion")
    private String prDescripcion;

    @Column(name = "macroproceso_id_macroproseso")
    private Integer macroprocesoIdMacroproseso;

    @Column(name = "macroproceso_verificador_id_verificador")
    private Integer macroprocesoVerificadorIdVerificador;

    @OneToMany(mappedBy = "proceso")
    private Set<SubProceso> idProcesos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "idMacroprocesos", allowSetters = true)
    private MacroProceso macroProceso;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public Proceso idProceso(Integer idProceso) {
        this.idProceso = idProceso;
        return this;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getPrDescripcion() {
        return prDescripcion;
    }

    public Proceso prDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
        return this;
    }

    public void setPrDescripcion(String prDescripcion) {
        this.prDescripcion = prDescripcion;
    }

    public Integer getMacroprocesoIdMacroproseso() {
        return macroprocesoIdMacroproseso;
    }

    public Proceso macroprocesoIdMacroproseso(Integer macroprocesoIdMacroproseso) {
        this.macroprocesoIdMacroproseso = macroprocesoIdMacroproseso;
        return this;
    }

    public void setMacroprocesoIdMacroproseso(Integer macroprocesoIdMacroproseso) {
        this.macroprocesoIdMacroproseso = macroprocesoIdMacroproseso;
    }

    public Integer getMacroprocesoVerificadorIdVerificador() {
        return macroprocesoVerificadorIdVerificador;
    }

    public Proceso macroprocesoVerificadorIdVerificador(Integer macroprocesoVerificadorIdVerificador) {
        this.macroprocesoVerificadorIdVerificador = macroprocesoVerificadorIdVerificador;
        return this;
    }

    public void setMacroprocesoVerificadorIdVerificador(Integer macroprocesoVerificadorIdVerificador) {
        this.macroprocesoVerificadorIdVerificador = macroprocesoVerificadorIdVerificador;
    }

    public Set<SubProceso> getIdProcesos() {
        return idProcesos;
    }

    public Proceso idProcesos(Set<SubProceso> subProcesos) {
        this.idProcesos = subProcesos;
        return this;
    }

    public Proceso addIdProceso(SubProceso subProceso) {
        this.idProcesos.add(subProceso);
        subProceso.setProceso(this);
        return this;
    }

    public Proceso removeIdProceso(SubProceso subProceso) {
        this.idProcesos.remove(subProceso);
        subProceso.setProceso(null);
        return this;
    }

    public void setIdProcesos(Set<SubProceso> subProcesos) {
        this.idProcesos = subProcesos;
    }

    public MacroProceso getMacroProceso() {
        return macroProceso;
    }

    public Proceso macroProceso(MacroProceso macroProceso) {
        this.macroProceso = macroProceso;
        return this;
    }

    public void setMacroProceso(MacroProceso macroProceso) {
        this.macroProceso = macroProceso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Proceso)) {
            return false;
        }
        return id != null && id.equals(((Proceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Proceso{" +
            "id=" + getId() +
            ", idProceso=" + getIdProceso() +
            ", prDescripcion='" + getPrDescripcion() + "'" +
            ", macroprocesoIdMacroproseso=" + getMacroprocesoIdMacroproseso() +
            ", macroprocesoVerificadorIdVerificador=" + getMacroprocesoVerificadorIdVerificador() +
            "}";
    }
}
