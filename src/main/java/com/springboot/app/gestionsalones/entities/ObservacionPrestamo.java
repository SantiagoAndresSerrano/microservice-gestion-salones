package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id_observacion;
	private Integer id_inventario;
	private Integer cantidad;
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_detalle")
	private Prestamo id_detalle;
		
	private static final long serialVersionUID = 1L;
}