package vista;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View {

    public static final String NAME = "";
    private final Panel loginPanel = new Panel("Login");
    private final FormLayout loginForm = new FormLayout();
    private final HorizontalLayout buttonLayout = new HorizontalLayout();
    private final TextField correo = new TextField("Correo");
    private final PasswordField password = new PasswordField("Contraseña");
    private final Button registrarse = new Button("Registrarse");
    private final Button loginButton = new Button("Log In");

    public LoginView(final Navigator navigator) {

        registrarse.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event
            ) {
                navigator.navigateTo(RegisterView.NAME);
            }
        });
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                
                navigator.navigateTo(MainView.NAME);
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
