package com.ceatformacion.mascotaspsi.repository;


import com.ceatformacion.mascotaspsi.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
   List<Mascota> findByNombreContainingIgnoreCase(String nombre);

}
