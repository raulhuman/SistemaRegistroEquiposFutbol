package edu.pe.idat.app.controllers;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.dao.IEquipo;
import edu.pe.idat.app.models.dao.ITorneo;
import edu.pe.idat.app.models.dao.IUsuario;
import edu.pe.idat.app.models.dao.PersonaService;
import edu.pe.idat.app.models.dao.UsuarioServicio;
import edu.pe.idat.app.models.entity.Equipo;
import edu.pe.idat.app.models.entity.Persona;
import edu.pe.idat.app.models.entity.Torneo;
import edu.pe.idat.app.models.entity.Usuario;

@Controller
@RequestMapping("equipo")
@SessionAttributes("equipo")
public class EquipoController {
	
	@Autowired
	private IEquipo iEquipo;
	
	@Autowired
	private ITorneo iTorneo;
	
	@Autowired
	private IUsuario iUsuario;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	

	@GetMapping("/listar")
	public String listarTodo(Model model) {
		model.addAttribute("titulo", "Lista de Equipos");
		model.addAttribute("equipos", iEquipo.findAll());
		return "equipo/listar";
	}
	
	@GetMapping({"/formulario", "/formulario/{id}"})
	public String formulario(@PathVariable(required = false) Integer id ,Model model, Authentication auth, HttpSession session) {
		List<Torneo> listaTorneos = iTorneo.findAll();
		List<Usuario> listaUsuarios = iUsuario.findAll();
		String username = auth.getName();
		if(id == null) {
			Equipo equipo = new Equipo();
			Usuario usuario = usuarioServicio.buscarPorUsername(username);
			usuario.setPassword(null);
			model.addAttribute("titulo", "Formulario de Equipo: Registrar");
			session.setAttribute("usuario", usuario);
			model.addAttribute("prueba", usuario);
			model.addAttribute("equipo", equipo);
			model.addAttribute("usuarios", listaUsuarios);
			model.addAttribute("torneos", listaTorneos);
		}else {
			model.addAttribute("titulo", "Formulario de equipo: Actualizar");
			model.addAttribute("torneos", listaTorneos);
			model.addAttribute("usuarios", listaUsuarios);
			model.addAttribute("equipo", iEquipo.getById(id));
			model.addAttribute("info", "¡Equipo encontrado exitosamente!");
		}
		
		return "equipo/formulario";
	}
	
	@PostMapping("/formulario")
	public String guardar(@Valid Equipo equipo, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
		List<Torneo> listaTorneos = iTorneo.findAll();
		List<Usuario> listaUsuarios = iUsuario.findAll();
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("error", "Debes completar todos los datos obligatorios :(");
			model.addAttribute("titulo", "Formulario de equipo: Registrar");
			model.addAttribute("torneos", listaTorneos);
			model.addAttribute("usuarios", listaUsuarios);
			return "equipo/formulario";
		}
		if (!file.isEmpty()) {
			String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path carpeta = Paths.get("uploads").resolve(nombreUnico);
			Path raiz = carpeta.toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), raiz);
				equipo.setFoto(nombreUnico);
				redirectAttributes.addFlashAttribute("info", "¡Se ha subido una imagen!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		iEquipo.save(equipo);
		redirectAttributes.addFlashAttribute("success", "¡Equipo guardado exitosamente!");
		return "redirect:listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Equipo equipo = iEquipo.getById(id);
		if (equipo.getFoto() != null) {
			Path raiz = Paths.get("uploads").resolve(equipo.getFoto()).toAbsolutePath();
			File foto = raiz.toFile();
			if(foto.exists() && foto.canRead()) {
				foto.delete();
		}
			
		
		}
		iEquipo.deleteById(id);
		redirectAttributes.addFlashAttribute("success", "Equipo eliminado exitosamente!");
		return "redirect:/equipo/listar";
	}
	
	@GetMapping("/jugadores/{valor}")
	public String buscar(@PathVariable(required = false) Integer valor, Model model) {
		List<Persona> listaJugadores = personaService.listarJugadores(valor);
		model.addAttribute("titulo", "Lista de Jugadores");
		model.addAttribute("jugadores", listaJugadores);
		return "equipo/jugadores.html";
	}
}
