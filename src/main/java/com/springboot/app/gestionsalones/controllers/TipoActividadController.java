package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.TipoActividad;
import com.springboot.app.gestionsalones.servicesImpl.TipoActividadServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/actividad")
@Log4j2
public class TipoActividadController 
{
	@Autowired
	TipoActividadServiceImpl service;
	
	@GetMapping
	public ResponseEntity<List<TipoActividad>> getAll()
	{
		List<TipoActividad> actividades = service.findAll();
		return new ResponseEntity<List<TipoActividad>> (actividades, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoActividad> get(@PathVariable Integer id)
	{
		TipoActividad actividad = service.findById(id);
		if(actividad == null)
			return ResponseEntity.notFound().build();
		
		return new ResponseEntity<TipoActividad> (actividad, HttpStatus.OK);		
	}
	
	@PostMapping
	public ResponseEntity<TipoActividad> create(@RequestBody TipoActividad actividad)
	{
		try {
			service.save(actividad);
		}catch (Exception e) {
			log.info(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return new ResponseEntity<TipoActividad> (actividad, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TipoActividad> update(@PathVariable Integer id, @RequestBody TipoActividad nuevo)
	{
		TipoActividad actual = new TipoActividad();
		try {
			actual = service.findById(id);
			if(actual == null)
				return ResponseEntity.notFound().build();
			actual.setNombre(nuevo.getNombre());
			actual.setDescripcion(nuevo.getDescripcion());
			
			service.save(actual);
		}catch(Exception e) {
			log.info(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		return new ResponseEntity<TipoActividad> (actual, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Integer id)
	{
		TipoActividad actividad = service.findById(id);
		if(actividad != null)
			service.delete(actividad);
	}	
}