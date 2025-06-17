package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MascotaController {

    @Autowired
    private MascotaRepository mascotaRepository;

    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mascotas", new Mascotas());
        return "formulario";

    }
    @PostMapping("/crud")
    public String leerFormulario(@ModelAttribute Mascotas mascotaFormulario , Model model) {
        mascotaRepository.save(mascotaFormulario);
        return "redirect:/crud";
    }
    @GetMapping("/crud")
    public String mostrarMascotas(Model model) {
        model.addAttribute("mascotasParaCrud",mascotaRepository.findAll());
        return "crud";
    }
    @GetMapping("/editar/{id}")
    public String editarMascota(Model model, @PathVariable int id){
        //debemos enviar los datos del cliente que se ha consultado mediante el id, hibernate lo busca y lo almacena en un objeto
        //recibimos el id mediante la url
        //se busca en la bbdd
        //se almacena todos los datos en un objeto tipo cliente
        Mascotas mascota = mascotaRepository.findById(id).get();//BUSCA POR EL ID ENVIADO POR LA URL...
        model.addAttribute("mascotas",mascota);
        return "formulario";

    }

    @GetMapping("/borrar/{id}")
    public String eliminarMascota(Model model, @PathVariable int id) {
        mascotaRepository.deleteById(id);
        return "redirect:/crud";
    }
    
}
