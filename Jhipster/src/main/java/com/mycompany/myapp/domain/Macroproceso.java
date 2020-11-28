package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Macroproceso.
 */
@Entity
@Table(name = "macroproceso")
public class Macroproceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "macroproceso")
    private Set<Proceso> procesos = new HashSet<>();

    @OneToMany(mappedBy = "macroproceso")
    private Set<Verificador> verificadors = new HashSet<>();

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

    public Macroproceso descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Proceso> getProcesos() {
        return procesos;
    }

    public Macroproceso procesos(Set<Proceso> procesos) {
        this.procesos = procesos;
        return this;
    }

    public Macroproceso addProceso(Proceso proceso) {
        this.procesos.add(proceso);
        proceso.setMacroproceso(this);
        return this;
    }

    public Macroproceso removeProceso(Proceso proceso) {
        this.procesos.remove(proceso);
        proceso.setMacroproceso(null);
        return this;
    }

    public void setProcesos(Set<Proceso> procesos) {
        this.procesos = procesos;
    }

    public Set<Verificador> getVerificadors() {
        return verificadors;
    }

    public Macroproceso verificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
        return this;
    }

    public Macroproceso addVerificador(Verificador verificador) {
        this.verificadors.add(verificador);
        verificador.setMacroproceso(this);
        return this;
    }

    public Macroproceso removeVerificador(Verificador verificador) {
        this.verificadors.remove(verificador);
        verificador.setMacroproceso(null);
        return this;
    }

    public void setVerificadors(Set<Verificador> verificadors) {
        this.verificadors = verificadors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Macroproceso)) {
            return false;
        }
        return id != null && id.equals(((Macroproceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Macroproceso{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
