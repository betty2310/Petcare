
package com.petcare.Controller.Owner;

import com.petcare.Controller.Pet.PetController;
import com.petcare.Model.Owner;
import com.petcare.Model.Pet;
import com.petcare.Model.RecordOnPet;
import com.petcare.Services.PetService;
import com.petcare.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static com.petcare.Constants.FXMLConstants.PET_VIEW_FXML;

public class OwnerDetailController implements Initializable {
    public Owner owner;

    @FXML
    private AnchorPane petPane;
    private final ViewUtils viewUtils = new ViewUtils();

    @FXML
    public Text nPets;

    @FXML
    public Text name;
    @FXML
    public Text sdt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void setPetView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PET_VIEW_FXML));
            Node node = fxmlLoader.load();
            petPane.getChildren().setAll(node);
            PetController petController = fxmlLoader.getController();
            petController.title.setText("Danh sách thú cưng của " + owner.getName());
            petController.setOwnerID(owner.getId());
            petController.petGrid();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void setTable() throws IOException {
//        List<RecordOnPet> recordOnPetList = PetService.getRecordsOnPet(this.pet.getID());
//        ObservableList<RecordOnPet> data = FXCollections.observableArrayList(recordOnPetList);
//        table.setItems(data);
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
//        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
//        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
//
//    }
}