package org.om.acme.frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.om.acme.frontend.admin.ListView;
import org.om.acme.frontend.admin.SignupView;

@CssImport("./styles/shared.css")
public class MainLayout extends AppLayout {

    public  MainLayout() {
        createHeader();
    }

    private void createHeader() {
        H1 logo = new H1("REPORTE");
        logo.addClassName("logo");

        RouterLink main = new RouterLink("Inicio", MainView.class);
        main.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink list = new RouterLink("Listado", ListView.class);
        RouterLink signup = new RouterLink("Registro", SignupView.class);
        Anchor logout = new Anchor("/logout", "Salir");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, main, list, signup, logout);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");
        header.addClassName("header");

        addToNavbar(header);
    }
}