package com.ceatformacion.mascotaspsi.controller;


import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistorialController {

    @Autowired
    private HistorialRepository historialRepository;
    @Autowired
    private MascotaRepository mascotaRepository;

    private HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/historial/{id}")
    public String getHistorial(@PathVariable int id) {
        historialRepository.findById(id).get();
        return "historial";
    }

    //API REST-> RECIBE Y ENVIA LOS DATOS JSON
    //HISTORIAL{id: ,fechaVisista}

    @ResponseBody //API REST para guardar una nueva entrada al historial
    @PostMapping("api/historial")
    public Historial save(@RequestBody Historial historial) {
        return historialRepository.save(historial);
    }

    //Este metodo busca el historial de la mascota cuando se solicite por el id
    @ResponseBody
    @GetMapping("/mascota/{id}")
    public List<Historial> findByMascotaId(@PathVariable int id) {
        return historialService.obtenerHistorialXMascota(id);
    }

    //Controlar la vista del formulario e historial
    @GetMapping("/consulta/{id}")
    public String getHistorialById(@PathVariable int id, Model model) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow();
        List<Historial> historial=historialRepository.findByMascotaId(id);
        model.addAttribute("historial", historial);
        model.addAttribute("mascota", mascota);
        model.addAttribute("nuevaVisita", new Historial());
        return "historial";
    }

    //Guardar la visita
    @PostMapping("/consulta/{id}")
    public String registrarVisita(@PathVariable int id, @ModelAttribute("nuevaVisita") Historial nuevaVisita) {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow();
        nuevaVisita.setMascota(mascota);
        historialRepository.save(nuevaVisita);
        return "redirect:/consulta/" + id;
    }


}
