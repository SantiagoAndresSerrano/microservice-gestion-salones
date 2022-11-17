package com.springboot.app.gestionsalones.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.gestionsalones.entities.Prestamo;
import com.springboot.app.gestionsalones.servicesImpl.PrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.VariedadesServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/prestamo")
@Log4j2
public class PrestamoController 
{
	@Value("${URI}") String URI;
	@Value("${URI_AUTH}") String URI_AUTH;
	
	@Autowired
	PrestamoServiceImpl service;
	
	@Autowired
	VariedadesServiceImpl other;
	
	@GetMapping(value = "/salones/{id}")
	public ResponseEntity<List<String>> getSalones(@PathVariable Integer id)
	{
		RestTemplate restTemplate = new RestTemplate();
		List<String> array = new ArrayList<>();
		try {
        	ResponseEntity<String> response = restTemplate
                    .getForEntity(URI + "bloque/" + String.valueOf(id), String.class);
            if (response.getStatusCode().value() != 200)
            	return ResponseEntity.notFound().build();
            array = other.getSalones(response.getBody());
        }catch (Exception e) { log.info(e.getMessage()); }
		
		/*String inicio = "2022-08-15T10:10:00";
		String fin = "2022-08-15T12:10:00";
		List<String> salones = new ArrayList<>();
		
		for(String i : array) {
			System.out.println(i);
			try {
				ResponseEntity<String> response = restTemplate
	                    .getForEntity(URI + "salon/estado/" +i+"/"+inicio+"/"+fin, String.class);
	        	if (response.getStatusCode().value() != 200)
	            	return ResponseEntity.badRequest().build();
	        	if(other.getEstadoSalon(response.getBody()) == 1)
	            	salones.add(i);
	        }catch (Exception e) {
	            log.info(e.getMessage());
	        }
		}*/
		return new ResponseEntity<List<String>> (array, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bloques")
	public ResponseEntity<List<String>> getBloques()
	{
		RestTemplate restTemplate = new RestTemplate();
		List<String> bloques = new ArrayList<>();
		try {
        	ResponseEntity<String> response = restTemplate
                    .getForEntity(URI + "bloque", String.class);
            if (response.getStatusCode().value() != 200)
            	return ResponseEntity.notFound().build();
            bloques = other.getBloques(response.getBody());
        }catch (Exception e) { log.info(e.getMessage()); }
		
		return new ResponseEntity<List<String>> (bloques, HttpStatus.OK);
	}
	
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
	public ResponseEntity<Prestamo> delete(@PathVariable("id") Integer id)
	{
		Prestamo prestamo = service.findById(id);
		if(prestamo == null) {
			return ResponseEntity.notFound().build();
		}
		
		service.delete(prestamo);
		return ResponseEntity.ok(prestamo);
	}
	
	@GetMapping(value = "/prestamo")
	public void prueba() {
		 String date_time = "2022-08-15T12:10:00";
	     SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	        {
	            try {
	                Date date = dateParser.parse(date_time);
	                System.out.println(date);

	                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	                System.out.println(dateFormatter.format(date));

	            } catch (ParseException e) {
	                e.printStackTrace();
	            }
	        }
	}
	
	@GetMapping(value="/{id}/user")
	public ResponseEntity<List<String>> getUserDetails(@PathVariable Integer id){
		Prestamo prestamo = service.findById(id);
		RestTemplate restTemplate = new RestTemplate();
		List<String> dates = new ArrayList<>();
		try {
        	ResponseEntity<String> response = restTemplate
        			.getForEntity(URI_AUTH + "/user/" + prestamo.getId_persona(), String.class);
        	dates = other.getUser(response.getBody());
        }catch (Exception e) { log.info(e.getMessage()); }
		
		return new ResponseEntity<List<String>> (dates, HttpStatus.OK);		
	}
}