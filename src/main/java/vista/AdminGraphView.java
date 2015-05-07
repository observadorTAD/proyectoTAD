/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import controlador.ArtistaController;
import controlador.EventoController;
import controlador.UsuarioController;

/**
 *
 * @author racede
 */
public class AdminGraphView extends HorizontalLayout implements View {
private final UsuarioController usuarioController = new UsuarioController();
private final ArtistaController artistaController = new ArtistaController();
private final EventoController eventoController = new EventoController();
    public AdminGraphView() {
        setMargin(true);

        int users = usuarioController.countUsuarios();
        int artistas = artistaController.countArtitas();
        int eventos = eventoController.countEventos();

        Chart chart = new Chart(ChartType.PIE);
        chart.setWidth("400px"); // 100% by default
        chart.setHeight("300px"); // 400px by default
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Datos absolutos");
        addComponent(chart);
        DataSeries items = new DataSeries();
        items.add(new DataSeriesItem("Usuarios", (Number) users));
        items.add(new DataSeriesItem("Artistas", (Number) artistas));
        items.add(new DataSeriesItem("Eventos", (Number) eventos));
        conf.addSeries(items);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
