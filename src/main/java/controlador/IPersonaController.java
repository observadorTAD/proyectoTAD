/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import modelo.entidades.Evento;
import modelo.entidades.Persona;

/**
 *
 * @author Alberto Lo
 */
public interface IPersonaController {

    void addEventos(String correo, List<Evento> eventos);

    List<Evento> getEventos(String correo);

    Persona getUsuario(String correo);

    boolean isArtista(String correo);

    void removeUsuario(String correo);

    boolean login(String correo, String password);

    public void updateUsuario(String correo, String password, String nombreUsuario, String nombre, String apellidos, String descripcion);
}
