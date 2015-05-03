/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 */
package controlador;

import modelo.entidades.Artista;

/**
 *
 * @author Alberto Lo
 */
public class ArtistaController {
    
    public Artista getArtista(String artista){
        return new Artista(artista, artista, artista, artista, artista, artista);
    }
}
