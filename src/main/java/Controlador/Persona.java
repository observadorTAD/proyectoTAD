/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author racede
 */
public class Persona {
    private String email;
    private String password;
    private String usuario;
    private String nombre;
    private String apellidos;

    public Persona(String email, String password, String usuario, String nombre,
            String apellidos) {
        this.email = email;
        this.password = password;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    
    
}
