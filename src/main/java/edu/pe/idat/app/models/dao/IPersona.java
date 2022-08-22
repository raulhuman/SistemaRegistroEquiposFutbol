package edu.pe.idat.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.app.models.entity.Persona;

@Repository
public interface IPersona extends JpaRepository<Persona, Integer>{

	@Query(value = "SELECT *  FROM persona WHERE persona.equipo_id = :valor", nativeQuery = true)
	List<Persona> listarJugadores(@Param("valor") int valor);
	
	

}
