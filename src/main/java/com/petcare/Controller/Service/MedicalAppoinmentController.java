package com.petcare.Controller.Service;

import com.petcare.Model.Record;
import com.petcare.Model.RecordOnPet;
import com.petcare.Model.Service;
import com.petcare.Services.MedicalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class MedicalAppoinmentController implements Initializable {
    @FXML
    private TableView<Record> recordTable;
    @FXML
    private TableColumn<Record, Integer> IDcolumn;

    @FXML
    private TableColumn<Record, String> Medicationcolmun;

    @FXML
    private TextField medicationtextfield;

    @FXML
    private TableColumn<Record, Integer> petcolumn;

    @FXML
    private TableColumn<Record, String> statuscolumn;

    @FXML
    private TextField statustextfield;

    @FXML
    private VBox updateVBox;

    @FXML
    void confirmButton(MouseEvent event) {

    }

    @FXML
    void updateButton(MouseEvent event) {

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
                int PetId = rs.getInt("Pet_ID");


                // Create Service object
                Record record = new Record(id,status,medication,PetId);
                RecordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<Record> data = FXCollections.observableArrayList(RecordList);

        recordTable.setItems(data);
        IDcolumn.setCellValueFactory(new PropertyValueFactory<Record,Integer>("ID"));
        petcolumn.setCellValueFactory(new PropertyValueFactory<Record,Integer>("petID"));
        Medicationcolmun.setCellValueFactory(new PropertyValueFactory<Record,String>("medication"));
        statuscolumn.setCellValueFactory(new PropertyValueFactory<Record,String>("status"));
    }





}
