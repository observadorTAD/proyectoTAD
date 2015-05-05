package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import controlador.ArtistaController;
import controlador.IPersonaController;
import controlador.UsuarioController;
import modelo.entidades.Artista;
import modelo.entidades.Persona;

public class EditDataView extends FormLayout implements View {

    private IPersonaController controller;
    private final PasswordField password = new PasswordField("Contraseña Actual");
    private final Button confirmarEditar = new Button("Confirmar");
    private final PasswordField newPassword = new PasswordField("Nueva contraseña");
    private final PasswordField passwordConf = new PasswordField("Confirmar contraseña");
    private TextField nombreUsuario;
    private TextArea descripcion = new TextArea("Descrpición");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final Button guardar = new Button("Guardar datos");
    private final Window subWindow = new Window("Confirmar contraseña");
    private final FormLayout subContent = new FormLayout();
    private final Button eliminarCuenta = new Button("Eliminar Cuenta");
    private final Button confirmarBorrar = new Button("Confirmar");

    public EditDataView(final Persona usuario, boolean artista) {
        if (artista) {
            controller = new ArtistaController();
            nombreUsuario = new TextField("Nombre artistico");
            descripcion.setValue(((Artista)usuario).getDescripcion());
        } else {
            controller = new UsuarioController();
            nombreUsuario = new TextField("Nombre de usuario");
        }
        confirmarBorrar.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (controller.login(usuario.getCorreo(), password.getValue())) {
                    controller.removeUsuario(usuario.getCorreo());
                    Notification.show("Se ha eliminado la cuenta con éxito",
                            Notification.Type.HUMANIZED_MESSAGE);
                    subWindow.close();
                    UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);

                } else {
                    Notification.show("Contraseña incorrecta",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        confirmarEditar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (controller.login(usuario.getCorreo(), password.getValue())) {
                    String aux = usuario.getPassword();
                    if (!newPassword.getValue().equals("")) {
                        aux = newPassword.getValue();
                    }
                    controller.updateUsuario(usuario.getCorreo(), aux, nombreUsuario.getValue(),
                            nombre.getValue(), apellidos.getValue(), descripcion.getValue());
                    Notification.show("Se han modificado los datos con éxito",
                            Notification.Type.HUMANIZED_MESSAGE);
                    subWindow.close();

                } else {
                    Notification.show("Contraseña incorrecta",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        guardar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if ((newPassword.getValue()).equals(passwordConf.getValue())) {
                    subContent.removeComponent(confirmarBorrar);
                    subContent.addComponent(confirmarEditar);
                    UI.getCurrent().addWindow(subWindow);
                } else {
                    Notification.show("La nueva contraseña no coincide",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        eliminarCuenta.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                subContent.removeComponent(confirmarEditar);
                subContent.addComponent(confirmarBorrar);
                UI.getCurrent().addWindow(subWindow);
                Notification.show("Esto eliminará la cuenta de forma definitiva.",
                        Notification.Type.WARNING_MESSAGE);

            }
        });

        subContent.setMargin(true);
        subWindow.setContent(subContent);

        // Put some components in it
        subContent.addComponent(password);

        // Center it in the browser window
        subWindow.center();
        nombreUsuario.setValue(usuario.getNombreUsuario());
        nombre.setValue(usuario.getNombre());
        apellidos.setValue(usuario.getApellidos());
        addComponent(nombreUsuario);
        if (artista) {
            addComponent(descripcion);
        }
        addComponent(nombre);
        addComponent(apellidos);
        addComponent(newPassword);
        addComponent(passwordConf);

        HorizontalLayout botones = new HorizontalLayout();
        botones.addComponent(guardar);
        botones.addComponent(eliminarCuenta);
        addComponent(botones);
        addComponent(new Label("Si no deseaa modificar la contraseña deje los campos "
                + "nueva contraseña y confirmar contraseña en blanco."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
