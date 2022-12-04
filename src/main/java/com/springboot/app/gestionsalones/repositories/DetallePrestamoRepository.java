package com.springboot.app.gestionsalones.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;

@Repository
public interface DetallePrestamoRepository extends CrudRepository<DetallePrestamo, Integer>
{
	@Query(nativeQuery = true, value = "SELECT * FROM detalle_prestamo WHERE estado = 0")
	public List<DetallePrestamo> getAgendados();	

}
