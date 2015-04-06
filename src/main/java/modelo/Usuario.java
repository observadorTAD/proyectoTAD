/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author racede
 */
public class Usuario extends Persona{
    private List<Evento> eventos;

    public Usuario(List<Evento> eventos, String email, String password, 
            String usuario, String nombre, String apellidos) {
        super(email, password, usuario, nombre, apellidos);
        this.eventos = eventos;
    }
    
    
}
