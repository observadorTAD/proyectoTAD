package vista;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
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
 * Vista para registrar nuevos usuarios.
 * @author racede
 */
public class RegisterView extends VerticalLayout implements View {

    /**
     * Parámetro para el navigator de vaadin.
     */
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
    private final ArtistaController artistaController = new ArtistaController();
    private final CheckBox artista = new CheckBox("Soy artista");

    private final Button registrarse = new Button("Registrarse");
    private final Button volver = new Button("Volver");

    /**
     * Constructor de la vista.
     */
    public RegisterView() {

        registrarse.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event
            ) {
                //Guardar base datos
                try {
                    if (correo.isValid() && password.getValue().equals(passwordConf.getValue())) {
                        if (artista.getValue() == true) {
                            if (usuarioController.getUsuario(correo.getValue()) == null) {
                                artistaController.crearNuevoArtista(correo.getValue(), password.getValue(), nombre.getValue(),
                                        apellidos.getValue(), nombreUsuario.getValue());
                            } else {
                                throw new Exception();
                            }
                        } else {
                            if (!artistaController.isArtista(correo.getValue())) {
                                usuarioController.crearNuevoUsuario(correo.getValue(), password.getValue(), nombre.getValue(),
                                        apellidos.getValue(), nombreUsuario.getValue());
                            } else {
                                throw new Exception();
                            }
                        }
                        UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
                        Notification.show("Se ha registrado con éxito",
                                Notification.Type.HUMANIZED_MESSAGE);
                    } else {
                        Notification.show("Campos inválidos", "Compruebe si el correo es válido o si las contraseñas coinciden.",
                                Notification.Type.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    Notification.show("Error de registro", "Ya existe ese correo. "
                            + "Utilice otro correo para registrarse",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        volver.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
            }
        }
        );
        correo.addValidator(new EmailValidator("Introduzca un correo"));
        correo.setImmediate(true);
        correo.setRequired(true);
        password.setRequired(true);
        passwordConf.setRequired(true);
        nombreUsuario.setRequired(true);

        loginForm.addComponent(correo);
        loginForm.addComponent(password);
        loginForm.addComponent(passwordConf);
        loginForm.addComponent(nombreUsuario);
        loginForm.addComponent(nombre);
        loginForm.addComponent(apellidos);
        loginForm.addComponent(artista);
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
