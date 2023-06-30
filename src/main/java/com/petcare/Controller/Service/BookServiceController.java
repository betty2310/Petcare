package com.petcare.Controller.Service;

import com.petcare.Controller.Service.ServiceController;
import com.petcare.Model.Pet;
import com.petcare.Model.Service;
import com.petcare.Services.PetService;
import com.petcare.Services.TypeService;
import com.petcare.Services.RecordService;
import com.petcare.Services.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.petcare.Services.TypeService.getTypes;

public class BookServiceController implements Initializable {
    @FXML
    private DatePicker endDate;

    @FXML
    private Text log;

    @FXML
    private ChoiceBox<Pet> petChoice;

    @FXML
    private ChoiceBox<String> serviceChoiceBox;

    @FXML
    private DatePicker startDate;

    @FXML
    void addService(ActionEvent event) {
        String serviceName = serviceChoiceBox.getValue();
        Pet pet = petChoice.getValue();

        if (startDate.getValue() != null && endDate.getValue() != null) {
            Date start = Date.valueOf(startDate.getValue());
            Date end = Date.valueOf(endDate.getValue());
            Date today = new Date(System.currentTimeMillis());
            if (serviceName == null || start == null || end == null || pet == null) {
                log.setText("Please fill all the fields");

            } else {
                Service service = new Service(serviceName, today, start, end, 1);
                int id = ServiceService.addService(service);
                if (id == -1) {
                    log.setText("Service not added");
                } else {
                    RecordService.addRecord(id, pet.getID());
                    log.setText("Service added");
                    log.setFill(javafx.scene.paint.Color.GREEN);
                }
            }
        } else {
            log.setText("Please fill all the fields");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Pet> petList = PetService.getPetsByOwnerID(1);
        ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petList);
        petChoice.setItems(petObservableList);
        petChoice.getSelectionModel().selectFirst();

        ObservableList<String> serviceList = FXCollections.observableArrayList(getTypes());
        serviceChoiceBox.setItems(serviceList);
        serviceChoiceBox.getSelectionModel().selectFirst();
    }
}
