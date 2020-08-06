package org.om.acme.frontend;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.om.acme.backend.model.Report;
import org.om.acme.backend.security.SecurityUtils;
import org.om.acme.backend.service.ReportService;
import org.om.acme.backend.service.UserService;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Bienvenid@")
public class MainView extends VerticalLayout {
    private Button report = new Button(("Reporte"));
    private ReportService serviceReport;
    private UserService serviceUser;

    public MainView(ReportService serviceReport, UserService serviceUser) {
        this.serviceReport = serviceReport;
        this.serviceUser = serviceUser;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        //setJustifyContentMode(JustifyContentMode.CENTER);

        add(new HorizontalLayout(new H1("Reporte Diario")),
                new H2("Tu ID es " + SecurityUtils.getUsername()),
                new H3("Reporte correspondiente al día"),
                //new Label(new SimpleDateFormat("dd' de 'MMMM' de 'yyyy").format(new Date())),
                //new Label(LocalDate.now().format(DateTimeFormatter.ofPattern(LocalDate.now().getDayOfWeek().name() + ", " + LocalDate.now().getDayOfMonth() + "'de '" + LocalDate.now().getMonth().name() + "'de ' yyyy"))),
                //new Label(LocalDate.now().format(DateTimeFormatter.ofPattern(LocalDate.now().toString()))),
                new Label(LocalDate.now().toString()),
                new HorizontalLayout(report)
        );

        report.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_LARGE);
        report.addClickListener(ev -> {
            VerticalLayout wrapper = new VerticalLayout();
            wrapper.setAlignItems(Alignment.CENTER);
            wrapper.setJustifyContentMode(JustifyContentMode.CENTER);
            Report newReport = new Report();
            newReport.setDate(LocalDate.now());
            newReport.setHour(Time.valueOf(LocalTime.now()));
            newReport.setUser(serviceUser.readUser(Long.valueOf(SecurityUtils.getUsername())));
            serviceReport.createReport(newReport);

            Dialog success = new Dialog();
            success.setCloseOnEsc(false);
            success.setCloseOnOutsideClick(false);

            wrapper.add(new H3("REPORTE EXITOSO"), new H4(LocalDate.now().toString()));
            wrapper.add(new H4("a las " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))));

            Button ok = new Button("Aceptar", e -> {
                success.close();
                report.setEnabled(false);
            });
            ok.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            wrapper.add(ok);

            success.add(wrapper);
            success.open();
        });
    }
}