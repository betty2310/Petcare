
package com.petcare.Controller.Pet;

import com.petcare.Model.Pet;
import com.petcare.Services.PetService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PetDetailController implements Initializable {
    private Pet pet;

    @FXML
    private Label nameLabel;
    @FXML
    private TextArea info;
    @FXML
    private Text log;

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    @FXML
    void onChange(ActionEvent event) {
        this.info.editableProperty().set(true);
    }
    @FXML
    void onUpdate(ActionEvent event) {
        String updatedInfo = this.info.getText();
        if(PetService.updateInfoPet(this.pet, updatedInfo) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(javafx.scene.paint.Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);

        }
        this.info.editableProperty().set(true);
    }

    public void setNameLabel(String info) {
        nameLabel.setText(info);
    }
    public void setInfoLabel(String info) {
        this.info.setText(info);
        this.info.editableProperty().set(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}