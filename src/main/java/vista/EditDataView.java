package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import controlador.UsuarioController;
import modelo.entidades.Usuario;

public class EditDataView extends FormLayout implements View {

    private UsuarioController usuarioController = new UsuarioController();
    private final PasswordField password = new PasswordField("Contraseña Actual");
    private final PasswordField newPassword = new PasswordField("Nueva contraseña");
    private final PasswordField passwordConf = new PasswordField("Confirmar contraseña");
    private final TextField nombreUsuario = new TextField("Nombre de usuario");
    private final TextField nombre = new TextField("Nombre");
    private final TextField apellidos = new TextField("Apellidos");
    private final Button guardar = new Button("Guardar datos");

    public EditDataView(final Usuario usuario) {
        guardar.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (usuarioController.isUser(usuario.getCorreo(), password.getValue())) {
                    if ((newPassword.getValue()).equals(passwordConf.getValue())) {
                        String aux = usuario.getPassword();
                        if (!newPassword.getValue().equals("")) {
                            aux = newPassword.getValue();
                        }
                        usuarioController.updateUsuario(usuario.getCorreo(), aux, nombreUsuario.getValue(),
                                nombre.getValue(), apellidos.getValue());
                        Notification.show("Se han modificado los datos con éxito",
                                Notification.Type.HUMANIZED_MESSAGE);
                    } else {
                        Notification.show("La nueva contraseña no coincide",
                                Notification.Type.ERROR_MESSAGE);
                    }
                } else {
                    Notification.show("Contraseña incorrecta",
                            Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        nombreUsuario.setValue(usuario.getNombreUsuario());
        nombre.setValue(usuario.getNombre());
        apellidos.setValue(usuario.getApellidos());
        addComponent(password);
        addComponent(newPassword);
        addComponent(passwordConf);
        addComponent(nombreUsuario);
        addComponent(nombre);
        addComponent(apellidos);
        addComponent(guardar);
        addComponent(new Label("Si no deseaa modificar la contraseña deje los campos "
                + "nueva contraseña y confirmar contraseña en blanco."));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
