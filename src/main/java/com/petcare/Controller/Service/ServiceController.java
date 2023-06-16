package com.petcare.Controller.Service;

import com.petcare.Model.Service;
import com.petcare.Services.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ServiceController implements Initializable {

    @FXML
    private TableColumn<Service, String> dateColumn, typeColumn, endColumn, priceColumn, startColumn, stateColumn;

    @FXML
    private TableView<Service> table;

    @FXML
    private AnchorPane basePane;

    @FXML
    void examine(ActionEvent event) {

    }

    @FXML
    void hotel(ActionEvent event) {

    }

    @FXML
    void makeup(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet rs = ServiceService.getServicesByOwnerID(1);

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

    }
}
