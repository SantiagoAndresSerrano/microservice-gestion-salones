package com.springboot.app.gestionsalones.services;

import java.util.List;

import com.springboot.app.gestionsalones.entities.TipoInventario;

public interface TipoInventarioService 
{	
	public List<TipoInventario> findAll();	
	public TipoInventario findById (Integer id);	
	public TipoInventario save (TipoInventario nuevo);	
	public void update (TipoInventario nuevo);	
	public void deleteById (Integer id);
	public void delete (TipoInventario actual);
}