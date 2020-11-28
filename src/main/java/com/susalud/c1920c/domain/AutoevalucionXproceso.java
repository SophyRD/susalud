package com.susalud.c1920c.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A AutoevalucionXproceso.
 */
@Entity
@Table(name = "autoevalucion_xproceso")
public class AutoevalucionXproceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "id_autoevalucion_xproceso", nullable = false)
    private Integer idAutoevalucionXproceso;

    @NotNull
    @Column(name = "ap_fecha_creacion", nullable = false)
    private LocalDate apFechaCreacion;

    @NotNull
    @Column(name = "sp_fecha_modificacion", nullable = false)
    private LocalDate spFechaModificacion;

    @NotNull
    @Column(name = "ap_comentario", nullable = false)
    private String apComentario;

    @NotNull
    @Column(name = "ap_puntuacion", nullable = false)
    private Integer apPuntuacion;

    @NotNull
    @Column(name = "id_autoevalucion", nullable = false)
    private Integer idAutoevalucion;

    @NotNull
    @Column(name = "id_mes", nullable = false)
    private Integer idMes;

    @NotNull
    @Column(name = "id_usuarios_xevaluacion", nullable = false)
    private Integer idUsuariosXevaluacion;

    @NotNull
    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    @NotNull
    @Column(name = "id_verificador", nullable = false)
    private Integer idVerificador;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdAutoevalucionXproceso() {
        return idAutoevalucionXproceso;
    }

    public AutoevalucionXproceso idAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
        return this;
    }

    public void setIdAutoevalucionXproceso(Integer idAutoevalucionXproceso) {
        this.idAutoevalucionXproceso = idAutoevalucionXproceso;
    }

    public LocalDate getApFechaCreacion() {
        return apFechaCreacion;
    }

    public AutoevalucionXproceso apFechaCreacion(LocalDate apFechaCreacion) {
        this.apFechaCreacion = apFechaCreacion;
        return this;
    }

    public void setApFechaCreacion(LocalDate apFechaCreacion) {
        this.apFechaCreacion = apFechaCreacion;
    }

    public LocalDate getSpFechaModificacion() {
        return spFechaModificacion;
    }

    public AutoevalucionXproceso spFechaModificacion(LocalDate spFechaModificacion) {
        this.spFechaModificacion = spFechaModificacion;
        return this;
    }

    public void setSpFechaModificacion(LocalDate spFechaModificacion) {
        this.spFechaModificacion = spFechaModificacion;
    }

    public String getApComentario() {
        return apComentario;
    }

    public AutoevalucionXproceso apComentario(String apComentario) {
        this.apComentario = apComentario;
        return this;
    }

    public void setApComentario(String apComentario) {
        this.apComentario = apComentario;
    }

    public Integer getApPuntuacion() {
        return apPuntuacion;
    }

    public AutoevalucionXproceso apPuntuacion(Integer apPuntuacion) {
        this.apPuntuacion = apPuntuacion;
        return this;
    }

    public void setApPuntuacion(Integer apPuntuacion) {
        this.apPuntuacion = apPuntuacion;
    }

    public Integer getIdAutoevalucion() {
        return idAutoevalucion;
    }

    public AutoevalucionXproceso idAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
        return this;
    }

    public void setIdAutoevalucion(Integer idAutoevalucion) {
        this.idAutoevalucion = idAutoevalucion;
    }

    public Integer getIdMes() {
        return idMes;
    }

    public AutoevalucionXproceso idMes(Integer idMes) {
        this.idMes = idMes;
        return this;
    }

    public void setIdMes(Integer idMes) {
        this.idMes = idMes;
    }

    public Integer getIdUsuariosXevaluacion() {
        return idUsuariosXevaluacion;
    }

    public AutoevalucionXproceso idUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
        return this;
    }

    public void setIdUsuariosXevaluacion(Integer idUsuariosXevaluacion) {
        this.idUsuariosXevaluacion = idUsuariosXevaluacion;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public AutoevalucionXproceso idEstado(Integer idEstado) {
        this.idEstado = idEstado;
        return this;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdVerificador() {
        return idVerificador;
    }

    public AutoevalucionXproceso idVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
        return this;
    }

    public void setIdVerificador(Integer idVerificador) {
        this.idVerificador = idVerificador;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AutoevalucionXproceso)) {
            return false;
        }
        return id != null && id.equals(((AutoevalucionXproceso) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AutoevalucionXproceso{" +
            "id=" + getId() +
            ", idAutoevalucionXproceso=" + getIdAutoevalucionXproceso() +
            ", apFechaCreacion='" + getApFechaCreacion() + "'" +
            ", spFechaModificacion='" + getSpFechaModificacion() + "'" +
            ", apComentario='" + getApComentario() + "'" +
            ", apPuntuacion=" + getApPuntuacion() +
            ", idAutoevalucion=" + getIdAutoevalucion() +
            ", idMes=" + getIdMes() +
            ", idUsuariosXevaluacion=" + getIdUsuariosXevaluacion() +
            ", idEstado=" + getIdEstado() +
            ", idVerificador=" + getIdVerificador() +
            "}";
    }
}
