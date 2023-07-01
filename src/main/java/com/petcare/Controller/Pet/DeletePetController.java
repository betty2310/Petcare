package com.petcare.Controller.Pet;

import com.petcare.Model.Pet;
import com.petcare.Services.PetService;
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
import java.util.prefs.Preferences;

public class DeletePetController implements Initializable {

    @FXML
    private ChoiceBox<Pet> petChoiceBox;

    @FXML
    private Text stage;

    /**
     * Delete pet, but now only delete pet with no constraints (no foreign key)
     * If you want to test, try add new pet, then delete it
     * @param event
     */
    @FXML
    void deletePet(ActionEvent event) {
        Pet pet = petChoiceBox.getValue();
        try {
            int res = PetService.deletePet(pet);
            if (res == 1) {
                stage.setText("Pet deleted successfully!");
            } else {
                stage.setFill(javafx.scene.paint.Color.RED);
                stage.setText("Pet deleted failed!");
            }

        } catch (RuntimeException e) {
            stage.setFill(javafx.scene.paint.Color.RED);
            stage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Preferences pre = Preferences.userRoot();
        int id = pre.getInt("id", 0);
        List<Pet> petList = PetService.getPetsByOwnerID(id);
        ObservableList<Pet> petObservableList = FXCollections.observableArrayList(petList);
        petChoiceBox.setItems(petObservableList);
        petChoiceBox.getSelectionModel().selectFirst();
    }
}
