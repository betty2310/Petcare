package com.petcare.Controller;
//import libs
import com.petcare.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.util.prefs.Preferences;

//import constants
import static com.petcare.Constants.DBConstants.*;
import static com.petcare.Utils.Utils.createDialog;
import static com.petcare.Constants.FXMLConstants.ADMIN_VIEW_FXML;

public class HomeController {
    @FXML
    private TextField inputUsername, inputPassword;

    public void handleLogin(ActionEvent event) {
        String SELECT_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
        String Username = inputUsername.getText();
        String Password = inputPassword.getText();
        if (Username.trim().equals("") || Password.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Cảnh báo!",
                    "Khoan nào bro!",
                    "Vui lòng nhập đầy đủ username và password!"
            );
        }   else {
            try {
                //Khai bao ket noi sql
                Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
                preparedStatement.setString(1, Username);
                preparedStatement.setString(2, Password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    Preferences userPreferences = Preferences.userRoot();
                    userPreferences.put("role", result.getString(4));
                    userPreferences.put("username", result.getString(2));
                    userPreferences.put("id", result.getString(1));

                    ViewUtils viewUtils = new ViewUtils();
                    viewUtils.changeScene(event, ADMIN_VIEW_FXML);
                }   else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Cảnh báo!",
                            "Khoan nào bro!",
                            "Sai username hoặc password!"
                    );
                }
            }   catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}