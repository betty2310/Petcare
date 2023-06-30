package com.petcare.Controller;

import com.petcare.Services.OwnerService;
import com.petcare.Services.PetService;
import com.petcare.Services.ServiceService;
import com.petcare.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.petcare.Constants.DBConstants.*;
import static com.petcare.Constants.FXMLConstants.*;
import static com.petcare.Utils.Utils.toUpperFirstLetter;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane basePane;

    @FXML
    private Button buttonPet;

    @FXML
    private Button buttonService;

    @FXML
    private Button dashboardButton;

    @FXML
    private Text notifLabel;

    @FXML
    private Label numberPetLabel;

    @FXML
    private Label numberServiceLabel;

    @FXML
    private Label usernameLabel;


    //Save user role
    private static final Preferences userPreferences = Preferences.userRoot();
    public static final String userRole = userPreferences.get("role", "");
    public static final String userName = userPreferences.get("username", "");
    private final ViewUtils viewUtils = new ViewUtils();
    private Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

    public AdminController() throws SQLException {
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        viewUtils.changeScene(event, ADMIN_VIEW_FXML);
    }

    public void switchToPet() throws IOException {
        viewUtils.changeAnchorPane(basePane, PET_VIEW_FXML);

    }
    public void switchToService() throws IOException {
        viewUtils.changeAnchorPane(basePane, ADMIN_SERVICE_VIEW_FXML);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberPetLabel.setText("" + PetService.getNumberOfPetsByOwnerID(1));
        usernameLabel.setText("" + OwnerService.getNameFromID(1));
        numberServiceLabel.setText("" + ServiceService.getNumberOfServicesByOwnerID(1));
    }

}
