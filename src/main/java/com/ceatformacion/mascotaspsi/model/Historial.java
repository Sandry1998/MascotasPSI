package com.ceatformacion.mascotaspsi.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Historial {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_historial;



    private LocalDate fechaVisita;
    private String motivoConsulta;
    private String tratamiento;
    private String observaciones;

    //Si necesito conectarlo con Mascotas
    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private Mascotas mascotas;

    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id) {
        this.id_historial = id;
    }

    public LocalDate getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(LocalDate fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Mascotas getMascotas() {
        return mascotas;
    }

    public void setMascotas(Mascotas mascotas) {
        this.mascotas = mascotas;
    }

    @Override
    public String toString() {
        return "Historial-> " +
                "\nid_historial: " + id_historial +
                "\nfechaVisita: " + fechaVisita +
                "\nmotivoConsulta: " + motivoConsulta +
                "\ntratamiento: " + tratamiento +
                "\nobservaciones: " + observaciones +
                "\nmascotas: " + mascotas;
    }
}
