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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;
import com.springboot.app.gestionsalones.servicesImpl.DetallePrestamoServiceImpl;
import com.springboot.app.gestionsalones.servicesImpl.VariedadesServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/api")

public class ApiController 
{
	@Value("${URI}") String URI;
	@Value("${URI_AUTH}") String URI_AUTH;
	
	@Autowired
	DetallePrestamoServiceImpl detalle_service;
	@Autowired
	VariedadesServiceImpl other;
	
	@GetMapping(value="/{id}/user")
	public ResponseEntity<List<String>> getUserDetails(@PathVariable Integer id){
		DetallePrestamo prestamo = detalle_service.findById(id);
		RestTemplate restTemplate = new RestTemplate();
		List<String> dates = new ArrayList<>();
		try {
        	ResponseEntity<String> response = restTemplate
        			.getForEntity(URI_AUTH + "/user/" + prestamo.getId_persona(), String.class);
        	dates = other.getUser(response.getBody());
        }catch (Exception e) { }
		
		return ResponseEntity.ok(dates);		
	}
	
	@GetMapping(value = "/bloque")
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
        }catch (Exception e) { }
		
		return ResponseEntity.ok(bloques);
	}
	
	@GetMapping(value = "bloques/{id}")
	public ResponseEntity<List<String>> getSalones(@PathVariable Integer id)
	{
		RestTemplate restTemplate = new RestTemplate();
		List<String> salones = new ArrayList<>();
		try {
			ResponseEntity<String> response = restTemplate
	            .getForEntity(URI + "bloque/" + id, String.class);
	        if (response.getStatusCode().value() != 200)
	        	return ResponseEntity.notFound().build();
	        salones = other.getSalones(response.getBody());
	    }catch (Exception e) { }
		
		return ResponseEntity.ok(salones);
	}
	
	@GetMapping(value = "/bloques/{id}/{fecha_inicio}/{fecha_fin}")
	public ResponseEntity<List<String>> getSalonesDisponibles(@PathVariable Integer id, @PathVariable String fecha_inicio, @PathVariable String fecha_fin)
	{
		//RestTemplate restTemplate = new RestTemplate();
		List<String> array = this.getSalones(id).getBody();
		/*List<String> salones = new ArrayList<>();
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
	
	@GetMapping(value = "/{id}/{fi}/{ff}")
	public ResponseEntity<Boolean> isDisponible(@PathVariable String id, @PathVariable String fi, @PathVariable String ff)
	{
		SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date fecha_inicio = new Date(), fecha_fin = new Date();
		try {
			fecha_inicio = dateParser.parse(fi);
			fecha_fin = dateParser.parse(ff);
		} catch (ParseException e) { }
		
		return ResponseEntity.ok(other.salonIsDipsonible(id, fecha_inicio, fecha_fin));
	}
	
	@GetMapping(value = "/inventario/{id}")
	public ResponseEntity<String[][]> getInventario(@PathVariable String id)
	{
		RestTemplate restTemplate = new RestTemplate();
		String inventario [][] = null;
		try {
        	ResponseEntity<String> response = restTemplate
                    .getForEntity(URI + "inventario/ " + id, String.class);
            if (response.getStatusCode().value() != 200)
            	return ResponseEntity.notFound().build();
            inventario = other.getInventario(response.getBody());            
        }catch (Exception e) { }
		
		return ResponseEntity.ok(inventario);
	}
}
