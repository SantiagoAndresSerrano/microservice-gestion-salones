package com.springboot.app.gestionsalones.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
			Gson gson = new Gson();
			Properties properties = gson.fromJson(String.valueOf(array.get(i)), Properties.class);
			inventario[i][0] = properties.getProperty("id_tipo");
			inventario[0][1] = properties.getProperty("nombre");
		}
        return inventario;
	}
}