package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "detalle_prestamo")
public class Prestamo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_detalle;
	private Integer id_actividad;
	private Integer id_persona;
	private Integer id_salon;
	private Byte estado;
	private Date fecha;
	private String observacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id_detalle", cascade = CascadeType.ALL)
	List<ObservacionPrestamo> observaciones;
	
	public Prestamo() {		
		this.estado = 1;
		this.fecha = new Date();
		this.observacion = "";
	}
	
	public Prestamo(Integer id_actividad, Integer id_persona, Integer id_salon) {
		this.id_actividad = id_actividad;
		this.id_persona = id_persona;
		this.id_salon = id_salon;
		
		this.estado = 1;
		this.fecha = new Date();
		this.observacion = "";
	}
	
	private static final long serialVersionUID = 1L;
}