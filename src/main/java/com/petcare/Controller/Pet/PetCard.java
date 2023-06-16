package com.petcare.Controller.Pet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

public class PetCard {

    @FXML
    private Label infoLabel;

    @FXML
    private Label nameLabel;

    @FXML
    public Button petButton;

    public void setInfoLabel(String info) {
        infoLabel.setText(info);
    }

    public void setNameLabel(String info) {
        nameLabel.setText(info);
    }

    @FXML
    void openPetDetail(ActionEvent event) {
        System.out.println("Hello, click from pet");
    }
}
