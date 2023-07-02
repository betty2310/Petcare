package com.petcare.Controller.Service;

import com.petcare.Model.Pet;
import com.petcare.Model.Service;
import com.petcare.Model.Type;
import com.petcare.Services.ServiceService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
//import java.util.Date;
import java.util.ResourceBundle;

import static com.petcare.Services.TypeService.getTypes;

public class ServiceDetailController implements Initializable {
    private Service service;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField idServiceText;

    @FXML
    private TextField idText;

    @FXML
    private Text log;

    @FXML
    private TextField priceText;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField trangthaiText;

//    @FXML
//    private TextField typeText;
    @FXML
    private ChoiceBox<String> typeChoice;

    public void setService(Service service) {
        this.service = service;
    }
    public void setType(String info) {typeChoice.setValue(info);}
    public void setServiceId(String info) {idServiceText.setText(info);}
    public void setId(String info) {idText.setText(info);}
    public void setTrangthai(String info) {trangthaiText.setText(info);}
    public void setPrice(String info) {priceText.setText(info);}
    public void setStartDate(Date info){startDate.setValue(info.toLocalDate());}
    public void setEndDate(Date info){endDate.setValue(info.toLocalDate());}
//    @FXML
//    void onChange(ActionEvent event) {
//        //this.idText.editableProperty().set(true);
//        this.priceText.editableProperty().set(true);
//        this.priceText.editableProperty().set(true);
//        this.typeText.editableProperty().set(true);
//    }
    @FXML
    void onUpdate(ActionEvent event) {
        String updatedType = this.typeChoice.getValue();
//        String Id = this.idText.getText();
//        int updatedID = Integer.parseInt(Id);
        String updatedTt = this.trangthaiText.getText();
        String updatedprice = this.priceText.getText();
        Date updatedStartTime = Date.valueOf(this.startDate.getValue());
        Date updatedEndTime = Date.valueOf(this.endDate.getValue());

        if(ServiceService.updateType(this.service, updatedType) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);
        }
        if(ServiceService.updateTrangthai(this.service, updatedTt) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);
        }
        if(ServiceService.updatePrice(this.service, updatedprice) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);
        }
        if(ServiceService.updateStartTime(this.service, updatedStartTime) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);
        }
        if(ServiceService.updateEndTime(this.service, updatedEndTime) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> serviceList = FXCollections.observableArrayList(getTypes());
        typeChoice.setItems(serviceList);
        typeChoice.getSelectionModel().selectFirst();
    }
}