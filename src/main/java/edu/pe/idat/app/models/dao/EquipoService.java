package edu.pe.idat.app.models.dao;

import java.util.List;

import edu.pe.idat.app.models.entity.Equipo;

public interface EquipoService {

	List<Equipo> listarEquipoTorneo(int valor);
	
	
}
