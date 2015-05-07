/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import controlador.UsuarioController;
import java.util.List;
import modelo.entidades.Usuario;

/**
 *
 * @author racede
 */
public class AdminUsersView extends VerticalLayout implements View {

    private final HorizontalSplitPanel usuarios = new HorizontalSplitPanel();
    private final Table todosUsuarios = new Table("Todos los usuarios");
    private final Button editar = new Button("Editar usuario");
    private final Button borrar = new Button("Borrar usuario");
    private final Button crear = new Button("Crear usuario");
    private final TextField correo = new TextField("Correo");
    private final TextField nombreUsuario = new TextField("Nombre de usuario");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final TextField password = new TextField("Password");
    private final VerticalLayout layoutUsuarios = new VerticalLayout();
    private final VerticalLayout usuarioActual = new VerticalLayout();
    private final HorizontalLayout botones = new HorizontalLayout();
    private final UsuarioController usuarioController = new UsuarioController();

    public AdminUsersView() {
        final List<Usuario> listaUsuarios = usuarioController.getUsuarios();

        //Establecer características a la tabla con todos los usuarios
        todosUsuarios.addContainerProperty("Correo", String.class, null);
        todosUsuarios.addContainerProperty("Nombre de Usuario", String.class, null);
        for (int i = 0; i < listaUsuarios.size(); i++) {
            todosUsuarios.addItem(new Object[]{listaUsuarios.get(i).getCorreo(),
                listaUsuarios.get(i).getNombreUsuario()}, i);
        }
        todosUsuarios.setWidth("100%");
        todosUsuarios.setPageLength(todosUsuarios.getVisibleItemIds().size());
        todosUsuarios.setSelectable(true);
        todosUsuarios.setImmediate(true);

        todosUsuarios.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int numUsuario = Integer.parseInt(todosUsuarios.getValue().toString());
                correo.setValue(listaUsuarios.get(numUsuario).getCorreo());
                nombreUsuario.setValue(listaUsuarios.get(numUsuario).getNombreUsuario());
                nombre.setValue(listaUsuarios.get(numUsuario).getNombre());
                apellidos.setValue(listaUsuarios.get(numUsuario).getApellidos());
                password.setValue(listaUsuarios.get(numUsuario).getPassword());
            }
        });

        editar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                usuarioController.updateUsuario(correo.getValue(), password.getValue(), nombreUsuario.getValue(), nombre.getValue(), apellidos.getValue(), "");
                MainAdminView.refresh("Usuarios");
            }
        });

        borrar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                usuarioController.removeUsuario(correo.getValue());
                MainAdminView.refresh("Usuarios");

            }
        });

        crear.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                usuarioController.crearNuevoUsuario(correo.getValue(), password.getValue(), nombre.getValue(), apellidos.getValue(), nombreUsuario.getValue());
                MainAdminView.refresh("Usuarios");

            }
        });

        usuarioActual.addComponent(new Label("Descripción del usuario seleccionado."));
        usuarioActual.addComponent(correo);
        usuarioActual.addComponent(nombreUsuario);
        usuarioActual.addComponent(nombre);
        usuarioActual.addComponent(apellidos);
        usuarioActual.addComponent(password);
        usuarios.setLocked(true);
        usuarios.setSplitPosition(50);
        botones.addComponent(editar);
        botones.addComponent(crear);
        botones.addComponent(borrar);
        layoutUsuarios.addComponent(todosUsuarios);
        layoutUsuarios.addComponent(botones);
        usuarios.setFirstComponent(layoutUsuarios);
        usuarios.setSecondComponent(usuarioActual);
        addComponent(usuarios);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
