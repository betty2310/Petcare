package com.petcare.Controller.Pet;

import com.petcare.HomeApplication;
import com.petcare.Model.Pet;
import com.petcare.Services.PetService;
import com.petcare.Utils.ViewUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.petcare.Constants.FXMLConstants.*;

public class PetController implements Initializable {

    @FXML
    private AnchorPane basePane;
    @FXML
    public Text title;

    @FXML
    private GridPane gridPet;

    private int ownerID;

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    @FXML
    void add(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_ADD_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            AddPetController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Add Pet!");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_DELETE_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            DeletePetController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Delete pet!");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        petGrid();
    }

    public void petGrid() {
        List<Pet> pets = PetService.getPetsByOwnerID(ownerID);
        int row = 0, column = 0;
        for(Pet pet : pets) {
            String name = pet.getName();
            String info = pet.getInfo();

            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_CARD_VIEW_FXML));
            try {
                AnchorPane petCard = fxmlLoader.load();
                PetCard petcard = fxmlLoader.getController();
                petcard.setInfoLabel("Info: "+ info);
                petcard.petButton.setOnMouseClicked(event -> handlePetCardClick(pet));
                petcard.setNameLabel(name);
                gridPet.add(petCard, column, row);
                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handlePetCardClick(Pet pet) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_DETAIL_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            PetDetailController popupController = fxmlLoader.getController();
            popupController.setPet(pet);

            popupController.setNameLabel(pet.getName());
            popupController.setInfoLabel(pet.getInfo());
            popupController.setTable();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Pet Detail");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


