package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;
import com.springboot.app.gestionsalones.servicesImpl.NovedadPrestamoServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/prestamo/novedad")
public class NovedadPrestamoController 
{
	@Autowired
	NovedadPrestamoServiceImpl service;
	
	@GetMapping
	public ResponseEntity<List<NovedadPrestamo>> getAll()
	{
		List<NovedadPrestamo> novedades = service.findAll();
		return new ResponseEntity<List<NovedadPrestamo>> (novedades, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<NovedadPrestamo> get(@PathVariable Integer id)
	{
		NovedadPrestamo novedad = service.findById(id);
		if(novedad == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(novedad);		
	}
}