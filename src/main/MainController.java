package main;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainController {

    public Connection connection = PgConnection.getConnection();
    public TextField usernameField;
    public PasswordField passwordField;
    public Label userInformation;
    public Label userInformationTitle;

    public MainController() throws SQLException {
    }

    public void goToHome() throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        StringBuilder information = new StringBuilder();
        userInformationTitle.setVisible(false);

        if(username != null && password != null){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.users WHERE username = '" + username + "' AND password = '" + password + "'");

            while (resultSet.next()) {
                information.append(String.format("Nombre: %s %nApellido: %s%n%n", resultSet.getString("name"), resultSet.getString("lastname")));
            }

            if (information.toString().equals("")){
                information.append("No hay información del usuario");
            }else {
                userInformationTitle.setVisible(true);
            }
            userInformation.setText(information.toString());
        }
    }
}
