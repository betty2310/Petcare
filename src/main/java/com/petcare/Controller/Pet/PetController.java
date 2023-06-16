package com.petcare.Controller.Pet;

import com.petcare.HomeApplication;
import com.petcare.Model.Pet;
import com.petcare.Services.PetService;
import com.petcare.Utils.Utils;
import com.petcare.Utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.petcare.Constants.FXMLConstants.PET_CARD_VIEW_FXML;
import static com.petcare.Constants.FXMLConstants.PET_DETAIL_VIEW_FXML;

public class PetController implements Initializable {

    private final ViewUtils viewUtils = new ViewUtils();

    @FXML
    private AnchorPane basePane;

    @FXML
    private GridPane gridPet;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Pet> pets = PetService.getPetsByOwnerID(1);
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

            popupController.setNameLabel(pet.getName());

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


