package edu.pe.idat.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.dao.ICategoria;
import edu.pe.idat.app.models.entity.Categoria;

@Controller
@RequestMapping("categoria")
@SessionAttributes("categoria")
public class CategoriaController {
	
	@Autowired
	private ICategoria iCategoria;
	
	@GetMapping("/listar")
	public String listarTodo(Model model) {
		model.addAttribute("titulo", "Lista de Categoria");
		model.addAttribute("categorias", iCategoria.findAll());
		return "categoria/listar";
	}
	
	@GetMapping({ "/formulario", "/formulario/{id}" })
	public String formulario(@PathVariable(required = false) Integer id, Model model) {
		if (id == null) {
			model.addAttribute("titulo", "Formulario de Categoria: Registrar");
			model.addAttribute("categoria", new Categoria());
		} else {
			model.addAttribute("titulo", "Formulario de Cliente: Actualizar");
			model.addAttribute("categoria", iCategoria.getById(id));
			model.addAttribute("info", "¡Categoria encontrado exitosamente!");
		}
		return "Categoria/formulario";
	}
	
	@PostMapping("/formulario")
	public String guardar(@Valid Categoria Categoria, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, SessionStatus sessionStatus) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("error", "Debes completar todos los datos obligatorios :(");
			model.addAttribute("titulo", "Formulario de Categoria: Registrar");
			return "categoria/formulario";
		}
		iCategoria.save(Categoria);
		sessionStatus.setComplete();
		redirectAttributes.addFlashAttribute("success", "¡Categoria guardado exitosamente!");
		return "redirect:listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		iCategoria.deleteById(id);
		redirectAttributes.addFlashAttribute("error", "¡Categoria eliminado exitosamente!");
		return "redirect:/categoria/listar";
	}

}
