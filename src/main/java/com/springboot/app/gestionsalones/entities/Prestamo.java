package com.springboot.app.gestionsalones.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_prestamo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detalle")
    private DetallePrestamo detalle;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_novedad")
	private NovedadPrestamo novedad;

	@OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
	List<ObservacionPrestamo> observaciones;
	
	@PrePersist
    public void prePersist() {
		this.observaciones = new ArrayList<>();
    }
	
	public Prestamo () {}
	
	public Prestamo (DetallePrestamo detalle) {
		this.detalle = detalle;
	}
	
	private static final long serialVersionUID = 1L;
}