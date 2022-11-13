package com.springboot.app.gestionsalones.servicesImpl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;
import com.springboot.app.gestionsalones.repositories.ObservacionPrestamoRepository;
import com.springboot.app.gestionsalones.services.ObservacionPrestamoService;

@Service
public class ObservacionPrestamoServiceImpl implements ObservacionPrestamoService
{
	@Autowired
	ObservacionPrestamoRepository data;
	
	@Override
	@Transactional (readOnly = true)
	public List<ObservacionPrestamo> findAll(){
		return (List<ObservacionPrestamo>) data.findAll();
	}
	
	@Override
	@Transactional (readOnly = true)
	public ObservacionPrestamo findById (Integer id) {
		return data.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public ObservacionPrestamo save (ObservacionPrestamo nuevo) {
		return data.save(nuevo);
	}
	
	@Override
	@Transactional
	public void update (ObservacionPrestamo nuevo) {
		ObservacionPrestamo actual = data.findById(nuevo.getId_observacion()).orElse(null);
		if(actual != null) {
			actual.setPrestamo(nuevo.getPrestamo());
			actual.setId_inventario(nuevo.getId_inventario());
			actual.setCantidad(nuevo.getCantidad());
			actual.setObservacion(nuevo.getObservacion());
			
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
	public void delete(ObservacionPrestamo actual) {
		data.delete(actual);		
	}
}