package edu.pe.idat.app.controllers;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.pe.idat.app.models.dao.EquipoService;
import edu.pe.idat.app.models.dao.ICategoria;
import edu.pe.idat.app.models.dao.ITorneo;
import edu.pe.idat.app.models.entity.Categoria;
import edu.pe.idat.app.models.entity.Equipo;
import edu.pe.idat.app.models.entity.Torneo;



@Controller
@RequestMapping("torneo")
public class TorneoController {
	
	@Autowired
	private EquipoService equipoService;
	
	@Autowired
    private ITorneo itorneo;
	
	@Autowired
	private ICategoria iCategoria;

    @GetMapping("/listar")
    public String ListarTorneo(Model model){
    	List<Torneo> listaTorneo = itorneo.findAll();
        model.addAttribute("titulo", "Lista de Torneos");
        model.addAttribute("Torneo", listaTorneo);
        return "torneo/listar.html";
    }
    @GetMapping({"/formulario", "/formulario/{id}"})
    public String formulario(@PathVariable(required = false) Integer id, Model model){
        List<Categoria> listaCategorias = iCategoria.findAll();
    	if (id == null){
            Torneo torneo = new Torneo();
            model.addAttribute("titulo","Formulario del Torneo: Registrar");
            model.addAttribute("torneo", torneo);
            model.addAttribute("categorias", listaCategorias);
        } else{
            model.addAttribute("titulo", "Actualizar Torneos");
            model.addAttribute("categorias", listaCategorias);
            model.addAttribute("torneo",itorneo.getById(id));
        }
        return "torneo/formulario.html";
    }
    @PostMapping("/formulario")
    public String guardarTorneo(@Valid Torneo torneo, BindingResult bindingResult, Model model, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            model.addAttribute("titulo", "Formulario del Torneo");
            return "torneo/formulario.html";
        }
        if (!file.isEmpty()) {
			String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			Path carpeta = Paths.get("uploads").resolve(nombreUnico);
			Path raiz = carpeta.toAbsolutePath();
			try {
				Files.copy(file.getInputStream(), raiz);
				torneo.setFoto(nombreUnico);
				redirectAttributes.addFlashAttribute("info", "¡Se ha subido una imagen!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        
        
        itorneo.save(torneo);
		redirectAttributes.addFlashAttribute("success", "¡Torneo guardado exitosamente!");
        return "redirect:listar";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        itorneo.deleteById(id);
        return "redirect:/Torneo/listar";
    }
    
    @GetMapping("/equipos/{valor}")
    public String buscar(@PathVariable (required = false) Integer valor ,Model model){
    	
    	List<Equipo> listaEquipos = equipoService.listarEquipoTorneo(valor);
    	 model.addAttribute("titulo", "Lista de equipos");
    	 model.addAttribute("equipos", listaEquipos);
    	return "torneo/equipos.html";
    }

}
