package edu.pe.idat.app.controllers;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import edu.pe.idat.app.models.dao.UsuarioServicio;

import edu.pe.idat.app.models.entity.Usuario;

@Controller
public class HomeController {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	@GetMapping({"/index",""})
	public String index(Authentication auth, HttpSession session ,Model model) {
		String username = auth.getName();
		
		
			Usuario usuario = usuarioServicio.buscarPorUsername(username);
			usuario.setPassword(null);
			System.out.println("Usuario"+ usuario);
			session.setAttribute("usuario", usuario);
			model.addAttribute("prueba", usuario);
		
		
		
		return "index";
	}
	
	

}
