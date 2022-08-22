package edu.pe.idat.app.models.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.pe.idat.app.dto.UsuarioRegistroDTO;
import edu.pe.idat.app.models.entity.Usuario;

public interface UsuarioServicio extends UserDetailsService{
	
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public Usuario guardar2(Usuario usuario);
	
	Usuario buscarPorUsername(String username);

}
