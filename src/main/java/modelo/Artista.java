/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author racede
 */
public class Artista extends Persona{
    private String nombreArtistico;
    private String descripcion;

    public Artista(String nombreArtistico, String descripcion, String email, 
            String password, String usuario, String nombre, String apellidos) {
        super(email, password, usuario, nombre, apellidos);
        this.nombreArtistico = nombreArtistico;
        this.descripcion = descripcion;
    }
    
}
