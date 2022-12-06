package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "observacion_prestamo")
public class ObservacionPrestamo implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_observacion;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prestamo")
	@JsonIgnore
	private Prestamo prestamo;
	
	private Integer id_inventario;
	private String nombre;
	private Integer cantidad;
	private String observacion;
	
	public ObservacionPrestamo() {}
	
	private static final long serialVersionUID = 1L;
}