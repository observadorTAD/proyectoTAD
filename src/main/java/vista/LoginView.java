package vista;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.grupo1.eventprueba.MyAppWidgetset")
public class LoginView extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final FormLayout loginLayout = new FormLayout();
        loginLayout.setMargin(true);
        setContent(loginLayout);
        
        final TextField correo = new TextField("Correo");
        final PasswordField password = new PasswordField("Contraseña");

        Button login = new Button("Login");
        login.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                loginLayout.addComponent(new Label("Thank you for clicking"));
            }
        });
        Button prueba = new Button("Prueba");
        prueba.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
            }
        });
        loginLayout.addComponent(correo);
        loginLayout.addComponent(password);
        loginLayout.addComponent(login);
        loginLayout.addComponent(prueba);

    }

    @WebServlet(value = {"/login/*","/*"}, name = "login", asyncSupported = true)
    @VaadinServletConfiguration(ui = LoginView.class, productionMode = false)
    public static class LoginServlet extends VaadinServlet {
    }
}
