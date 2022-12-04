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
@Table(name = "novedad_prestamo")
public class NovedadPrestamo implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_novedad;
	
	private String novedad;
	
	public NovedadPrestamo() {}
	
	private static final long serialVersionUID = 1L;
}