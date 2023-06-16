package com.petcare.Utils;

import com.petcare.Controller.AdminController;
import com.petcare.Controller.Pet.PetDetailController;
import com.petcare.HomeApplication;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

import static com.petcare.Constants.FXMLConstants.ADMIN_VIEW_FXML;
import static com.petcare.Constants.FXMLConstants.PET_DETAIL_VIEW_FXML;

public class ViewUtils {
    public void changeScene(ActionEvent event, String viewSource) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewSource));
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changeAnchorPane(AnchorPane currentPane, String viewSource) throws IOException {
        Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(viewSource)));
        currentPane.getChildren().setAll(node);
    }

}