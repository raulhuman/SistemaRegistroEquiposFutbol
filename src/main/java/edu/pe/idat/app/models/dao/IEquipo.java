package edu.pe.idat.app.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.pe.idat.app.models.entity.Equipo;

@Repository
public interface IEquipo extends JpaRepository<Equipo, Integer>{
	
	@Query(value = "SELECT *  FROM equipo WHERE equipo.torneo_id = :valor", nativeQuery = true)
	List<Equipo> listarEquipoTorneo(@Param("valor") int valor);
	
	


}
