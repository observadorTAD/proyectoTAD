package vista;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginView extends FormLayout implements View {

    public static final String NAME = "";
    final TextField correo = new TextField("Correo");
    final PasswordField password = new PasswordField("Contraseña");

    public LoginView(final Navigator navigator) {
        Button button = new Button("Registrarse",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        navigator.navigateTo(RegisterView.NAME);
                    }
                });
        addComponent(correo);
        addComponent(password);
        addComponent(button);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Login");
    }
}
