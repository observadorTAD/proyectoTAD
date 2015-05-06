/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Date;
import java.util.List;
import modelo.DAO.EventoDAO;
import modelo.entidades.Evento;

/**
 *
 * @author Alberto Lo
 */
public class EventoController {

    private final EventoDAO eventoDAO;
    private ArtistaController artistaController = new ArtistaController();

    public EventoController() {
        eventoDAO = new EventoDAO();
    }

    public void crearEvento(String titulo, String lugar, Date fecha, String precio, String descripcion, String artista) {
        Double precioFinal;
        if (precio == null || precio.equals("")) {
            precioFinal = 0.0;
        } else {
            precioFinal = Double.parseDouble(precio);
        }
        Evento evento = new Evento(titulo, descripcion, lugar, fecha, artista, precioFinal);
        eventoDAO.crearEvento(evento);
    }

    public List<Evento> getEventos() {
        return eventoDAO.getEventos();
    }

    public void updateEvent(String tituloAnt, String artistaAnt, Date fechaAnt, String titulo, String lugar, Date fecha, String precio, String descripcion, String artista) {
        Double precioFinal;
        if (precio == null || precio.equals("")) {
            precioFinal = 0.0;
        } else {
            precioFinal = Double.parseDouble(precio);
        }
        eventoDAO.updateEvent(tituloAnt, artistaAnt, fechaAnt, titulo, lugar, fecha, precioFinal, descripcion, artista);
    }

    public void removeEvent(String titulo, String artista, Date fecha) {
        eventoDAO.removeEvent(titulo, artista, fecha);
    }

    public int countEventos() {
        return eventoDAO.countEventos();
    }
}
