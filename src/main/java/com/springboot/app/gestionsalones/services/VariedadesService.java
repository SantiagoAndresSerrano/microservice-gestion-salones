package com.springboot.app.gestionsalones.services;

import java.util.Date;
import java.util.List;

public interface VariedadesService 
{	
	public List<String> getSalones(String json);
	public int getEstadoSalon(String json);
	public List<String> getBloques(String json);
	public List<String> getUser(String json);
	public boolean salonIsDipsonible(String salon, Date fecha_inicio, Date fecha_fin);
	public String[][] getInventario(String json);
	public String[] getMensaje(String nombre, String salon, Date fecha_inicio, Date fecha_fin, String email);
}