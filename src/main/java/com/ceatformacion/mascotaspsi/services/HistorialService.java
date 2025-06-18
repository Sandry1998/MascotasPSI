package com.ceatformacion.mascotaspsi.services;


import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;
import org.yaml.snakeyaml.tokens.Token;

import java.util.List;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    //Creamos un metodo que obtiene el historial x mascota
    public List<Historial> obtenerHistorialXMascota(int id) {
        return historialRepository.findByMascotasIdMascota(id);
    }

    //Guardar la entrada al historial
    public Historial guardarHistorialXMascota(Historial historial) {
        return historialRepository.save(historial);
    }

    public void borrarHistorialXMascota(Integer id_historial) {
        historialRepository.deleteById(id_historial);
    }

}
