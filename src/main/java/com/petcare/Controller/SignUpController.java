package com.petcare.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.petcare.Constants.DBConstants.*;
import static com.petcare.Utils.Utils.hashPassword;
import static com.petcare.Utils.Utils.createDialog;

public class SignUpController implements Initializable {
    @FXML
    private TextField signUpUsername, signUpPassword, ReSignUpPassword;
    private final ToggleGroup toggleRole = new ToggleGroup();

    public void handleSignUp() {
        String inputUsername = signUpUsername.getText();
        String inputPassword = signUpPassword.getText();
        String reInputPassword = ReSignUpPassword.getText();
        String role = "";

        if (inputUsername.trim().equals("") || inputPassword.trim().equals("") || reInputPassword.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Khoan nào cán bộ",
                    "", "Vui lòng nhập đủ tài khoản và mật khẩu!"
            );

        } else {
            String CREATE_QUERY = "INSERT INTO user (username, password, role, userId) VALUES (?,?,?,?)";
            try {
                Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY);
                preparedStatement.setString(1, inputUsername);
                preparedStatement.setString(2, inputPassword);
                preparedStatement.setString(3, "chunuoi");
                preparedStatement.setInt(4, 10000);
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    signUpPassword.clear();
                    signUpUsername.clear();
                    createDialog(
                            Alert.AlertType.CONFIRMATION,
                            "Thành công",
                            "", "Đăng ký người dùng mới thành công!"
                    );
                } else {
                    createDialog(
                            Alert.AlertType.ERROR,
                            "Thất bại",
                            "", "Đăng ký người dùng mới thất bại!"
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
