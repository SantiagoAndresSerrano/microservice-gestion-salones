package com.springboot.app.gestionsalones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.NovedadPrestamo;

@Repository
public interface NovedadPrestamoRepository extends CrudRepository<NovedadPrestamo, Integer>{

}
