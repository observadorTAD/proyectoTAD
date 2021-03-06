package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import controlador.ArtistaController;
import controlador.UsuarioController;

/**
 * Vista de login.
 * @author racede
 */
public class LoginView extends VerticalLayout implements View {

    /**
     * Parámetro para el nombre de la vista en el navigator de vaadin. Es la 
     * vista por defecto
     */
    public static final String NAME = "";
    private final Panel loginPanel = new Panel("Login");
    private final FormLayout loginForm = new FormLayout();
    private final HorizontalLayout buttonLayout = new HorizontalLayout();
    private final TextField correo = new TextField("Correo");
    private final PasswordField password = new PasswordField("Contraseña");
    private final Button registrarse = new Button("Registrarse");
    private final Button loginButton = new Button("Log In");
    private final UsuarioController usuarioController = new UsuarioController();
    private final ArtistaController artistaController = new ArtistaController();

    /**
     * Constructor de la vista.
     */
    public LoginView() {

        registrarse.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event
            ) {
                UI.getCurrent().getNavigator().navigateTo(RegisterView.NAME);
            }
        });
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                if (usuarioController.loginAdmin(correo.getValue(), password.getValue())) {
                    Label aux = (Label) VaadinSession.getCurrent().getAttribute("correo");
                    aux.setValue("admin");

                    UI.getCurrent().getNavigator().navigateTo(MainAdminView.NAME);

                } else if (artistaController.login(correo.getValue(), password.getValue())) {
                    Label aux = (Label) VaadinSession.getCurrent().getAttribute("correo");
                    aux.setValue(correo.getValue());
                    Label aux2 = (Label) VaadinSession.getCurrent().getAttribute("artista");
                    aux2.setValue("true");

                    UI.getCurrent().getNavigator().navigateTo(MainView.NAME);
                } else if (usuarioController.login(correo.getValue(), password.getValue())) {
                    Label aux = (Label) VaadinSession.getCurrent().getAttribute("correo");
                    aux.setValue(correo.getValue());
                    Label aux2 = (Label) VaadinSession.getCurrent().getAttribute("artista");
                    aux2.setValue("false");

                    UI.getCurrent().getNavigator().navigateTo(MainView.NAME);

                } else {
                    Notification.show("Correo/contraseña no coinciden", Notification.Type.ERROR_MESSAGE);
                }
            }
        }
        );
        loginForm.addComponent(correo);
        loginForm.addComponent(password);

        buttonLayout.addComponent(loginButton);
        buttonLayout.addComponent(registrarse);
        buttonLayout.setSizeFull();
        buttonLayout.setMargin(true);

        loginForm.addComponent(buttonLayout);
        loginForm.setMargin(true);

        loginPanel.setContent(loginForm);
        addComponent(loginPanel);
        setSizeFull();
        loginPanel.setWidth("50%");
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
