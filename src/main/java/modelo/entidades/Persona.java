/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades;
public class Persona {
    private String email;
    private String password;
    private String nombre;
    private String apellidos;

/**
 * Constructor para la creación de un objeto de tipo Persona
 * @param email
 * @param password
 * @param nombre
 * @param apellidos
 */
    public Persona(String email, String password, String nombre,
            String apellidos) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    
    
}
