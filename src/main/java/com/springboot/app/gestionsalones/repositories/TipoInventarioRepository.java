package com.springboot.app.gestionsalones.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.gestionsalones.entities.TipoInventario;

@Repository
public interface TipoInventarioRepository extends CrudRepository<TipoInventario, Integer>{

}
