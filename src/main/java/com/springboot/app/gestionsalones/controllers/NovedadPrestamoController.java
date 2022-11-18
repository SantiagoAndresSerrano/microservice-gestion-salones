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

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;
import com.springboot.app.gestionsalones.servicesImpl.NovedadPrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.PrestamoServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/novedad")
@Log4j2
public class NovedadPrestamoController 
{
	@Autowired
	NovedadPrestamoServiceImpl service;
	
	@Autowired
	PrestamoServiceImpl prestamo_service;
	
	@GetMapping
	public ResponseEntity<List<NovedadPrestamo>> getAll()
	{
		List<NovedadPrestamo> novedades = service.findAll();
		return new ResponseEntity<List<NovedadPrestamo>> (novedades, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<NovedadPrestamo> get(@PathVariable Integer id)
	{
		NovedadPrestamo novedad = service.findById(id);
		if(novedad == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(novedad);		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody NovedadPrestamo nuevo)
	{ 
		try {
			service.save(nuevo);
			nuevo.getPrestamo().setEstado((byte) 2);
			//nuevo.getPrestamo().setNovedad(nuevo);
			prestamo_service.update(nuevo.getPrestamo());
		}catch (Exception e) {
			log.info(e.getMessage());			
		}
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<NovedadPrestamo> delete(@PathVariable("id") Integer id)
	{
		NovedadPrestamo novedad = service.findById(id);
		if(novedad == null)
			return ResponseEntity.notFound().build();
		
		service.delete(novedad);
		return ResponseEntity.ok(novedad);
	}	
}