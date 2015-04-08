package vista;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class RegisterView extends VerticalLayout implements View {

    public static final String NAME = "registro";

    public RegisterView(final Navigator navigator) {
        setSizeFull();
        Button button = new Button("Go to Main View",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        navigator.navigateTo(LoginView.NAME);
                    }
                });
        addComponent(button);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Registro");
    }
}
