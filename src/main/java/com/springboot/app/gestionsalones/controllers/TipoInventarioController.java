package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import com.springboot.app.gestionsalones.services.TipoInventarioService;
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

import com.springboot.app.gestionsalones.entities.TipoInventario;
import com.springboot.app.gestionsalones.servicesImpl.TipoInventarioServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
//@RequestMapping(value= "inventario")
@Log4j2
public class TipoInventarioController 
{
	@Autowired
	TipoInventarioServiceImpl service;

	@Autowired
	TipoInventarioService tipoInventarioService;

	@RequestMapping(value= "api/inventory")
	public ResponseEntity<List<TipoInventario>> getInventory(){
		List<TipoInventario> tipos = service.findAll();
		return new ResponseEntity<List<TipoInventario>> (tipos, HttpStatus.OK);
}

	@GetMapping
	public ResponseEntity<List<TipoInventario>> getAll()
	{
		List<TipoInventario> tipos = service.findAll();
		return new ResponseEntity<List<TipoInventario>> (tipos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<TipoInventario> get(@PathVariable Integer id)
	{
		TipoInventario tipo = service.findById(id);
		if(tipo == null)
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.ok(tipo);		
	}
	
	@PostMapping
	public ResponseEntity<TipoInventario> create(@RequestBody TipoInventario tipo)
	{
		try {
			service.save(tipo);
		}catch (Exception e) {
			log.info(e.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(tipo);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<TipoInventario> delete(@PathVariable("id") Integer id)
	{
		TipoInventario tipo = service.findById(id);
		if(tipo == null) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(tipo);
		return ResponseEntity.ok(tipo);
	}	
}