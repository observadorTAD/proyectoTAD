package vista;

import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.grupo1.myevent.MyAppWidgetset")
@PreserveOnRefresh
public class MyEventUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        try {
            VaadinSession.getCurrent().getLockInstance().lock();
            VaadinSession.getCurrent().setAttribute("correo", new Label("out"));
            VaadinSession.getCurrent().setAttribute("artista", new Label("false"));
        } finally {
            // safely unlock the session in a finally block
            VaadinSession.getCurrent().getLockInstance().unlock();
        }
        Navigator navigator = new Navigator(this, this);
        navigator.addView(LoginView.NAME, new LoginView());
        navigator.addView(RegisterView.NAME, new RegisterView());
        navigator.addView(MainView.NAME, new MainView());
        navigator.addView(MainAdminView.NAME, new MainAdminView());
    }

    @WebServlet(value = {"/myevent/*", "/*"}, name = "MyEventServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyEventUI.class, productionMode = false, heartbeatInterval = 10, closeIdleSessions = true)
    public static class MyEventServlet extends VaadinServlet {
    }
}
