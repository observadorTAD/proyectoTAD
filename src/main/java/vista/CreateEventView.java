package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

public class CreateEventView extends FormLayout implements View {

    private final TextField titulo = new TextField("Título del evento");
    private final TextArea descripcion = new TextArea("Descripción del evento");
    private final TextField lugar = new TextField("Lugar del evento");
    private final TextField precio = new TextField("Precio del evento"); //Validar
    private final DateField fecha = new DateField("Fecha del evento");
    private final Button crear = new Button("Crear Evento");

    public CreateEventView() {
        boolean artista = true;
        if (artista) {
            crear.addClickListener(new Button.ClickListener() {

                @Override
                public void buttonClick(Button.ClickEvent event) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            addComponent(titulo);
            addComponent(lugar);
            addComponent(fecha);
            addComponent(precio);
            addComponent(descripcion);
            addComponent(crear);
        } else {
            Notification.show("Necesita ser artista para crear un evento", Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("CrearEvento");
    }
}
