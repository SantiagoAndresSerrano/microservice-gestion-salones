package com.springboot.app.gestionsalones.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.springboot.app.gestionsalones.entities.DetallePrestamo;
import com.springboot.app.gestionsalones.services.VariedadesService;

@Service
public class VariedadesServiceImpl implements VariedadesService
{
	@Autowired
	DetallePrestamoServiceImpl detalleService;

	@Override
	public List<String> getSalones(String json) 
	{
		List<String> salones = new ArrayList<>();
		JSONArray array = new JSONArray(json);
		for(int i = 0; i<array.length(); i++) {
			Gson gson = new Gson();
			Properties properties = gson.fromJson(String.valueOf(array.get(i)), Properties.class);
			salones.add(properties.getProperty("id_salon"));
		}
		return salones;
	}

	@Override
	public int getEstadoSalon(String json) {
		JSONObject obj = new JSONObject(json);
        return Integer.parseInt(obj.get("estado").toString());
	}

	@Override
	public List<String> getBloques(String json) {
		List<String> bloques = new ArrayList<>();
		JSONArray array = new JSONArray(json);
		for(int i = 0; i<array.length(); i++) {
			Gson gson = new Gson();
			Properties properties = gson.fromJson(String.valueOf(array.get(i)), Properties.class);
			bloques.add(properties.getProperty("nombre"));
		}
		return bloques;
	}

	@Override
	public List<String> getUser(String json) {
		List<String> dates = new ArrayList<>();
		JSONObject obj = new JSONObject(json);
        dates.add(obj.get("nombre").toString());
        dates.add(obj.get("email").toString());
        
        JSONArray array = new JSONArray(obj.get("roles").toString());
        boolean admin = false;
        for(int i = 0; i<array.length(); i++) {
			Gson gson = new Gson();
			Properties properties = gson.fromJson(String.valueOf(array.get(i)), Properties.class);
			int aux = Integer.parseInt(properties.getProperty("id"));
			if(aux == 3) dates.add("Estudiante");
			if(aux == 4) dates.add("Docente");
			if(aux == 2) admin = true;
		}
        if(admin) dates.add("Administrador");
        else dates.add("Usuario");
		return dates;
	}

	@Override
	public boolean salonIsDipsonible(String salon, Date fecha_inicio, Date fecha_fin) 
	{
		List<DetallePrestamo> array = detalleService.getAgendados();
		for(DetallePrestamo i: array) {
			if(!i.getId_salon().equals(salon)) continue;
			if(i.getFecha_inicio().getTime() > fecha_inicio.getTime())
				return fecha_fin.getTime() <= i.getFecha_inicio().getTime();
			else 
				return fecha_inicio.getTime() >= i.getFecha_fin().getTime();			
		}
		return true;
	}
	
	@Override
	public String[][] getInventario(String json)
	{
		JSONArray array = new JSONArray(json);
		String inventario [][] = new String[array.length()][2];
		
        for(int i = 0; i<array.length(); i++) {
        	JSONObject object = array.getJSONObject(i).getJSONObject("tipoRel");
			inventario[i][0] = object.get("id_tipo").toString();
			inventario[i][1] = object.get("nombre").toString();
		}
        return inventario;
	}

	@Override
	public String[] getMensaje(String nombre, String salon, Date fecha_inicio, Date fecha_fin, String email) {
		String[] msg = new String[3];
		msg[0] = "Información de Préstamo ";
		msg[1] = "<!DOCTYPE html> \n" +
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
						"del aula "+ salon +" para el día "+ fecha_inicio + " - "+ fecha_fin +".\n" +
						"            </p>\n" +
						"            <div style=\"width:100%;border-top:2px solid #e4230a;padding:1rem 0\">\n" +
						"                <p>Copyright © 2022 Microservicios <br> Todos los derechos reservados.</p><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
						"            </div></div><div class=\"adL\">\n" +
						"        </div></div><div class=\"adL\">\n" +
						"    </div></div><div class=\"adL\">\n" +
						"</div></div>\n" +
						"                        </body> \n" +
						"                         \n" +
						"                        </html>";
				msg[2] = email;
		return msg;
	}
}