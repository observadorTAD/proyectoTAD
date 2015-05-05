/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;

import java.util.List;

/**
 *
 * @author Alberto Lo
 */
public interface Persona {

    String getApellidos();

    String getCorreo();

    List<Evento> getEventos();

    String getNombre();

    String getNombreUsuario();

    String getPassword();
    
}
