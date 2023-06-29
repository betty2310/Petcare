package com.petcare.Controller.Service;

import com.petcare.Controller.Pet.AddPetController;
import com.petcare.HomeApplication;
import com.petcare.Model.Service;
import com.petcare.Services.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.petcare.Constants.FXMLConstants.PET_ADD_VIEW_FXML;

public class AdminServiceController implements Initializable {
    @FXML
    private Button add_service_id;

    @FXML
    private AnchorPane basePane;
    @FXML
    private TableColumn<Service, String> idColumn, dateColumn, typeColumn, endColumn, priceColumn, startColumn, stateColumn;

    @FXML
    private GridPane gridPet;

    @FXML
    private TableView<Service> table;

    @FXML
    void bookService(ActionEvent event) {

    }

    @FXML
    void add(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_ADD_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            AddPetController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add new Service!");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet rs = ServiceService.getServices();

        List<Service> serviceList = new ArrayList<>();

        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String price = rs.getString("Price");
                Date date = rs.getDate("Date");
                String state = rs.getString("State");
                String type = rs.getString("Type");
                Date startTime = rs.getDate("Start_Time");
                Date endTime = rs.getDate("End_Time");
                int ownerId = rs.getInt("Owner_ID");

                // Create Service object
                Service service = new Service(id, price, date, state, type, startTime, endTime, ownerId);

                // Add service to the list
                serviceList.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ObservableList<Service> serviceData = FXCollections.observableArrayList(serviceList);

        table.setItems(serviceData);

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
    }
}

