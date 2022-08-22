package edu.pe.idat.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.pe.idat.app.models.entity.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer>{

	public Usuario findByUsername(String username);
	
	
	
}
