package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "detalle_prestamo")
public class Prestamo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_detalle;
	private Integer id_actividad;
	private Integer id_persona;
	private Integer id_salon;
	private Byte estado;
	private Date fecha_inicio;
	private Date fecha_fin;
	private String observacion;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prestamo", cascade = CascadeType.ALL)
	List<ObservacionPrestamo> observaciones;
	
	@PrePersist
    public void prePersist() {
		this.estado = 0;
		this.observaciones = new ArrayList<>();
    }
	
	public Prestamo() {}
	
	public Prestamo(Integer id_actividad, Integer id_persona, Integer id_salon, String observacion, Date fecha_inicio, Date fecha_fin) {
		this.id_actividad = id_actividad;
		this.id_persona = id_persona;
		this.id_salon = id_salon;
		this.observacion = observacion;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
	}
	
	private static final long serialVersionUID = 1L;
}