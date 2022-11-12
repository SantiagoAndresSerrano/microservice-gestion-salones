package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "observacion_prestamo")
public class ObservacionPrestamo implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_observacion;
	private Integer cantidad;
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_detalle")
	private Prestamo prestamo;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_inventario")
	private TipoInventario tipoInventario;
	
	public ObservacionPrestamo() {}
	
	public ObservacionPrestamo(TipoInventario tipoInventario, Integer cantidad, String observacion, Prestamo prestamo) {
		this.tipoInventario = tipoInventario;
		this.cantidad = cantidad;
		this.observacion = observacion;
		this.prestamo = prestamo;
	}

	private static final long serialVersionUID = 1L;
}