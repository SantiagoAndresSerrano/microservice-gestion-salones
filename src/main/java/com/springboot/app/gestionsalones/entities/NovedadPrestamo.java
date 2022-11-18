package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "novedad_prestamo")
public class NovedadPrestamo implements Serializable
{
	@Id
	private Integer id_novedad;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_detalle")
	private Prestamo prestamo;
	
	private String novedad;
	
	public NovedadPrestamo() {}
	
	public NovedadPrestamo(Prestamo prestamo, String novedad) {
		this.prestamo = prestamo;
		this.novedad = novedad;		
	}
	
	private static final long serialVersionUID = 1L;
}