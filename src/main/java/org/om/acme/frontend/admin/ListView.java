package org.om.acme.frontend.admin;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.om.acme.backend.model.Report;
import org.om.acme.backend.service.ReportService;
import org.om.acme.frontend.MainLayout;
import org.springframework.context.annotation.Role;
import org.springframework.security.access.annotation.Secured;

import java.time.LocalDate;
import java.util.List;

@Route(value = "admin/list", layout = MainLayout.class)
@PageTitle("Listado")
@Secured("admin")
public class ListView extends VerticalLayout {
    private DatePicker date = new DatePicker("Fecha");
    private Button search = new Button("Obtener");
    private Button generate = new Button("Generar");
    private Grid<Report> reports = new Grid<>();
    private ReportService serviceReport;

    public ListView() {
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        date.addValueChangeListener(ev -> search.setEnabled(date.getValue() == null ? false : true));

        search.setIcon(VaadinIcon.GRID.create());
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        search.setEnabled(false);
        search.addClickListener(ev -> {
            try {
                List<Report> list = serviceReport.listReport(LocalDate.of(date.getValue().getYear(), date.getValue().getMonth(), date.getValue().getDayOfMonth()));
                reports.setItems(list);
            } catch (Exception ex){}
        });

        generate.setIcon(VaadinIcon.LIST.create());
        generate.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        generate.setEnabled(false);

        HorizontalLayout searchBar = new HorizontalLayout( date, search, generate);
        searchBar.setJustifyContentMode(JustifyContentMode.END);
        searchBar.setSpacing(false);
        add(searchBar, new Html("<hr>"));

        createGrid();
    }

    private void createGrid() {
        reports.addColumn(Report::getIdReport).setHeader("# ID");
        reports.addColumn(Report::getUser).setHeader("USUARIO");
        reports.addColumn(r -> r.getDate()).setHeader("FECHA");
        reports.addColumn(Report::getHour).setHeader("HORA");

        reports.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.MATERIAL_COLUMN_DIVIDERS);

        add(new H1("Listado de Reportes del día"),
                new H3(date.getValue() == null ? "" : String.valueOf(date.getValue())), reports);
    }
}