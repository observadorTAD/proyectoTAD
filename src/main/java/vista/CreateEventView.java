package vista;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import controlador.ArtistaController;
import controlador.EventoController;
import modelo.entidades.Usuario;

public class CreateEventView extends FormLayout implements View {

    private final TextField titulo = new TextField("Título del evento");
    private final TextArea descripcion = new TextArea("Descripción del evento");
    private final TextField lugar = new TextField("Lugar del evento");
    private final TextField precio = new TextField("Precio del evento"); //Validar
    private final DateField fecha = new DateField("Fecha del evento");
    private final Button crear = new Button("Crear Evento");
    private EventoController eventoController = new EventoController();
    private ArtistaController artistaController = new ArtistaController();

    public CreateEventView(final Usuario usuario) {
        boolean artista = artistaController.isArtista(usuario.getNombre());
        if (artista) {
            crear.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    if (!fecha.isEmpty() && !titulo.isEmpty() && !lugar.isEmpty() && isNumeric(precio.getValue())) {
                        String artista = usuario.getNombre();
                        //Debe ser el nombreArtistico del artista
                        String artista = "holita";
                        eventoController.crearEvento(titulo.getValue(), lugar.getValue(), fecha.getValue(), precio.getValue(), descripcion.getValue(), artista);
                        UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
                        Notification.show("¡Evento creado!", "Ha creado el evento con exito", Notification.Type.HUMANIZED_MESSAGE);
                    } else {
                        Notification.show("ERROR", "Revise los datos ingresados", Notification.Type.ERROR_MESSAGE);
                    }
                }
            });
            titulo.setRequired(true);
            addComponent(titulo);
            lugar.setRequired(true);
            addComponent(lugar);
            fecha.setRequired(true);
            addComponent(fecha);
            addComponent(precio);
            addComponent(descripcion);
            addComponent(crear);
        } else {
            Notification.show("ERROR", "Necesita ser artista para crear un evento", Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("CrearEvento");
    }

    private boolean isNumeric(String num) {
        boolean temp = true;
        int i = 0;
        boolean simbolo = false;
        while (temp && i < num.length()) {
            if (num.charAt(i) == '.') {
                if (!simbolo) {
                    simbolo = true;
                } else {
                    temp = false;
                }
            }
            if ((num.charAt(i) < '0' && num.charAt(i) != '.') || (num.charAt(i) > '9' && num.charAt(i) != '.')) {
                temp = false;
            }
            i++;
        }
        return temp;
    }
}
