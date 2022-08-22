package edu.pe.idat.app.models.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pe.idat.app.models.entity.Equipo;

@Service
public class EquipoServiceImpl implements EquipoService{
	@Autowired
	IEquipo iEquipo;

	@Override
	public List<Equipo> listarEquipoTorneo(int valor) {
		
		List<Equipo> equipos = iEquipo.listarEquipoTorneo(valor);
		return equipos;
	}


}
