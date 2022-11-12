package com.springboot.app.gestionsalones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.ObservacionPrestamo;

@Repository
public interface ObservacionPrestamoRepository extends CrudRepository<ObservacionPrestamo, Integer>{

}
