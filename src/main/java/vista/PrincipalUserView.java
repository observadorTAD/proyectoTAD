/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import modelo.entidades.Usuario;

/**
 *
 * @author racede
 */
public class PrincipalUserView extends VerticalLayout implements View {

    public PrincipalUserView(Usuario usuario) {
        addComponent(new Label("Hola " + usuario.getNombreUsuario()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
