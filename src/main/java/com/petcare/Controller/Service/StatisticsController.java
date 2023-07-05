package com.petcare.Controller.Service;

import com.petcare.Model.RevanueModel;
import com.petcare.Model.RevanueModel2;
import com.petcare.Services.RevanueService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;


public class StatisticsController implements Initializable {
    public String search_box_test = "";
    public String date_filter = "";

    @FXML
    private TableColumn<RevanueModel, Date> dateColumn;

    @FXML
    private DatePicker date_select;

    @FXML
    private DatePicker month_select;


    @FXML
    private TableColumn<RevanueModel, String> priceColumn;

    @FXML
    private TableColumn<RevanueModel2, String> priceColumn1;

    @FXML
    private TextField search_box;

    @FXML
    private TableColumn<RevanueModel, Date> startColumn;

    @FXML
    private TableColumn<RevanueModel, Date> endColumn;
    @FXML
    private TableColumn<RevanueModel, Integer> stateColumn;

    @FXML
    private TableColumn<RevanueModel2, Date> endColumn1;

    @FXML
    private TableView<RevanueModel> table;

    @FXML
    private TableView<RevanueModel2> table1;

    @FXML
    private TextField total_price;

    @FXML
    private TextField total_price1;

    @FXML
    private TableColumn<RevanueModel, String> typeColumn;

    public void sumPrice(List<RevanueModel> data) {
        float total = 0;
        for (RevanueModel revanueModel : data) {
            total = total + Float.parseFloat(revanueModel.getPrice().substring(1));
        }
        // show data
        total_price.setText(String.valueOf(total));
    }

    public void sumPrice1(List<RevanueModel2> data) {
        float total = 0;
        for (RevanueModel2 revanueModel : data) {
            total = total + Float.parseFloat(revanueModel.getPrice().substring(1));
        }
        total_price1.setText(String.valueOf(total));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ResultSet rs = RevanueService.getRevanue();
        List<RevanueModel> RecordList = new ArrayList<>();

        ResultSet rs1 = RevanueService.getRevanue2();
        List<RevanueModel2> RecordList1 = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("Owner_ID");
                String type = rs.getString("Type");
                String price = rs.getString("Price");
                Date date = rs.getDate("Date");
                Date date_begin = rs.getDate("Start_Time");
                Date date_end = rs.getDate("End_Time");


                // Create Service object
                RevanueModel record = new RevanueModel(id, type, price, date, date_begin, date_end);
                RecordList.add(record);
            }

            ObservableList<RevanueModel> data = FXCollections.observableArrayList(RecordList);

            stateColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, Integer>("id"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, String>("type"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, String>("price"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, Date>("date"));
            startColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, Date>("date_begin"));
            endColumn.setCellValueFactory(new PropertyValueFactory<RevanueModel, Date>("date_end"));
            table.setItems(data);

            sumPrice(data);


            // fileter of datepicker
            FilteredList<RevanueModel> filteredData = new FilteredList<>(data, b->true);
            date_select.valueProperty().addListener((observable, oldValue, newValue) ->{
                if(newValue==null) {
                    date_filter = "";
                } else date_filter = newValue.toString();
                filteredData.setPredicate(revanueModel -> {
                    if(newValue == null){
                        if(search_box_test == ""){
                            return true;
                        }
                        if(revanueModel.getDate_end().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getDate_begin().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getType().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getPrice().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getDate().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getId().contains(search_box_test)) {
                            return true;
                        } else
                            return  false;
                    } else if(revanueModel.getDate_end().toString().contains(newValue.toString())){
                        if(search_box_test.isBlank() || search_box_test.isEmpty()){
                            return true;
                        }
                        if(revanueModel.getDate_end().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getDate_begin().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getType().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getPrice().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getDate().toString().contains(search_box_test)) {
                            return true;
                        } else if(revanueModel.getId().contains(search_box_test)) {
                            return true;
                        } else
                            return  false;
                    } else
                        return  false;
                });
                sumPrice(filteredData);
                SortedList<RevanueModel> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(table.comparatorProperty());

                table.setItems(sortedData);
                }
            );

            //filter of search_box
            FilteredList<RevanueModel> filteredData1 = new FilteredList<>(data, b->true);
            search_box.textProperty().addListener((observable, oldValue, newValue) ->{
                search_box_test = newValue;
                filteredData1.setPredicate(revanueModel -> {
                    if(newValue.isBlank() || newValue.isEmpty() || newValue == null){
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    }
                    if(revanueModel.getDate_end().toString().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else if(revanueModel.getDate_begin().toString().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else if(revanueModel.getType().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else if(revanueModel.getPrice().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else if(revanueModel.getDate().toString().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else if(revanueModel.getId().contains(newValue)) {
                        if(date_filter == ""){
                            return true;
                        } else if(revanueModel.getDate_end().toString().contains(date_filter.toString())) {
                            return  true;
                        } else
                            return false;
                    } else
                        return  false;

                });
                sumPrice(filteredData1);
                SortedList<RevanueModel> sortedData1 = new SortedList<>(filteredData1);
                sortedData1.comparatorProperty().bind(table.comparatorProperty());

                table.setItems(sortedData1);

            });



            while (rs1.next()) {
                String price = rs1.getString("Price");
                Date date_end = rs1.getDate("End_Time");
                // Create Service object
                RevanueModel2 recordMd2 = new RevanueModel2(date_end, price);
                RecordList1.add(recordMd2);
            }


            ObservableList<RevanueModel2> data2 = FXCollections.observableArrayList(RecordList1);

            priceColumn1.setCellValueFactory(new PropertyValueFactory<RevanueModel2, String>("price"));
            endColumn1.setCellValueFactory(new PropertyValueFactory<RevanueModel2, Date>("date_end"));
            table1.setItems(data2);

            // fileter of datepicker2
            FilteredList<RevanueModel2> filteredMonth = new FilteredList<>(data2, b->true);
            month_select.valueProperty().addListener((observable, oldValue, newValue) ->{
                String month = "";
                        if(newValue == null) {
                            month = "";
                        } else {
                            if(newValue.getMonthValue() < 10) {
                                month = newValue.getYear() + "-0" + newValue.getMonthValue() + "-";
                            } else  {
                                month = newValue.getYear() + "-" + newValue.getMonthValue() + "-";
                            }
                        }
                String finalMonth = month;
                filteredMonth.setPredicate(revanueModel -> {
                            if(newValue == null){
                                    return  true;
                            } else if(revanueModel.getDate_end().toString().contains(finalMonth)){
                                    return  true;
                            } else
                                return  false;
                        });
                    sumPrice1(filteredMonth);
                    }
            );
            SortedList<RevanueModel2> sortedMonth = new SortedList<>(filteredMonth);
            sortedMonth.comparatorProperty().bind(table1.comparatorProperty());

            table1.setItems(sortedMonth);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
