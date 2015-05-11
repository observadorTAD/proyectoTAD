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
import controlador.ArtistaController;
import java.util.List;
import modelo.entidades.Artista;

/**
 * Vista de administrador para gestionar los artistas. Muestra una tabla 
 * de artistas y permite realizar las operaciones CRUD sobre la entidad.
 * @author racede
 */
public class AdminArtistView extends VerticalLayout implements View {

    private final HorizontalSplitPanel artistas = new HorizontalSplitPanel();
    private final Table todosArtistas = new Table("Todos los artistas");
    private final Button editar = new Button("Editar artista");
    private final Button borrar = new Button("Borrar artista");
    private final Button crear = new Button("Crear artista");
    private final TextField correo = new TextField("Correo");
    private final TextField nombreArtista = new TextField("Nombre artísico");
    private final TextField descripcion = new TextField("Descripción");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final TextField password = new TextField("Password");
    private final VerticalLayout layoutArtistas = new VerticalLayout();
    private final VerticalLayout artistaActual = new VerticalLayout();
    private final HorizontalLayout botones = new HorizontalLayout();
    private final ArtistaController artistaController = new ArtistaController();

    /**
     * Constructor de la vista
     */
    public AdminArtistView() {
        final List<Artista> listaArtistas = artistaController.getArtistas();

        //Establecer características a la tabla con todos los artistas
        todosArtistas.addContainerProperty("Correo", String.class, null);
        todosArtistas.addContainerProperty("Nombre de Artista", String.class, null);
        for (int i = 0; i < listaArtistas.size(); i++) {
            todosArtistas.addItem(new Object[]{listaArtistas.get(i).getCorreo(),
                listaArtistas.get(i).getNombreUsuario()}, i);
        }
        todosArtistas.setWidth("100%");
        todosArtistas.setPageLength(todosArtistas.getVisibleItemIds().size());
        todosArtistas.setSelectable(true);
        todosArtistas.setImmediate(true);

        todosArtistas.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int numArtista = Integer.parseInt(todosArtistas.getValue().toString());
                correo.setValue(listaArtistas.get(numArtista).getCorreo());
                nombreArtista.setValue(listaArtistas.get(numArtista).getNombreUsuario());
                descripcion.setValue(listaArtistas.get(numArtista).getDescripcion());
                nombre.setValue(listaArtistas.get(numArtista).getNombre());
                apellidos.setValue(listaArtistas.get(numArtista).getApellidos());
                password.setValue(listaArtistas.get(numArtista).getPassword());
            }
        });

        editar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                artistaController.updateUsuario(correo.getValue(), password.getValue(), nombreArtista.getValue(), nombre.getValue(), apellidos.getValue(), descripcion.getValue());
                MainAdminView.refresh("Artistas");
            }
        });

        borrar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                artistaController.removeUsuario(correo.getValue());
                MainAdminView.refresh("Artistas");

            }
        });

        crear.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                artistaController.crearNuevoArtista(correo.getValue(), password.getValue(), nombre.getValue(), apellidos.getValue(), nombreArtista.getValue());
                MainAdminView.refresh("Artistas");

            }
        });

        artistaActual.addComponent(new Label("Descripción del artista seleccionado."));
        artistaActual.addComponent(correo);
        artistaActual.addComponent(nombreArtista);
        artistaActual.addComponent(descripcion);
        artistaActual.addComponent(nombre);
        artistaActual.addComponent(apellidos);
        artistaActual.addComponent(password);
        artistas.setLocked(true);
        artistas.setSplitPosition(50);
        botones.addComponent(editar);
        botones.addComponent(crear);
        botones.addComponent(borrar);
        layoutArtistas.addComponent(todosArtistas);
        layoutArtistas.addComponent(botones);
        artistas.setFirstComponent(layoutArtistas);
        artistas.setSecondComponent(artistaActual);
        addComponent(artistas);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
