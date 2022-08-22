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
import edu.pe.idat.app.models.dao.IPersona;
import edu.pe.idat.app.models.dao.UsuarioServicio;
import edu.pe.idat.app.models.entity.Equipo;
import edu.pe.idat.app.models.entity.Persona;
import edu.pe.idat.app.models.entity.Usuario;


@Controller
@RequestMapping("persona")
@SessionAttributes("persona")
public class PersonaController {

	@Autowired
	private IPersona iPersona;
	
	@Autowired
	private IEquipo iEquipo;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/listar")
	public String listarTodo(Model model) {
		model.addAttribute("titulo", "Lista de Jugadores");
		model.addAttribute("personas", iPersona.findAll());
		return "persona/listar";
	}
	
	@GetMapping({ "/formulario", "/formulario/{id}" })
	public String formulario(@PathVariable(required = false) Integer id, Model model, Authentication auth, HttpSession session) {
		List<Equipo> listaEquipos = iEquipo.findAll();
		String username = auth.getName();
		if (id == null) {
			Usuario usuario = usuarioServicio.buscarPorUsername(username);
			usuario.setPassword(null);
			System.out.println("Usuario"+ usuario);
			session.setAttribute("usuario", usuario);
			model.addAttribute("prueba", usuario);
			model.addAttribute("titulo", "Formulario de Jugador: Registrar");
			model.addAttribute("persona", new Persona());
			model.addAttribute("equipos", listaEquipos);
			
		} else {
			model.addAttribute("titulo", "Formulario de Jugador: Actualizar");
			model.addAttribute("equipos", listaEquipos);
			model.addAttribute("persona", iPersona.getById(id));
			model.addAttribute("info", "¡Cliente encontrado exitosamente!");
		}
		return "persona/formulario";
	}
	
	@PostMapping("/formulario")
	public String guardar(@Valid Persona persona, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
		List<Equipo> listaEquipos = iEquipo.findAll();
		/*if(bindingResult.hasErrors()) {
			model.addAttribute("error", "Debes completar todos los datos obligatorios");
			model.addAttribute("titulo", "Formulario de Jugador: Registrar");
			model.addAttribute("equipos", listaEquipos);
			return "persona/formulario";
		}*/
		if (!file.isEmpty()) {
			String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path carpeta = Paths.get("uploads").resolve(nombreUnico);
			Path raiz = carpeta.toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), raiz);
				persona.setFoto(nombreUnico);
				redirectAttributes.addFlashAttribute("info", "Se ha subido una image");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			iPersona.save(persona);
			model.addAttribute("equipos", listaEquipos);
			redirectAttributes.addFlashAttribute("success", "Persona guardado exitosamente!");

			return "redirect:formulario";
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		Persona persona = iPersona.getById(id);
		Path raiz = Paths.get("uploads").resolve(persona.getFoto()).toAbsolutePath();
		File foto = raiz.toFile();
		if(foto.exists() && foto.canRead()) {
			foto.delete();
		}
		
		iPersona.deleteById(id);
		redirectAttributes.addFlashAttribute("success", "Â¡Cliente eliminado exitosamente!");
		return "redirect:/persona/listar";
	}
	@GetMapping("/detalle/{id}")
	public String detalle(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("titulo", "Detalle del Jugador");
		model.addAttribute("usuario", iPersona.getById(id));
		return "persona/detalle";
	}
	
}
