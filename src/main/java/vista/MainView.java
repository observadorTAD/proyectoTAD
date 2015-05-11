package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import controlador.ArtistaController;
import controlador.IPersonaController;
import controlador.UsuarioController;
import modelo.entidades.Persona;
import modelo.entidades.Usuario;

/**
 *
 * @author racede
 */
public class MainView extends VerticalLayout implements View {

    /**
     *
     */
    public static final String NAME = "main";
    private final HorizontalSplitPanel menuContent = new HorizontalSplitPanel();
    private final Accordion nav = new Accordion();
    private boolean artista;
    private Persona usuario = new Usuario(NAME, null, NAME, NAME, NAME, NAME);
    private IPersonaController controller;

    /**
     *
     */
    public MainView() {
        nav.addSelectedTabChangeListener(
                new TabSheet.SelectedTabChangeListener() {
                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        TabSheet tabsheet = event.getTabSheet();
                        Layout tab = (Layout) tabsheet.getSelectedTab();
                        String caption = tabsheet.getTab(tab).getCaption();
                        switch (caption) {
                            case "Principal":
                                menuContent.setSecondComponent(new PrincipalUserView(usuario));
                                break;
                            case "Buscar eventos":
                                menuContent.setSecondComponent(new AddEventView(usuario));
                                break;
                            case "Crear evento":
                                menuContent.setSecondComponent(new CreateEventView(usuario, artista));
                                break;
                            case "Editar datos":
                                menuContent.setSecondComponent(new EditDataView(usuario, artista));
                                break;
                            case "Logout":
                                Label aux = (Label) VaadinSession.getCurrent().getAttribute("correo");
                                aux.setValue("out");
                                UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
                                break;
                            default:
                        }
                    }
                });

        nav.addTab(new VerticalLayout(), "Principal");
        nav.addTab(new VerticalLayout(), "Buscar eventos");
        nav.addTab(new VerticalLayout(), "Crear evento");
        nav.addTab(new VerticalLayout(), "Editar datos");
        nav.addTab(new VerticalLayout(), "Logout");

        menuContent.setFirstComponent(nav);
        menuContent.setLocked(true);
        menuContent.setSplitPosition(15);

        addComponent(new Label("<h1>MyEvent</h1>", ContentMode.HTML));
        addComponent(menuContent);
        addComponent(new Label("<h3>MyEvent. Grupo 1 TAD. Universidad Pablo de "
                + "Olvaide</h3>", ContentMode.HTML));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Label aux = (Label) VaadinSession.getCurrent().getAttribute("correo");
        if (aux.getValue().equals("out")) {
            UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
        } else {
            Label aux2 = (Label) VaadinSession.getCurrent().getAttribute("artista");
            if (aux2.getValue().equals("true")) {
                controller = new ArtistaController();
            } else {
                controller = new UsuarioController();
            }
            usuario = controller.getUsuario(aux.getValue());
            artista = aux2.getValue().equals("true");
            menuContent.setSecondComponent(new PrincipalUserView(usuario));
        }
    }
}
