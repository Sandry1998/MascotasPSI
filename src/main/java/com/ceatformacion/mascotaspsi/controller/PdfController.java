package com.ceatformacion.mascotaspsi.controller;


import com.ceatformacion.mascotaspsi.model.Mascota;
import com.ceatformacion.mascotaspsi.repository.MascotaRepository;
import com.ceatformacion.mascotaspsi.services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;


//Para generar ficheros Json,Pdf...etc
@RestController
public class PdfController {

    //Llama al repositorio
    @Autowired
     private MascotaRepository mascotaRepository;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportarPdf() {
        //generar la lista
        List<Mascota> mascotas = mascotaRepository.findAll();
        //Los flujos o datos se convierte en byte y lo que se va a generar
        ByteArrayInputStream pfdStream= pdfService.exportarMascotas(mascotas);

        //se crean las cabeceras http para indicar que el archivo será mostrado como pdf
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=mascotas.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(pfdStream.readAllBytes());
    }


}
