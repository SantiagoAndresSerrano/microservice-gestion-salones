package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;

public interface NovedadPrestamoService 
{	
	public List<NovedadPrestamo> findAll();	
	public NovedadPrestamo findById (Integer id);	
	public NovedadPrestamo save (NovedadPrestamo nuevo);	
	public void update (NovedadPrestamo nuevo);	
	public void deleteById (Integer id);
	public void delete (NovedadPrestamo actual);
}