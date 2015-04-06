/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.List;

/**
 *
 * @author racede
 */
public class Evento {
    private String titulo;
    private String descripcion;
    private String lugar;
    private List<Persona> usuarios;
    private Artista artista;
    private float precio;

    public Evento(String titulo, String descripcion, String lugar, List<Persona> usuarios, 
            Artista artista, float precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.usuarios = usuarios;
        this.artista = artista;
        this.precio = precio;
    }
    
}
