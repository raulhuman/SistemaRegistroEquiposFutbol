package edu.pe.idat.app.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.pe.idat.app.models.entity.Rol;

public interface IRol extends CrudRepository<Rol, Long>{
	
	public List<Rol> findByNombre(String nombre);

}
