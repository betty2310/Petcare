package com.petcare.Controller.Owner;

import com.petcare.Controller.Owner.OwnerCard;
import com.petcare.Controller.Pet.AddPetController;
import com.petcare.Controller.Pet.DeletePetController;
import com.petcare.Controller.Pet.PetDetailController;
import com.petcare.HomeApplication;
import com.petcare.Model.Owner;
import com.petcare.Services.OwnerService;
import com.petcare.Services.PetService;
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
import java.util.prefs.Preferences;

import static com.petcare.Constants.FXMLConstants.*;

public class OwnerController implements Initializable {

    private final ViewUtils viewUtils = new ViewUtils();

    @FXML
    private AnchorPane basePane;

    @FXML
    private GridPane gridPet;

    @FXML
    void add(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(PET_ADD_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            AddPetController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            Preferences pre = Preferences.userRoot();
            int id = pre.getInt("id", 0);
            String role = pre.get("role", "");
            if(role == "chunuoi") {
                popupStage.setTitle("Add Pet!");

            } else {
                popupStage.setTitle("Thêm chủ nuôi!");
            }
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
        Preferences pre = Preferences.userRoot();
        String role = pre.get("role", "");
        int id = pre.getInt("id", 0);
        List<Owner> pets = OwnerService.getOwner();
        int row = 0, column = 0;
        for(Owner pet : pets) {
            String name = pet.getName();
            String info = pet.getPhone();
            int ownerid = pet.getId();

            int numberOfPets = PetService.getNumberOfPetsByOwnerID(ownerid);

            FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(OWNER_CARD_VIEW_FXML));
            try {
                AnchorPane petCard = fxmlLoader.load();
                OwnerCard petcard = fxmlLoader.getController();
                petcard.setInfoLabel("SDT: "+ info + "\nPet: " +numberOfPets);
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

    private void handlePetCardClick(Owner pet) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(OWNER_DETAIL_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            OwnerDetailController popupController = fxmlLoader.getController();
            popupController.Owner_ID = pet.getId();
//            popupController.setPet(pet);
//
//            popupController.setNameLabel(pet.getName());
//            popupController.setInfoLabel(pet.getInfo());
//            popupController.setTable();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Owner Detail");
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


