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
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import controlador.EventoController;
import java.util.Date;
import java.util.List;
import modelo.entidades.Evento;

/**
 *
 * @author racede
 */
public class AdminEventView extends VerticalLayout implements View {

    private final HorizontalSplitPanel eventos = new HorizontalSplitPanel();
    private final Table todosEventos = new Table("Todos los eventos");
    private final Button editar = new Button("Editar evento");
    private final Button borrar = new Button("Borrar evento");
    private final Button crear = new Button("Crear evento");
    private final TextField titulo = new TextField("Título");
    private final TextField descripcion = new TextField("Descripción");
    private final TextField artista = new TextField("Artista");
    private final TextField lugar = new TextField("Lugar");
    private final DateField fecha = new DateField("Fecha");
    private final TextField precio = new TextField("Precio");
    private final VerticalLayout layoutEventos = new VerticalLayout();
    private final VerticalLayout descripcionEvento = new VerticalLayout();
    private final HorizontalLayout botones = new HorizontalLayout();
    private final EventoController eventoController = new EventoController();

    public AdminEventView() {
        final List<Evento> listaEventos = eventoController.getEventos();

        //Establecer características a la tabla con todos los eventos
        todosEventos.addContainerProperty("Título", String.class, null);
        todosEventos.addContainerProperty("Artista", String.class, null);
        for (int i = 0; i < listaEventos.size(); i++) {
            todosEventos.addItem(new Object[]{listaEventos.get(i).getTitulo(),
                listaEventos.get(i).getArtista()}, i);
        }
        todosEventos.setWidth("100%");
        todosEventos.setPageLength(todosEventos.getVisibleItemIds().size());
        todosEventos.setSelectable(true);
        todosEventos.setImmediate(true);

        todosEventos.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int numEvento = Integer.parseInt(todosEventos.getValue().toString());
                titulo.setValue(listaEventos.get(numEvento).getTitulo());
                descripcion.setValue(listaEventos.get(numEvento).getDescripcion());
                artista.setValue(listaEventos.get(numEvento).getArtista());
                fecha.setValue(listaEventos.get(numEvento).getFecha());
                lugar.setValue(listaEventos.get(numEvento).getLugar());
                precio.setValue(listaEventos.get(numEvento).getPrecio().toString());
            }
        });

        editar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                int numEvento = Integer.parseInt(todosEventos.getValue().toString());
                eventoController.updateEvent(listaEventos.get(numEvento).getTitulo(), listaEventos.get(numEvento).getArtista(), listaEventos.get(numEvento).getFecha(), titulo.getValue(), lugar.getValue(), (Date) fecha.getValue(), precio.getValue(), descripcion.getValue(), artista.getValue());
                MainAdminView.refresh("Eventos");
            }
        });

        borrar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                eventoController.removeEvent(titulo.getValue(), artista.getValue(), fecha.getValue());
                MainAdminView.refresh("Eventos");

            }
        });

        crear.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                eventoController.crearEvento(titulo.getValue(), lugar.getValue(), (Date) fecha.getValue(), precio.getValue(), descripcion.getValue(), artista.getValue());
                MainAdminView.refresh("Eventos");

            }
        });

        descripcionEvento.addComponent(new Label("Descripción del evento seleccionado."));
        descripcionEvento.addComponent(titulo);
        descripcionEvento.addComponent(descripcion);
        descripcionEvento.addComponent(artista);
        descripcionEvento.addComponent(fecha);
        descripcionEvento.addComponent(lugar);
        descripcionEvento.addComponent(precio);
        eventos.setLocked(true);
        eventos.setSplitPosition(50);

        eventos.setFirstComponent(layoutEventos);
        eventos.setSecondComponent(descripcionEvento);
        botones.addComponent(editar);
        botones.addComponent(crear);
        botones.addComponent(borrar);
        layoutEventos.addComponent(todosEventos);
        layoutEventos.addComponent(botones);
        addComponent(eventos);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
