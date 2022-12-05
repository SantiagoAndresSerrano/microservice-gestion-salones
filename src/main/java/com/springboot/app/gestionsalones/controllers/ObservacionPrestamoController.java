package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;
import com.springboot.app.gestionsalones.servicesImpl.ObservacionPrestamoServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/prestamo/observacion")

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
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(nuevo);
	}
}