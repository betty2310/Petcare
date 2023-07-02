package com.petcare.Controller.Service;

import com.petcare.Controller.Pet.DeletePetController;
import com.petcare.Controller.Pet.PetDetailController;
import com.petcare.HomeApplication;
import com.petcare.Model.Service;
import com.petcare.Services.ServiceService;
import com.petcare.Services.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import static com.petcare.Constants.FXMLConstants.*;

public class AdminServiceController implements Initializable {
    @FXML
    private Button add_service_id;

    @FXML
    private AnchorPane basePane;
    @FXML
    private TableColumn<Service, String> dateColumn, typeColumn, endColumn, priceColumn, startColumn, stateColumn, idColumn;

    @FXML
    private GridPane gridType;

    @FXML
    private TableView<Service> table;
    @FXML
    private Button fixButton;
    @FXML
    private Button deleteButton;

    @FXML
    void bookService() {
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

    @FXML
    void add(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(ADMIN_SERVICE_ADD_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            AddServiceController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add new Service!");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void delete(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(ADMIN_TYPE_DELETE_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            DeleteTypeController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Delete type!");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getRandomColorCode(Random random) {
        // Tạo một màu ngẫu nhiên
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        return String.format("#%02x%02x%02x", red, green, blue); // Định dạng mã màu hex
    }
    private void setFixButton() {
        fixButton.setOnMouseClicked(event -> {
            Service selectedService = table.getSelectionModel().getSelectedItem();
            if (selectedService != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(SERVICE_DETAIL_VIEW_FXML));
                try {
                    Parent root = fxmlLoader.load();
                    ServiceDetailController popupController = fxmlLoader.getController();
                    popupController.setService(selectedService);

                    popupController.setId(Integer.toString(selectedService.getId()));
                    popupController.setType(selectedService.getType());
                    popupController.setTrangthai(selectedService.getState());
                    popupController.setPrice(selectedService.getPrice());
                    popupController.setStartDate(selectedService.getStartTime());
                    popupController.setEndDate(selectedService.getEndTime());

                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setTitle("Service Detail");
                    popupStage.setScene(new Scene(root));
                    popupStage.showAndWait();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       List<String> types = TypeService.getTypes();
        int rowIndex = 0;
        int colIndex = 0;
        Random random = new Random();

        for (String type : types) {
            Button button = new Button(type);
            Font font = new Font("System", 18); // Tạo đối tượng Font với tên font và kích thước
            button.setFont(font);
            button.setStyle("-fx-background-color: " + getRandomColorCode(random)); // Màu nền ngẫu nhiên
            button.setOnMouseClicked(event -> bookService());
            button.setPrefWidth(211);
            button.setPrefHeight(192);
            gridType.add(button, colIndex, rowIndex);
            colIndex++;
            if (colIndex > 2) {
                colIndex = 0;
                rowIndex++;
            }
        }

        setFixButton();

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

