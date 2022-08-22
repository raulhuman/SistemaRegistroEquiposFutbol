package edu.pe.idat.app.models.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.app.models.entity.Persona;

@Service
public class PersonaServiceImpl implements PersonaService{
	
	@Autowired
	IPersona iPersona;

	@Override
	public String guardarPersonas(List<Persona> listaPersonas) {
		List<Persona> guardarListaPersonas = new ArrayList<>();
		iPersona.saveAll(listaPersonas).forEach(guardarListaPersonas::add);
		return "Saved : user ids -" + guardarListaPersonas.stream().map(u -> u.getId()).collect(Collectors.toList());
	}

	@Override
	public List<Persona> listarJugadores(int valor) {
		List<Persona> jugadores = iPersona.listarJugadores(valor);
		return jugadores;
	}

}
