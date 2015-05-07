package controlador;

import java.util.Date;
import java.util.List;
import modelo.DAO.EventoDAO;
import modelo.entidades.Evento;

/**
 * Clase controlador de la entidad evento.
 * @author Alberto Lo
 */
public class EventoController {
    private final EventoDAO eventoDAO;
    private ArtistaController artistaController = new ArtistaController();
    /**
     * Inicializa el controlador de la entidad eventos.
     */
    public EventoController(){
        eventoDAO = new EventoDAO();
    }
    /**
     * Crea un evento y lo guarda en la persistencia.
     * @param titulo
     * @param lugar
     * @param fecha
     * @param precio
     * @param descripcion
     * @param artista 
     */
    public void crearEvento(String titulo, String lugar, Date fecha, String precio, String descripcion, String artista){
        Double precioFinal;
        if(precio == null ||precio.equals("")){
            precioFinal=0.0;
        }else{
        precioFinal = Double.parseDouble(precio);
    }
        Evento evento = new Evento(titulo, descripcion, lugar, fecha, artista, precioFinal);
        eventoDAO.crearEvento(evento);
    }
    /**
     * Devuelve los eventos existentes en la persistencia.
     * @return 
     */
    public List<Evento> getEventos(){
        return eventoDAO.getEventos();
    }
    
    /**
     * Actualiza un evento con los parámetros proveidos
     * @param tituloAnt
     * @param artistaAnt
     * @param fechaAnt
     * @param titulo
     * @param lugar
     * @param fecha
     * @param precio
     * @param descripcion
     * @param artista 
     */
    public void updateEvent(String tituloAnt, String artistaAnt, Date fechaAnt, String titulo, String lugar, Date fecha, String precio, String descripcion, String artista) {
        Double precioFinal;
        if (precio == null || precio.equals("")) {
            precioFinal = 0.0;
        } else {
            precioFinal = Double.parseDouble(precio);
        }
        eventoDAO.updateEvent(tituloAnt, artistaAnt, fechaAnt, titulo, lugar, fecha, precioFinal, descripcion, artista);
    }

    /**
     * Elimina un evento de un determinado artista.
     * @param titulo
     * @param artista
     * @param fecha 
     */
    public void removeEvent(String titulo, String artista, Date fecha) {
        eventoDAO.removeEvent(titulo, artista, fecha);
    }
/**
 * Devuelve el número de eventos existente
 * @return numero de eventos
 */
    public int countEventos() {
        return eventoDAO.countEventos();
    }
}
