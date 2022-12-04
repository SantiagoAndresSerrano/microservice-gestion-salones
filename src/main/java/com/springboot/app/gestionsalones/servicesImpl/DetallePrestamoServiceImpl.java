package com.springboot.app.gestionsalones.servicesImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;
import com.springboot.app.gestionsalones.repositories.DetallePrestamoRepository;
import com.springboot.app.gestionsalones.services.DetallePrestamoService;

@Service
public class DetallePrestamoServiceImpl implements DetallePrestamoService
{
	@Autowired
	DetallePrestamoRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<DetallePrestamo> findAll(){
		return (List<DetallePrestamo>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public DetallePrestamo findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public DetallePrestamo save (DetallePrestamo nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (DetallePrestamo nuevo) {
		DetallePrestamo actual = data.findById(nuevo.getId_detalle()).orElse(null);
		if(actual != null) {
			
		}
	}
	
	@Override
	@Transactional
	public void deleteById (Integer id) {
		data.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(DetallePrestamo actual) {
		data.delete(actual);		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DetallePrestamo> getAgendados() {
		return data.getAgendados();		
	}
}