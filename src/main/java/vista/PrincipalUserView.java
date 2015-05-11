/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import controlador.UsuarioController;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Evento;
import modelo.entidades.Persona;

/**
 *
 * @author racede
 */
public class PrincipalUserView extends VerticalLayout implements View {

    private final HorizontalSplitPanel eventos = new HorizontalSplitPanel();
    private final Table misEventos = new Table("Mis eventos");
    private final Label titulo = new Label();
    private final Label descripcion = new Label();
    private final Label artista = new Label();
    private final Label lugar = new Label();
    private final Label fecha = new Label();
    private final Label precio = new Label();
    private final VerticalLayout layoutEventos = new VerticalLayout();
    private final VerticalLayout descripcionEvento = new VerticalLayout();
    private final UsuarioController usuarioController = new UsuarioController();

    /**
     *
     * @param usuario
     */
    public PrincipalUserView(Persona usuario) {
        final List<Evento> listaEventoUsuario = usuarioController.getEventos(usuario.getCorreo());
        //Establecer características a la tabla con todos los eventos del usuario
        misEventos.addContainerProperty("Título", String.class, null);
        misEventos.addContainerProperty("Artista", String.class, null);
        for (int i = 0; i < listaEventoUsuario.size(); i++) {
            misEventos.addItem(new Object[]{listaEventoUsuario.get(i).getTitulo(),
                listaEventoUsuario.get(i).getArtista()}, i);
        }
        misEventos.setPageLength(misEventos.getVisibleItemIds().size());
        misEventos.setSelectable(true);
        misEventos.setImmediate(true);
        misEventos.setWidth("100%");

        misEventos.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int numEvento = Integer.parseInt(misEventos.getValue().toString());
                titulo.setValue(listaEventoUsuario.get(numEvento).getTitulo());
                descripcion.setValue(listaEventoUsuario.get(numEvento).getDescripcion());
                artista.setValue("Artista: " + listaEventoUsuario.get(numEvento).getTitulo());
                fecha.setValue("Fecha: " + listaEventoUsuario.get(numEvento).getFecha().toString());
                lugar.setValue("Lugar: " + listaEventoUsuario.get(numEvento).getLugar());
                precio.setValue("Precio: " + listaEventoUsuario.get(numEvento).getPrecio().toString());
            }
        });

        eventos.setLocked(true);
        eventos.setSplitPosition(50);
        descripcionEvento.addComponent(new Label("Descripción del evento seleccionado."));
        descripcionEvento.addComponent(titulo);
        descripcionEvento.addComponent(descripcion);
        descripcionEvento.addComponent(artista);
        descripcionEvento.addComponent(fecha);
        descripcionEvento.addComponent(lugar);
        descripcionEvento.addComponent(precio);
        layoutEventos.addComponent(misEventos);
        eventos.setFirstComponent(layoutEventos);
        eventos.setSecondComponent(descripcionEvento);
        addComponent(new Label("Hola " + usuario.getNombreUsuario()));
        addComponent(eventos);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
