package edu.pe.idat.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import edu.pe.idat.app.dto.UsuarioRegistroDTO;
import edu.pe.idat.app.models.dao.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}
	
	@GetMapping
	public String mostrarFormularioRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, @RequestParam MultipartFile file) {
		if (!file.isEmpty()) {
			String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path carpeta = Paths.get("uploads").resolve(nombreUnico);
			Path raiz = carpeta.toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), raiz);
				registroDTO.setFoto(nombreUnico);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		usuarioServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}


}
