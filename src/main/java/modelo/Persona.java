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
public class Persona {
    private String email;
    private String password;
    private String nombre;
    private String apellidos;

    public Persona(String email, String password, String nombre,
            String apellidos) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    
    
    
}
