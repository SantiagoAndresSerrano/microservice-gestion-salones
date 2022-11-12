package com.springboot.app.gestionsalones.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.TipoInventario;
import com.springboot.app.gestionsalones.repositories.TipoInventarioRepository;

@Service
public class TipoInventarioServiceImpl implements TipoInventarioService
{
	@Autowired
	TipoInventarioRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<TipoInventario> findAll(){
		return (List<TipoInventario>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public TipoInventario findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public TipoInventario save (TipoInventario nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (TipoInventario nuevo) {
		TipoInventario actual = data.findById(nuevo.getId_inventario()).orElse(null);
		if(actual != null) {
			actual.setNombre(nuevo.getNombre());
			
			data.save(actual);
		}
	}
	
	@Override
	@Transactional
	public void deleteById (Integer id) {
		data.deleteById(id);
	}
}