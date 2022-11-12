package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.TipoActividad;
import com.springboot.app.gestionsalones.services.TipoActividadServiceImpl;

@RestController
@RequestMapping("/actividad")
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
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TipoActividad> get(@PathVariable Integer id)
	{
		TipoActividad actividad = service.findById(id);
		if(actividad == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(actividad);		
	}
	
	@PostMapping
	public ResponseEntity<TipoActividad> create(@RequestBody TipoActividad actividad)
	{
		try {
			service.save(actividad);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ResponseEntity.ok(actividad);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TipoActividad> deleteCliente(@PathVariable("id") Integer id)
	{
		TipoActividad actividad = service.findById(id);
		if(actividad == null) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(actividad);
		return ResponseEntity.ok(actividad);
	}	
}