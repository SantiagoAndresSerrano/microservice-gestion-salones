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

import com.springboot.app.gestionsalones.entities.DetallePrestamo;
import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;
import com.springboot.app.gestionsalones.entities.Prestamo;
import com.springboot.app.gestionsalones.servicesImpl.DetallePrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.PrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.VariedadesServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/prestamo")
@Log4j2
public class PrestamoController 
{
	@Autowired
	PrestamoServiceImpl service;
	@Autowired
	DetallePrestamoServiceImpl detalle_service;
	@Autowired
	VariedadesServiceImpl other;
	
	@GetMapping
	public ResponseEntity<List<Prestamo>> getAll()
	{
		List<Prestamo> prestamos = service.findAll();
		return new ResponseEntity<List<Prestamo>> (prestamos, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Prestamo> get(@PathVariable Integer id)
	{
		Prestamo prestamo = service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(prestamo);		
	}
	
	@PostMapping
	public ResponseEntity<Prestamo> create(@RequestBody Prestamo nuevo)
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
	public ResponseEntity<Prestamo> cancelar(@PathVariable("id") Integer id)
	{
		Prestamo prestamo = service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		DetallePrestamo detalle = prestamo.getDetalle();
		detalle.setEstado((byte) 2);
		detalle_service.save(detalle);
		service.save(prestamo);
		
		return ResponseEntity.ok(prestamo);
	}
	
	@GetMapping(value = "/{id}/observaciones")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ObservacionPrestamo>> getObservaciones(@PathVariable Integer id, List<ObservacionPrestamo> observaciones) 
	{
		Prestamo prestamo = service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(prestamo.getObservaciones());
	}
	
	@PostMapping(value = "/{id}/observaciones")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Prestamo> createObservaciones(@PathVariable Integer id, List<ObservacionPrestamo> observaciones) 
	{
		Prestamo prestamo = service.findById(id);
		if(prestamo == null)
			return ResponseEntity.notFound().build();
		
		prestamo.setObservaciones(observaciones);
		service.save(prestamo);
		
		return ResponseEntity.ok(prestamo);
	}
}