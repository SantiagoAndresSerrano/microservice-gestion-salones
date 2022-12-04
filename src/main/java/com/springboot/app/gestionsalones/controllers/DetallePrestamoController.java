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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;
import com.springboot.app.gestionsalones.entities.Prestamo;
import com.springboot.app.gestionsalones.servicesImpl.DetallePrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.PrestamoServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/prestamo/detalle")
public class DetallePrestamoController 
{
	@Autowired
	PrestamoServiceImpl prestamo_service;
	@Autowired
	DetallePrestamoServiceImpl detalle_service;
	
	@GetMapping
	public ResponseEntity<List<DetallePrestamo>> getAll()
	{
		List<DetallePrestamo> detalles = detalle_service.findAll();
		return new ResponseEntity<List<DetallePrestamo>> (detalles, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DetallePrestamo> get(@PathVariable Integer id)
	{
		DetallePrestamo detalle = detalle_service.findById(id);
		if(detalle == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(detalle);		
	}
	
	@PostMapping
	public ResponseEntity<DetallePrestamo> create(@RequestBody DetallePrestamo nuevo)
	{
		try {
			detalle_service.save(nuevo);
			prestamo_service.save(new Prestamo(nuevo));		
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(nuevo);
	}
}