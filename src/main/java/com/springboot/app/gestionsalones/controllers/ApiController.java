package com.springboot.app.gestionsalones.controllers;

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

import com.springboot.app.gestionsalones.servicesImpl.VariedadesServiceImpl;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://microservices-frontend-ufps.vercel.app/**"})
@RequestMapping("/api")
@Log4j2
public class ApiController 
{
	@Value("${URI}") String URI;
	@Value("${URI_AUTH}") String URI_AUTH;
	
	@Autowired
	VariedadesServiceImpl other;
	
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
	
	@GetMapping(value = "bloque/{id}")
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
	    }catch (Exception e) { log.info(e.getMessage()); }
		
		return new ResponseEntity<List<String>> (salones, HttpStatus.OK);
	}
	
	@GetMapping(value = "bloque/{id}/{fecha_inicio}/{fecha_fin}")
	public ResponseEntity<List<String>> getSalonesDisponibles(@PathVariable Integer id, @PathVariable Date fecha_inicio, @PathVariable Date fecha_fin)
	{
		//RestTemplate restTemplate = new RestTemplate();
		List<String> array = this.getSalones(id).getBody();
		/*SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		String inicio = "2022-08-15T10:10:00";
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
}