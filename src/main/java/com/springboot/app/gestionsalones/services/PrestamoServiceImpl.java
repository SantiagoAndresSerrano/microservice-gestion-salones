package com.springboot.app.gestionsalones.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.Prestamo;
import com.springboot.app.gestionsalones.repositories.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements PrestamoService
{
	@Autowired
	PrestamoRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<Prestamo> findAll(){
		return (List<Prestamo>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public Prestamo findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Prestamo save (Prestamo nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (Prestamo nuevo) {
		Prestamo actual = data.findById(nuevo.getId_detalle()).orElse(null);
		if(actual != null) {
			actual.setId_actividad(nuevo.getId_actividad());
			actual.setId_persona(nuevo.getId_persona());
			actual.setId_salon(nuevo.getId_salon());
			actual.setEstado(nuevo.getEstado());
			actual.setObservacion(nuevo.getObservacion());
			
			data.save(actual);
		}
	}
	
	@Override
	@Transactional
	public void deleteById (Integer id) {
		data.deleteById(id);
	}
}