package com.springboot.app.gestionsalones.services;

import java.util.List;

public interface VariedadesService 
{	
	public List<String> getSalones(String json);
	public int getEstadoSalon(String json);
}