package edu.pe.idat.app.models.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.pe.idat.app.dto.UsuarioRegistroDTO;
import edu.pe.idat.app.models.entity.Rol;
import edu.pe.idat.app.models.entity.Usuario;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuario iUsuario;

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(registroDTO.getNombre(), registroDTO.getApellidos(), registroDTO.getDni(),
				registroDTO.getEmail(), registroDTO.getTelefono(), registroDTO.getEdad(), registroDTO.getUsername()
				,passwordEncoder.encode(registroDTO.getPassword()), registroDTO.getFoto() , Arrays.asList(new Rol("ROLE_USER")));
		return iUsuario.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = iUsuario.findByUsername(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password invalidos");
		}
		return new User(usuario.getUsername(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}

	@Override
	public Usuario guardar2(Usuario usuario) {
		Usuario usuario2 = new Usuario(usuario.getNombre(), usuario.getApellidos(), usuario.getDni(),
				usuario.getEmail(), usuario.getTelefono(), usuario.getEdad(), usuario.getUsername(),
				passwordEncoder.encode(usuario.getPassword()), usuario.getFoto(), usuario.getRoles());
		return iUsuario.save(usuario2);
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return iUsuario.findByUsername(username);
	}

}
