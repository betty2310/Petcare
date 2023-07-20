package com.petcare.Controller;

import com.petcare.Controller.Pet.PetController;
import com.petcare.Services.OwnerService;
import com.petcare.Services.PetService;
import com.petcare.Services.ServiceService;
import com.petcare.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private Label numberOwner;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button buttonMedicalAppointment;
    @FXML
    private VBox ownerBox;
    @FXML
    private TabPane charTab;

    @FXML
    private Button buttonStatistics;
    @FXML
    private PieChart stageChart;

    @FXML
    private PieChart typeChart;
    @FXML
    private HBox statisticHbox;
    @FXML
    private HBox medicalHbox;

    //Save user role
    private final ViewUtils viewUtils = new ViewUtils();
    private Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

    public AdminController() throws SQLException {
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        viewUtils.changeScene(event, ADMIN_VIEW_FXML);
    }

    public void switchToPet() throws IOException {
        Preferences pre = Preferences.userRoot();
        String role = pre.get("role", "");
        if (role.equals("chunuoi")) {
            viewUtils.changeAnchorPane(basePane, PET_VIEW_FXML);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PET_VIEW_FXML));
            Node node = fxmlLoader.load();
            basePane.getChildren().setAll(node);
            PetController petController = fxmlLoader.getController();
            petController.setOwnerID(Integer.parseInt(pre.get("id", "")));
            petController.petGrid();
        }
        if (role.equals("admin")) {
            viewUtils.changeAnchorPane(basePane, OWNER_VIEW_FXML);

        }


    }

    public void switchToService() throws IOException {
        Preferences pre = Preferences.userRoot();
        String role = pre.get("role", "");
        if (role.equals("admin")) {
            viewUtils.changeAnchorPane(basePane, ADMIN_SERVICE_VIEW_FXML);
        } else {
            viewUtils.changeAnchorPane(basePane, SERVICE_VIEW_FXML);
        }
    }


    public void switchToMedical() throws IOException {
        viewUtils.changeAnchorPane(basePane, MEDICAL_APPOINTMENT_VIEW_FXML);
    }

    public void switchToStatistics() throws IOException {
        viewUtils.changeAnchorPane(basePane, STATISTICS_VIEW_FXML);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        viewUtils.changeScene(event, HOME_VIEW_FXML);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Preferences pre = Preferences.userRoot();
        String role = pre.get("role", "");
        if (role.equals("chunuoi")) {
            int id = Integer.parseInt(pre.get("id", ""));
            numberPetLabel.setText("" + PetService.getNumberOfPetsByOwnerID(id));
            usernameLabel.setText("" + OwnerService.getNameFromID(id));
            numberServiceLabel.setText("" + ServiceService.getNumberOfServicesByOwnerID(id));
            ownerBox.setVisible(false);
            charTab.setVisible(false);
        }
        if (role.equals("admin")) {
            numberPetLabel.setText("" + PetService.getNumberOfPets());
            numberServiceLabel.setText("" + ServiceService.getNumberOfServices());
            numberOwner.setText("" + OwnerService.getOwner().size());
            statisticHbox.setVisible(true);
            medicalHbox.setVisible(true);
            buttonPet.setText("Chủ nuôi");
            createPieChart();
        }
    }

    public void createPieChart() {
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Khám bệnh", ServiceService.getNumberOfServicesByType("Khám bệnh")),
                        new PieChart.Data("làm đẹp", ServiceService.getNumberOfServicesByType("làm đẹp")),
                        new PieChart.Data("lưu trữ", ServiceService.getNumberOfServicesByType("lưu trữ"))
                );
        typeChart.setData(pieChartData);
        typeChart.setTitle("Theo loại dịch vụ");

        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("done", ServiceService.getNumberOfServicesByState("done")),
                new PieChart.Data("not start", ServiceService.getNumberOfServicesByState("not start")),
                new PieChart.Data("in progress", ServiceService.getNumberOfServicesByState("in progress"))
        );
        stageChart.setData(data);
        stageChart.setTitle("Theo trạng thái");
    }

}
