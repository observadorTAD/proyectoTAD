package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class EditDataView extends FormLayout implements View {

    private final PasswordField password = new PasswordField("Contraseña");
    private final PasswordField newPassword = new PasswordField("Nueva contraseña");
    private final PasswordField passwordConf = new PasswordField("Confirmar contraseña");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final Button guardar = new Button("Guardar datos");

    public EditDataView() {
        guardar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                
            }
        });
        addComponent(password);
        addComponent(newPassword);
        addComponent(passwordConf);
        addComponent(nombre);
        addComponent(apellidos);
        addComponent(guardar);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
