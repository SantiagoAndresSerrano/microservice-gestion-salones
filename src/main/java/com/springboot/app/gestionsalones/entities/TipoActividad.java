package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tipo_actividad")
public class TipoActividad implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_actividad;
	
	private String nombre;
	private String descripcion;
	
	public TipoActividad() {}
	
	public TipoActividad(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 1L;
}