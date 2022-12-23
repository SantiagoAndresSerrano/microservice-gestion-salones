package com.springboot.app.gestionsalones.controllers;

import java.util.List;

import com.springboot.app.gestionsalones.servicesImpl.EmailSenderServiceImp;
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

	@Autowired
	EmailSenderServiceImp emailServiceImp;

	@Autowired
	ApiController apiController;
	
	@GetMapping
	public ResponseEntity<List<DetallePrestamo>> getAll()
	{
		List<DetallePrestamo> detalles = detalle_service.findAll();
		System.out.println("SI");
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
			ResponseEntity <List<String>> user = apiController.getUserDetails(nuevo.getId_persona());
			String nombre = user.getBody().get(0);
			String email = user.getBody().get(1);
			emailServiceImp.enviarEmail("Información de Préstamo ",
					"<!DOCTYPE html> \n" +
							"                        <html lang=\\en\\> \n" +
							"                         \n" +
							"                        <head> \n" +
							"                            <meta charset=\"UTF-8\"> \n" +
							"                            <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> \n" +
							"                            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"\\> \n" +
							"                            <title>Document</title> \n" +
							"                        </head> \n" +
							"                         \n" +
							"                        <body style=\"width: 800px\"> \n" +
							"                           <div style=\"width:800px\">\n" +
							"    <div style=\"background-color:#e4230aa2;width:100%;padding:3rem 0\">\n" +
							"        <div style=\"text-align:center;background-color:#ffffff;margin:0 auto;width:80%;border-radius:8px\">\n" +
							"            <img style=\"margin-top:3rem;width:290px\" src=\"https://ingsistemas.cloud.ufps.edu.co/rsc/img/logo_vertical_ingsistemas_ht180.png\" alt=\"logo\" class=\"CToWUd\" data-bit=\"iit\">\n" +
							"            <p style=\"margin:1rem 0;font-size:25px\"><span class=\"il\">Confirmación</span> de <span class=\"il\">cuenta</span></p>\n" +
							"            <p style=\"color:#424242\">Hola, <b>"+ nombre +"</b>, has registrado un préstamo " +
							"del aula "+ nuevo.getId_salon() +" para el día "+ nuevo.getFecha_inicio() + " - "+ nuevo.getFecha_fin() +".\n" +
							"            </p>\n" +
							"            <div style=\"width:100%;border-top:2px solid #e4230a;padding:1rem 0\">\n" +
							"                <p>Copyright © 2022 Microservicios <br> Todos los derechos reservados.</p><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
							"            </div></div><div class=\"adL\">\n" +
							"        </div></div><div class=\"adL\">\n" +
							"    </div></div><div class=\"adL\">\n" +
							"</div></div>\n" +
							"                        </body> \n" +
							"                         \n" +
							"                        </html>"
					,
					email);
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(nuevo);
	}
}