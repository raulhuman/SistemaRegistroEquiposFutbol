package edu.pe.idat.app.controllers;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.dao.IRol;
import edu.pe.idat.app.models.dao.IUsuario;
import edu.pe.idat.app.models.dao.UsuarioServicio;
import edu.pe.idat.app.models.entity.Rol;
import edu.pe.idat.app.models.entity.Usuario;

@Controller
@RequestMapping("usuario")
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private IUsuario iUsuario;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private IRol iRol;
	

	@GetMapping("/listar")
	public String listarTodo(Model model) {
		model.addAttribute("titulo", "Lista de Usuarios");
		model.addAttribute("usuarios", iUsuario.findAll());
		return "usuario/listar";
	}

	@GetMapping({ "/formulario", "/formulario/{id}" })
	public String formulario(@PathVariable(required = false) Integer id, Model model , RedirectAttributes redirectAttributes) {
		if (id == null) {
			model.addAttribute("titulo", "Formulario de Cliente: Registrar");
			model.addAttribute("usuario", new Usuario());
			model.addAttribute("roles", iRol.findAll());
			
		} else {
			
			model.addAttribute("titulo", "Formulario de Cliente: Actualizar");
			model.addAttribute("usuario", iUsuario.getById(id));
			model.addAttribute("info", "¡Cliente encontrado exitosamente!");
		}
		return "usuario/formulario";
	}

	@PostMapping("/formulario")
	public String guardar(@Valid Usuario usuario, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Debes completar todos los datos obligatorios :(");
			model.addAttribute("titulo", "Formulario de Cliente: Registrar");
			model.addAttribute("roles", iRol.findAll());
			return "usuario/formulario";
		}
		
		if (!file.isEmpty()) {
			String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path carpeta = Paths.get("uploads").resolve(nombreUnico);
			Path raiz = carpeta.toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), raiz);
				usuario.setFoto(nombreUnico);
				redirectAttributes.addFlashAttribute("info", "¡Se ha subido una imagen!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		usuarioServicio.guardar2(usuario);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("success", "¡Cliente guardado exitosamente!");
		return "redirect:formulario";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Usuario usuario = iUsuario.getById(id);
		Path raiz = Paths.get("uploads").resolve(usuario.getFoto()).toAbsolutePath();
		File foto = raiz.toFile();
		if(foto.exists() && foto.canRead()) {
			foto.delete();
		}
		
		iUsuario.deleteById(id);
		redirectAttributes.addFlashAttribute("success", "Â¡Cliente eliminado exitosamente!");
		return "redirect:/usuario/listar";
	}
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("titulo", "Detalle del Cliente");
		model.addAttribute("usuario", iUsuario.getById(id));
		return "usuario/detalle";
	}
	
}
