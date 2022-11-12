package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.TipoActividad;

public interface TipoActividadService 
{
	public List<TipoActividad> findAll();	
	public TipoActividad findById (Integer id);	
	public TipoActividad save (TipoActividad nuevo);	
	public void update (TipoActividad nuevo);	
	public void deleteById (Integer id);
	public void delete (TipoActividad actual);
}