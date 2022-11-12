package com.springboot.app.gestionsalones.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.TipoActividad;
import com.springboot.app.gestionsalones.repositories.TipoActividadRepository;

@Service
public class TipoActividadServiceImpl implements TipoActividadService
{
	@Autowired
	TipoActividadRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<TipoActividad> findAll(){
		return (List<TipoActividad>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public TipoActividad findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public TipoActividad save (TipoActividad nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (TipoActividad nuevo) {
		TipoActividad actual = data.findById(nuevo.getId_actividad()).orElse(null);
		if(actual != null) {
			actual.setNombre(nuevo.getNombre());
			actual.setDescripcion(nuevo.getDescripcion());
			
			data.save(actual);
		}
	}
	
	@Override
	@Transactional
	public void deleteById (Integer id) {
		data.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(TipoActividad actual) {
		data.delete(actual);		
	}
}