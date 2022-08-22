package edu.pe.idat.app.models.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;

import edu.pe.idat.app.models.entity.Persona;

public interface PersonaService {

	public String guardarPersonas(List<Persona> listaPersonas);
	
	List<Persona> listarJugadores(@Param("valor") int valor);
}
