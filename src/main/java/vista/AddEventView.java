package vista;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.event.DataBoundTransferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import controlador.EventoController;
import controlador.UsuarioController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import modelo.entidades.Evento;
import modelo.entidades.Persona;

/**
 * Vista para añadir nuevos eventos de los usuarios
 * @author racede
 */
public class AddEventView extends VerticalLayout implements View {

    private final HorizontalSplitPanel eventos = new HorizontalSplitPanel();
    private final Table todosEventos = new Table("Todos los eventos");
    private final Table misEventos = new Table("Mis eventos");
    private final Button guardar = new Button("Guardar eventos");
    private final Label titulo = new Label();
    private final Label descripcion = new Label();
    private final Label artista = new Label();
    private final Label lugar = new Label();
    private final Label fecha = new Label();
    private final Label precio = new Label();
    private final VerticalLayout layoutEventos = new VerticalLayout();
    private final VerticalLayout descripcionEvento = new VerticalLayout();
    private final EventoController eventoController = new EventoController();
    private final UsuarioController usuarioController = new UsuarioController();

    /**
     * Constructor de la vista. Esta vista permite añadir eventos entre 
     * dos tablas mediante la técnica drag and drop.
     * @param usuario
     */
    public AddEventView(final Persona usuario) {
        final List<Evento> listaEventos = eventoController.getEventos();
        List<Evento> listaEventoUsuario = usuarioController.getEventos(usuario.getCorreo());

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

        //Establecer características a la tabla con todos los eventos del usuario
        misEventos.addContainerProperty("Título", String.class, null);
        misEventos.addContainerProperty("Artista", String.class, null);
        for (int i = 0; i < listaEventoUsuario.size(); i++) {
            int enc = listaEventos.indexOf(listaEventoUsuario.get(i));
            System.out.println(enc);
            System.out.println(listaEventoUsuario.get(i).getTitulo());
            if (enc >= 0) {
                misEventos.addItem(new Object[]{listaEventoUsuario.get(i).getTitulo(),
                    listaEventoUsuario.get(i).getArtista()}, enc);
            }
        }
        misEventos.setPageLength(todosEventos.getVisibleItemIds().size());
        misEventos.setSelectable(true);
        misEventos.setImmediate(true);
        misEventos.setWidth("80%");

        //Por defecto aparece el primer evento
        titulo.setValue(listaEventos.get(0).getTitulo());
        descripcion.setValue(listaEventos.get(0).getDescripcion());
        artista.setValue("Artista: " + listaEventos.get(0).getTitulo());
        fecha.setValue("Fecha: " + listaEventos.get(0).getFecha().toString());
        lugar.setValue("Lugar: " + listaEventos.get(0).getLugar());
        precio.setValue("Precio: " + listaEventos.get(0).getPrecio().toString());

        todosEventos.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                int numEvento = Integer.parseInt(todosEventos.getValue().toString());
                titulo.setValue(listaEventos.get(numEvento).getTitulo());
                descripcion.setValue(listaEventos.get(numEvento).getDescripcion());
                artista.setValue("Artista: " + listaEventos.get(numEvento).getTitulo());
                fecha.setValue("Fecha: " + listaEventos.get(numEvento).getFecha().toString());
                lugar.setValue("Lugar: " + listaEventos.get(numEvento).getLugar());
                precio.setValue("Precio: " + listaEventos.get(numEvento).getPrecio().toString());
            }
        });

        //Drag and drop
        todosEventos.setDragMode(Table.TableDragMode.ROW);
        todosEventos.setDropHandler(new DropHandler() {

            @Override
            public void drop(DragAndDropEvent dropEvent) {
                DataBoundTransferable t = (DataBoundTransferable) dropEvent.getTransferable();
                Object sourceItemId = t.getItemId(); // returns our Bean

                if (t.getSourceComponent() == todosEventos) {
                    return;
                }

                Item item = misEventos.getItem(sourceItemId);
                Object o0 = item.getItemProperty("Título").getValue();
                Object o1 = item.getItemProperty("Artista").getValue();
                todosEventos.addItem(new Object[]{o0, o1}, sourceItemId);
                misEventos.removeItem(sourceItemId);

            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }
        });

        misEventos.setDragMode(Table.TableDragMode.ROW);
        misEventos.setDropHandler(new DropHandler() {

            @Override
            public void drop(DragAndDropEvent dropEvent) {
                DataBoundTransferable t = (DataBoundTransferable) dropEvent.getTransferable();
                Object sourceItemId = t.getItemId(); // returns our Bean

                if (t.getSourceComponent() == misEventos) {
                    return;
                }

                Item item = todosEventos.getItem(sourceItemId);
                Object o0 = item.getItemProperty("Título").getValue();
                Object o1 = item.getItemProperty("Artista").getValue();
                misEventos.addItem(new Object[]{o0, o1}, sourceItemId);
                todosEventos.removeItem(sourceItemId);
            }

            @Override
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }
        });

        guardar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Collection miListaEventos = misEventos.getItemIds();
                List<Evento> miListaDefinitiva = new ArrayList<>();
                for (Object miListaEvento : miListaEventos) {
                    miListaDefinitiva.add(listaEventos.get((int) miListaEvento));
                }
                usuarioController.addEventos(usuario.getCorreo(), miListaDefinitiva);
                Notification.show("Lista actualizada con éxito");
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
        layoutEventos.addComponent(todosEventos);
        eventos.setFirstComponent(layoutEventos);
        eventos.setSecondComponent(descripcionEvento);
        addComponent(eventos);
        addComponent(misEventos);
        addComponent(guardar);
        addComponent(new Label("Arrastra los eventos a los cuales quieres unirte"
                + " a tus eventos. Si quieres salirte devuelvos a todos los eventos."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
