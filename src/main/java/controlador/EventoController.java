/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.DAO.EventoDAO;

/**
 *
 * @author Alberto Lo
 */
public class EventoController {
    private final EventoDAO eventoDAO;
    
    public EventoController(){
        eventoDAO = new EventoDAO();
    }
}
