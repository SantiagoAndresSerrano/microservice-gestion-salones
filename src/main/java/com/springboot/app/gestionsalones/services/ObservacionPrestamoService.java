package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;

public interface ObservacionPrestamoService 
{	
	public List<ObservacionPrestamo> findAll();	
	public ObservacionPrestamo findById (Integer id);	
	public ObservacionPrestamo save (ObservacionPrestamo nuevo);
	public void deleteById (Integer id);
	public void delete (ObservacionPrestamo actual);
}