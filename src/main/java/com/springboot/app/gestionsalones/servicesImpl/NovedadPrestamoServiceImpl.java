package com.springboot.app.gestionsalones.servicesImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;
import com.springboot.app.gestionsalones.repositories.NovedadPrestamoRepository;
import com.springboot.app.gestionsalones.services.NovedadPrestamoService;

@Service
public class NovedadPrestamoServiceImpl implements NovedadPrestamoService
{
	@Autowired
	NovedadPrestamoRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<NovedadPrestamo> findAll(){
		return (List<NovedadPrestamo>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public NovedadPrestamo findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public NovedadPrestamo save (NovedadPrestamo nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (NovedadPrestamo nuevo) {
		NovedadPrestamo actual = data.findById(nuevo.getId_novedad()).orElse(null);
		if(actual != null) {
			//actual.setPrestamo(nuevo.getPrestamo());
			actual.setNovedad(nuevo.getNovedad());
			
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
	public void delete(NovedadPrestamo actual) {
		data.delete(actual);		
	}
}