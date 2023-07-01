package com.petcare.Controller.Service;

import com.petcare.Model.Pet;
import com.petcare.Model.Type;
import com.petcare.Services.PetService;
import com.petcare.Services.TypeService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteTypeController implements Initializable {
    @FXML
    private Text stage;

    @FXML
    private ChoiceBox<Type> typeChoiceBox;

    @FXML
    void deleteType(ActionEvent event) {
        Type type = typeChoiceBox.getValue();
        try {
            int res = TypeService.deleteType(type);
            if (res == 1) {
                stage.setText("Type deleted successfully!");
            } else {
                stage.setFill(javafx.scene.paint.Color.RED);
                stage.setText("Type deleted failed!");
            }

        } catch (RuntimeException e) {
            stage.setFill(javafx.scene.paint.Color.RED);
            stage.setText(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Type> typeList = TypeService.getAllTypes();
        ObservableList<Type> ObservableList = FXCollections.observableArrayList(typeList);
        typeChoiceBox.setItems(ObservableList);
        typeChoiceBox.getSelectionModel().selectFirst();
    }
}