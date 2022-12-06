package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;
import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;
import com.springboot.app.gestionsalones.entities.Prestamo;
import com.springboot.app.gestionsalones.servicesImpl.PrestamoServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/prestamo")

public class PrestamoController 
{
	@Autowired
	PrestamoServiceImpl prestamo_service;
	
	
	@GetMapping
	public ResponseEntity<List<Prestamo>> getAll()
	{
		List<Prestamo> prestamos = prestamo_service.findAll();
		return new ResponseEntity<List<Prestamo>> (prestamos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Prestamo> get(@PathVariable Integer id)
	{
		Prestamo prestamo = prestamo_service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		return new ResponseEntity<Prestamo>(prestamo, HttpStatus.OK);		
	}
		
	@PutMapping(value = "/{id}/cancelar")
	public ResponseEntity<Prestamo> cancelar(@PathVariable Integer id)
	{
		Prestamo prestamo = prestamo_service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		prestamo.getDetalle().setEstado((byte) 2);				
		return ResponseEntity.ok(prestamo);
	}
	
	@PostMapping(value = "/{id}")
	public ResponseEntity<Prestamo> createNovedad(@PathVariable Integer id, @RequestBody NovedadPrestamo novedad)
	{
		Prestamo prestamo = prestamo_service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		prestamo.getDetalle().setEstado((byte) 3);
		prestamo.setNovedad(novedad); prestamo_service.save(prestamo);
				
		return ResponseEntity.ok(prestamo);
	}
	
	@GetMapping(value = "/{id}/observaciones")
	public ResponseEntity<List<ObservacionPrestamo>> getObservaciones(@PathVariable Integer id) 
	{
		Prestamo prestamo = prestamo_service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(prestamo.getObservaciones());
	}
	
	@PostMapping(value = "/{id}/observaciones")
	public ResponseEntity<Prestamo> setObservaciones(@PathVariable Integer id, @RequestBody List<ObservacionPrestamo> observaciones) 
	{
		Prestamo prestamo = prestamo_service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		for(ObservacionPrestamo i: observaciones)
			i.setPrestamo(prestamo);
		
		prestamo.setObservaciones(observaciones);
		prestamo.getDetalle().setEstado((byte) 1);
		prestamo_service.save(prestamo);
		
		return ResponseEntity.ok(prestamo);
	}
}