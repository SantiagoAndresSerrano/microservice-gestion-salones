package com.springboot.app.gestionsalones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.Prestamo;

@Repository
public interface PrestamoRepository extends CrudRepository<Prestamo, Integer>{

}
