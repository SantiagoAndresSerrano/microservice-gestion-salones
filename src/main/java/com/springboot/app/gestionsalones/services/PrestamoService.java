package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.Prestamo;

public interface PrestamoService 
{	
	public List<Prestamo> findAll();
	public Prestamo findById (Integer id);	
	public Prestamo save (Prestamo nuevo);	
	public void update (Prestamo nuevo);	
	public void deleteById (Integer id);
	public void delete (Prestamo actual);
}