package com.petcare.Controller.Service;

import com.petcare.Model.Pet;
import com.petcare.Model.Type;
import com.petcare.Services.TypeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddServiceController implements Initializable {

    @FXML
    private TextArea infoInput;

    @FXML
    private TextField nameInput;

    @FXML
    private Text stage;

    @FXML
    void addService(ActionEvent event) {
        String name = nameInput.getText();
        String info = infoInput.getText();
        if (name == null || info == null) {
            stage.setText("Please fill all the fields");
        } else {
            Type type = new Type(name, info);
            int res = TypeService.addType(type);
            if (res == 1) {
                stage.setText("Type added successfully!");
            } else {
                stage.setFill(javafx.scene.paint.Color.RED);
                stage.setText("Type added failed!");
            }
        }
    }
    public AddServiceController(){
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

