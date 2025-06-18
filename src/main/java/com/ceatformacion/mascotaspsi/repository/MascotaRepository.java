package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository <Mascotas, Integer> {

    //Necesitamos esta lista para buscar por nombre
    List<Mascotas> findByNombreContainingIgnoreCase(String nombre);
}
