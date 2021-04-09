package main;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

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
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");

            /*
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            */

            while (resultSet.next()) {
                information.append(String.format(
                        "Nombre: %s %nApellido: %s%n%n", resultSet.getString("name"),
                        resultSet.getString("lastname")));
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
