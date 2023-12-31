package com.petcare.Controller.Pet;

import com.petcare.Model.Pet;
import com.petcare.Services.PetService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class AddPetController implements Initializable {

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private TextArea infoInput;

    @FXML
    private TextField nameInput;
    @FXML
    private Label titleLabel;

    @FXML
    private Text stage;
    public String role;

    boolean isNameValid(String name) {
        return name.matches("[a-zA-Z]+");
    }

    @FXML
    void addPet(ActionEvent event) {
        Preferences pre = Preferences.userRoot();
        int id = pre.getInt("id", 0);
        String name = nameInput.getText();
        String info = infoInput.getText();
        char gender = genderChoiceBox.getValue().charAt(0);

        int employeeID = getRandomNumber(1, 100);

        Pet pet = new Pet(name, gender, info, id, employeeID);
        int res = PetService.addPet(pet);
        if (res == 1) {
            stage.setText("Pet added successfully!");
        } else {
            stage.setFill(javafx.scene.paint.Color.RED);
            stage.setText("Pet added failed!");
        }

    }

    public AddPetController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Preferences pre = Preferences.userRoot();
        int id = pre.getInt("id", 0);
        role = pre.get("role", "");
        if(role.equals("admin")) {
            titleLabel.setText("Thêm thú cưng mới");
        }

        ObservableList<String> genderList = FXCollections.observableArrayList("F", "M");
        genderChoiceBox.setItems(genderList);
        genderChoiceBox.getSelectionModel().selectFirst();
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
