
package com.petcare.Controller.Owner;

import com.petcare.Model.Pet;
import com.petcare.Model.RecordOnPet;
import com.petcare.Services.PetService;
import com.petcare.Utils.ViewUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.util.ResourceBundle;

import static com.petcare.Constants.FXMLConstants.PET_VIEW_FXML;

public class OwnerDetailController implements Initializable {
    private Pet pet;

    @FXML
    private Label nameLabel;
    @FXML
    private TextArea info;
    @FXML
    private Text log;
    @FXML
    private TableView<RecordOnPet> table;

    @FXML
    private TableColumn<RecordOnPet, String> typeColumn, dateColumn, stateColumn, statusColumn;

    @FXML
    private AnchorPane petPane;
    private final ViewUtils viewUtils = new ViewUtils();

    public int Owner_ID;


    public void setPet(Pet pet) {
        this.pet = pet;
    }
    @FXML
    void onChange(ActionEvent event) {
        this.info.editableProperty().set(true);
    }
    @FXML
    void onUpdate(ActionEvent event) {
        String updatedInfo = this.info.getText();
        if(PetService.updateInfoPet(this.pet, updatedInfo) == 1) {
            this.log.setText("Update successfully!");
            this.log.setFill(Color.GREEN);
        } else {
            this.log.setText("Update failed!");
            this.log.setFill(Color.RED);

        }
        this.info.editableProperty().set(true);
    }

    public void setNameLabel(String info) {
        nameLabel.setText(info);
    }
    public void setInfoLabel(String info) {
        this.info.setText(info);
        this.info.editableProperty().set(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            viewUtils.changeAnchorPane(petPane, PET_VIEW_FXML);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTable() throws IOException {
        List<RecordOnPet> recordOnPetList = PetService.getRecordsOnPet(this.pet.getID());
        ObservableList<RecordOnPet> data = FXCollections.observableArrayList(recordOnPetList);
        table.setItems(data);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
    }
}