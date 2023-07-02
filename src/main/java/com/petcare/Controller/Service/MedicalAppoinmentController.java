package com.petcare.Controller.Service;

import com.petcare.Model.Record;
import com.petcare.Model.RecordOnPet;
import com.petcare.Model.Service;
import com.petcare.Services.MedicalService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import static com.petcare.Services.MedicalService.updateRecord;

public class MedicalAppoinmentController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Record> recordTable;
    @FXML
    private TableColumn<Record, Integer> IDcolumn;

    @FXML
    private TableColumn<Record, String> Medicationcolmun;

    @FXML
    private TextField medicationtextfield;

    @FXML
    private TableColumn<Record, String> petcolumn;

    @FXML
    private TableColumn<Record, String> statuscolumn;

    @FXML
    private TextField statustextfield;

    @FXML
    private Button updateButton;
    @FXML
    private VBox updateVBox;

    @FXML
    void confirmButton(MouseEvent event) {
        Record record = recordTable.getSelectionModel().getSelectedItem();
        updateRecord(record.getID(),medicationtextfield.getText(),statustextfield.getText());
        initialize(location,resources);
        updateVBox.setVisible(false);
        updateButton.setVisible(false);
    }

    @FXML
    void updateButton(MouseEvent event) {
        updateVBox.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = MedicalService.getRecord() ;

        List<Record> RecordList = new ArrayList<>();

        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                String status = rs.getString("Status");
                String medication = rs.getString("Medication");
                String Pet_Name = rs.getString("Name");


                // Create Service object
                Record record = new Record(id,status,medication,Pet_Name);
                RecordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Record> data = FXCollections.observableArrayList(RecordList);

        recordTable.setItems(data);
        IDcolumn.setCellValueFactory(new PropertyValueFactory<Record,Integer>("ID"));
        petcolumn.setCellValueFactory(new PropertyValueFactory<Record,String>("pet_Name"));
        Medicationcolmun.setCellValueFactory(new PropertyValueFactory<Record,String>("medication"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<Record,String>("status"));

        recordTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Record>() {
            @Override
            public void changed(ObservableValue<? extends Record> observableValue, Record record, Record t1) {
                if(t1 != null){
                    updateButton.setVisible(true);
                }
            }
        });
    }





}
