package vista;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
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
import com.vaadin.ui.VerticalLayout;
import controlador.UsuarioController;
import modelo.DAO.MongoDBJDBC;

public class RegisterView extends VerticalLayout implements View {

    public static final String NAME = "registro";
    private final Panel loginPanel = new Panel("Registrarse");
    private final FormLayout loginForm = new FormLayout();
    private final HorizontalLayout buttonLayout = new HorizontalLayout();
    private final TextField correo = new TextField("Correo");
    private final PasswordField password = new PasswordField("Contraseña");
    private final PasswordField passwordConf = new PasswordField("Confirmar contraseña");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final TextField nombreUsuario = new TextField("Nombre de usuario");
    private final Label cabecera = new Label(); //editar
    private final UsuarioController usuarioController = new UsuarioController();

    private final Button registrarse = new Button("Registrarse");
    private final Button volver = new Button("Volver");

    public RegisterView(final Navigator navigator) {

        registrarse.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event
            ) {
                //Guardar base datos
                try{
                usuarioController.crearNuevoUsuario(correo.getValue(), password.getValue(), nombre.getValue(), 
                        apellidos.getValue(), nombreUsuario.getValue());
                navigator.navigateTo(LoginView.NAME);
                } catch(Exception e){
                    Notification.show("Error registro", "Ya existe ese correo. Utilice otro correo para registrarse",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        volver.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                navigator.navigateTo(LoginView.NAME);
            }
        }
        );
        correo.addValidator(new EmailValidator("Introduzca un correo válido"));
        loginForm.addComponent(correo);
        loginForm.addComponent(password);
        loginForm.addComponent(passwordConf);
        loginForm.addComponent(nombreUsuario);
        loginForm.addComponent(nombre);
        loginForm.addComponent(apellidos);

        buttonLayout.addComponent(registrarse);
        buttonLayout.addComponent(volver);
        buttonLayout.setSizeFull();
        buttonLayout.setMargin(true);

        loginForm.addComponent(buttonLayout);
        loginForm.setMargin(true);

        loginPanel.setContent(loginForm);
        cabecera.setValue("<h1>Registrarte y conoce eventos</h1>");
        
        cabecera.setContentMode(ContentMode.HTML);
        addComponent(cabecera);
        addComponent(loginPanel);
        loginPanel.setWidth("50%");
        cabecera.setWidth("50%");
        setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setComponentAlignment(cabecera, Alignment.MIDDLE_CENTER);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
