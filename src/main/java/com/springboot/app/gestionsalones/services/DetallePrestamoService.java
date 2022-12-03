package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;

public interface DetallePrestamoService 
{	
	public List<DetallePrestamo> findAll();	
	public DetallePrestamo findById (Integer id);	
	public DetallePrestamo save (DetallePrestamo nuevo);	
	public void update (DetallePrestamo nuevo);	
	public void deleteById (Integer id);
	public void delete (DetallePrestamo actual);
}