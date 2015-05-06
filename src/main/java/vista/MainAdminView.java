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

public class MainAdminView extends VerticalLayout implements View {

    public static final String NAME = "mainAdmin";
    private static final HorizontalSplitPanel menuContent = new HorizontalSplitPanel();
    private final Accordion nav = new Accordion();

    public MainAdminView() {
        nav.addSelectedTabChangeListener(
                new TabSheet.SelectedTabChangeListener() {
                    @Override
                    public void selectedTabChange(SelectedTabChangeEvent event) {
                        TabSheet tabsheet = event.getTabSheet();
                        Layout tab = (Layout) tabsheet.getSelectedTab();
                        String caption = tabsheet.getTab(tab).getCaption();
                        switch (caption) {
                            case "Usuarios":
                                menuContent.setSecondComponent(new AdminUsersView());
                                break;
                            case "Artistas":
                                menuContent.setSecondComponent(new AdminArtistView());
                                break;
                            case "Eventos":
                                menuContent.setSecondComponent(new AdminEventView());
                                break;
                            case "Gráficos":
                                menuContent.setSecondComponent(new AdminGraphView());
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

        nav.addTab(new VerticalLayout(), "Usuarios");
        nav.addTab(new VerticalLayout(), "Artistas");
        nav.addTab(new VerticalLayout(), "Eventos");
        nav.addTab(new VerticalLayout(), "Gráficos");
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
        if (!aux.getValue().equals("admin")) {
            UI.getCurrent().getNavigator().navigateTo(LoginView.NAME);
        } else {
            menuContent.setSecondComponent(new AdminUsersView());
        }
    }

    public static void refresh(String vista) {
        switch (vista) {
            case "Usuarios":
                menuContent.setSecondComponent(new AdminUsersView());
                break;
            case "Artistas":
                menuContent.setSecondComponent(new AdminArtistView());
                break;
            case "Eventos":
                menuContent.setSecondComponent(new AdminEventView());
                break;
        }
    }
}
