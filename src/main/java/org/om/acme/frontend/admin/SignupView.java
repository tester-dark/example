package org.om.acme.frontend.admin;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.om.acme.backend.model.User;
import org.om.acme.backend.service.UserService;
import org.om.acme.frontend.MainLayout;

@Route(value = "admin/signup", layout = MainLayout.class)
@PageTitle("Registro")
public class SignupView extends VerticalLayout {
    private TextField username = new TextField("ID-Usuario");
    private PasswordField password = new PasswordField("Contraseña");
    private Checkbox isAdmin = new Checkbox("Administador");
    private Button accept = new Button("Aceptar");
    private UserService service;

    public SignupView(UserService service) {
        this.service = service;
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        accept.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        accept.addClickListener(ev -> {
            User user = new User();
            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            user.setAdmin(isAdmin.getValue());
            service.createUser(user);
            Notification.show("Usuario registrado con éxito.", 1700, Notification.Position.MIDDLE);
            username.clear();
            password.clear();
            isAdmin.clear();
            username.focus();
        });

        add(new H1("Registro de Usuarios"), new Html("<hr>"), username, password, isAdmin,
                new Html("<hr>"), accept);
    }
}