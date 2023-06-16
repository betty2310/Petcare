
package com.petcare.Controller.Pet;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PetDetailController {

    @FXML
    private Label nameLabel;

    public void setNameLabel(String info) {
        nameLabel.setText(info);
    }

}