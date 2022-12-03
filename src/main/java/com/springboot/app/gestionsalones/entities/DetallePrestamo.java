package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "detalle_prestamo")
public class DetallePrestamo implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_detalle;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prestamo")
    private Prestamo prestamo;
	
	@ManyToOne
	@JoinColumn(name = "id_actividad")
	private TipoActividad actividad;
	
	private Integer id_persona;
	private String id_salon;
	private Byte estado;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String observacion;
	
	@PrePersist
    public void prePersist() {
		this.estado = 0;
    }
	
	public DetallePrestamo(Prestamo prestamo, TipoActividad actividad, Integer id_persona, String id_salon, Date fecha_inicio, Date fecha_fin) {
		this.prestamo = prestamo;
		this.actividad = actividad;
		this.id_persona = id_persona;
		this.id_salon = id_salon;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.observacion = "";
	}
	
	private static final long serialVersionUID = 1L;
}