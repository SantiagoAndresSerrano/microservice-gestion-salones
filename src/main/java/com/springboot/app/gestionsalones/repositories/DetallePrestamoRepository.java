package com.springboot.app.gestionsalones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.DetallePrestamo;

@Repository
public interface DetallePrestamoRepository extends CrudRepository<DetallePrestamo, Integer>{

}
