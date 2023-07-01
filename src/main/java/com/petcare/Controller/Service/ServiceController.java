package com.petcare.Controller.Service;

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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.petcare.Constants.FXMLConstants.SERVICE_HOTEL_VIEW_FXML;

public class ServiceController implements Initializable {

    @FXML
    private TableColumn<Service, String> dateColumn, typeColumn, endColumn, priceColumn, startColumn, stateColumn;

    @FXML
    private TableView<Service> table;

    @FXML
    private AnchorPane basePane;



    @FXML
    void bookService(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(SERVICE_HOTEL_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            BookServiceController popupController = fxmlLoader.getController();


            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Book service");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Preferences pre = Preferences.userRoot();
        int ID = pre.getInt("id", 0);
        ResultSet rs = ServiceService.getServicesByOwnerID(ID);

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
