package com.uniminuto.susalud.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SubProceso.
 */
@Entity
@Table(name = "sub_proceso")
public class SubProceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_subproceso")
    private Integer idSubproceso;

    @Column(name = "sp_descripcion")
    private String spDescripcion;

    @Column(name = "proceso_id_proceso")
    private Integer procesoIdProceso;

    @Column(name = "proceso_macroproceso_id_macroproceso")
    private Integer procesoMacroprocesoIdMacroproceso;

    @Column(name = "proceso_macroproceso_verificador_id_verificador")
    private Integer procesoMacroprocesoVerificadorIdVerificador;

    @ManyToOne
    @JsonIgnoreProperties(value = "idProcesos", allowSetters = true)
    private Proceso proceso;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdSubproceso() {
        return idSubproceso;
    }

    public SubProceso idSubproceso(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
        return this;
    }

    public void setIdSubproceso(Integer idSubproceso) {
        this.idSubproceso = idSubproceso;
    }

    public String getSpDescripcion() {
        return spDescripcion;
    }

    public SubProceso spDescripcion(String spDescripcion) {
        this.spDescripcion = spDescripcion;
        return this;
    }

    public void setSpDescripcion(String spDescripcion) {
        this.spDescripcion = spDescripcion;
    }

    public Integer getProcesoIdProceso() {
        return procesoIdProceso;
    }

    public SubProceso procesoIdProceso(Integer procesoIdProceso) {
        this.procesoIdProceso = procesoIdProceso;
        return this;
    }

    public void setProcesoIdProceso(Integer procesoIdProceso) {
        this.procesoIdProceso = procesoIdProceso;
    }

    public Integer getProcesoMacroprocesoIdMacroproceso() {
        return procesoMacroprocesoIdMacroproceso;
    }

    public SubProceso procesoMacroprocesoIdMacroproceso(Integer procesoMacroprocesoIdMacroproceso) {
        this.procesoMacroprocesoIdMacroproceso = procesoMacroprocesoIdMacroproceso;
        return this;
    }

    public void setProcesoMacroprocesoIdMacroproceso(Integer procesoMacroprocesoIdMacroproceso) {
        this.procesoMacroprocesoIdMacroproceso = procesoMacroprocesoIdMacroproceso;
    }

    public Integer getProcesoMacroprocesoVerificadorIdVerificador() {
        return procesoMacroprocesoVerificadorIdVerificador;
    }

    public SubProceso procesoMacroprocesoVerificadorIdVerificador(Integer procesoMacroprocesoVerificadorIdVerificador) {
        this.procesoMacroprocesoVerificadorIdVerificador = procesoMacroprocesoVerificadorIdVerificador;
        return this;
    }

    public void setProcesoMacroprocesoVerificadorIdVerificador(Integer procesoMacroprocesoVerificadorIdVerificador) {
        this.procesoMacroprocesoVerificadorIdVerificador = procesoMacroprocesoVerificadorIdVerificador;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public SubProceso proceso(Proceso proceso) {
        this.proceso = proceso;
        return this;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubProceso)) {
            return false;
        }
        return id != null && id.equals(((SubProceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubProceso{" +
            "id=" + getId() +
            ", idSubproceso=" + getIdSubproceso() +
            ", spDescripcion='" + getSpDescripcion() + "'" +
            ", procesoIdProceso=" + getProcesoIdProceso() +
            ", procesoMacroprocesoIdMacroproceso=" + getProcesoMacroprocesoIdMacroproceso() +
            ", procesoMacroprocesoVerificadorIdVerificador=" + getProcesoMacroprocesoVerificadorIdVerificador() +
            "}";
    }
}
