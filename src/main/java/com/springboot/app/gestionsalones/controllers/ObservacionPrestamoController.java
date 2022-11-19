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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;
import com.springboot.app.gestionsalones.servicesImpl.ObservacionPrestamoServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/observacion")
@Log4j2
public class ObservacionPrestamoController 
{
	@Autowired
	ObservacionPrestamoServiceImpl service;
	
	@GetMapping
	public ResponseEntity<List<ObservacionPrestamo>> getAll()
	{
		List<ObservacionPrestamo> observaciones = service.findAll();
		return new ResponseEntity<List<ObservacionPrestamo>> (observaciones, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ObservacionPrestamo> get(@PathVariable Integer id)
	{
		ObservacionPrestamo observacion = service.findById(id);
		if(observacion == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(observacion);		
	}
	
	@PostMapping
	public ResponseEntity<ObservacionPrestamo> create(@RequestBody ObservacionPrestamo nuevo)
	{
		try {
			service.save(nuevo);
		}catch (Exception e) {
			log.info(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(nuevo);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ObservacionPrestamo> delete(@PathVariable("id") Integer id)
	{
		ObservacionPrestamo observacion = service.findById(id);
		if(observacion == null) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(observacion);
		return ResponseEntity.ok(observacion);
	}	
}