package vista;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.UI;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.grupo1.myevent.MyAppWidgetset")
public class MyEventUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Navigator navigator = new Navigator(this, this);
        navigator.addView(LoginView.NAME, new LoginView(navigator));
        navigator.addView(RegisterView.NAME, new RegisterView(navigator));
        navigator.addView(MainView.NAME, new MainView(navigator));
        WrappedSession session = getSession().getSession();

    }
    @WebServlet(value = {"/myevent/*","/*"}, name = "MyEventServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyEventUI.class, productionMode = false,heartbeatInterval=10,closeIdleSessions=true)
    public static class MyEventServlet extends VaadinServlet {
    }
}
