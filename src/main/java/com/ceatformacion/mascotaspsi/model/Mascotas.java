package com.ceatformacion.mascotaspsi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mascotas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nombre;
    private String raza;
    private String descripcion;
    private String nombreAmo;
    private int edad;
    private int peso;
    private String dniAmo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreAmo() {
        return nombreAmo;
    }

    public void setNombreAmo(String nombreAmo) {
        this.nombreAmo = nombreAmo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getDniAmo() {
        return dniAmo;
    }

    public void setDniAmo(String dniAmo) {
        this.dniAmo = dniAmo;
    }

    @Override
    public String toString() {
        return "Mascotas-> " +
                "\nId: " + id +
                "\nNombre: " + nombre +
                "\nraza: " + raza +
                "\nDescripcion: " + descripcion +
                "\nNombreAmo: " + nombreAmo +
                "\nEdad: " + edad +
                "\nPeso: " + peso +
                "\nDniAmo: " + dniAmo;
    }
}
