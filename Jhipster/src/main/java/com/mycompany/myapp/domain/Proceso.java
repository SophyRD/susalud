package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "proceso")
    private Set<Subproceso> subprocesos = new HashSet<>();

    @OneToMany(mappedBy = "proceso")
    private Set<Verificador> verificadors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "procesos", allowSetters = true)
    private Macroproceso macroproceso;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Proceso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Subproceso> getSubprocesos() {
        return subprocesos;
    }

    public Proceso subprocesos(Set<Subproceso> subprocesos) {
        this.subprocesos = subprocesos;
        return this;
    }

    public Proceso addSubproceso(Subproceso subproceso) {
        this.subprocesos.add(subproceso);
        subproceso.setProceso(this);
        return this;
    }

    public Proceso removeSubproceso(Subproceso subproceso) {
        this.subprocesos.remove(subproceso);
        subproceso.setProceso(null);
        return this;
    }

    public void setSubprocesos(Set<Subproceso> subprocesos) {
        this.subprocesos = subprocesos;
    }

    public Set<Verificador> getVerificadors() {
        return verificadors;
    }

    public Proceso verificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
        return this;
    }

    public Proceso addVerificador(Verificador verificador) {
        this.verificadors.add(verificador);
        verificador.setProceso(this);
        return this;
    }

    public Proceso removeVerificador(Verificador verificador) {
        this.verificadors.remove(verificador);
        verificador.setProceso(null);
        return this;
    }

    public void setVerificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
    }

    public Macroproceso getMacroproceso() {
        return macroproceso;
    }

    public Proceso macroproceso(Macroproceso macroproceso) {
        this.macroproceso = macroproceso;
        return this;
    }

    public void setMacroproceso(Macroproceso macroproceso) {
        this.macroproceso = macroproceso;
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
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
