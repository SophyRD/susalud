package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Subproceso.
 */
@Entity
@Table(name = "subproceso")
public class Subproceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "subproceso")
    private Set<Verificador> verificadors = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "subprocesos", allowSetters = true)
    private Proceso proceso;

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

    public Subproceso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Verificador> getVerificadors() {
        return verificadors;
    }

    public Subproceso verificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
        return this;
    }

    public Subproceso addVerificador(Verificador verificador) {
        this.verificadors.add(verificador);
        verificador.setSubproceso(this);
        return this;
    }

    public Subproceso removeVerificador(Verificador verificador) {
        this.verificadors.remove(verificador);
        verificador.setSubproceso(null);
        return this;
    }

    public void setVerificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public Subproceso proceso(Proceso proceso) {
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
        if (!(o instanceof Subproceso)) {
            return false;
        }
        return id != null && id.equals(((Subproceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Subproceso{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
