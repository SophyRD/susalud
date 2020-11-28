package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Verificador.
 */
@Entity
@Table(name = "verificador")
public class Verificador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @NotNull
    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne
    @JsonIgnoreProperties(value = "verificadors", allowSetters = true)
    private Macroproceso macroproceso;

    @ManyToOne
    @JsonIgnoreProperties(value = "verificadors", allowSetters = true)
    private Proceso proceso;

    @ManyToOne
    @JsonIgnoreProperties(value = "verificadors", allowSetters = true)
    private Subproceso subproceso;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Verificador codigo(Integer codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Verificador descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Verificador estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Macroproceso getMacroproceso() {
        return macroproceso;
    }

    public Verificador macroproceso(Macroproceso macroproceso) {
        this.macroproceso = macroproceso;
        return this;
    }

    public void setMacroproceso(Macroproceso macroproceso) {
        this.macroproceso = macroproceso;
    }

    public Proceso getProceso() {
        return proceso;
    }

    public Verificador proceso(Proceso proceso) {
        this.proceso = proceso;
        return this;
    }

    public void setProceso(Proceso proceso) {
        this.proceso = proceso;
    }

    public Subproceso getSubproceso() {
        return subproceso;
    }

    public Verificador subproceso(Subproceso subproceso) {
        this.subproceso = subproceso;
        return this;
    }

    public void setSubproceso(Subproceso subproceso) {
        this.subproceso = subproceso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Verificador)) {
            return false;
        }
        return id != null && id.equals(((Verificador) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Verificador{" +
            "id=" + getId() +
            ", codigo=" + getCodigo() +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
