package com.petcare.Controller.Owner;

import com.petcare.Controller.Pet.DeletePetController;
import com.petcare.HomeApplication;
import com.petcare.Model.Owner;
import com.petcare.Services.OwnerService;
import com.petcare.Services.PetService;
import com.petcare.Services.ServiceService;
import com.petcare.Utils.ViewUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.scene.control.cell.PropertyValueFactory;


import static com.petcare.Constants.FXMLConstants.*;
import static com.petcare.Utils.Utils.createDialog;

public class OwnerController implements Initializable {

    private final ViewUtils viewUtils = new ViewUtils();

    @FXML
    private TableColumn<Owner, String> sdt, name, nPets, nServices;
    @FXML
    private TableColumn indexColumn;
    @FXML
    private Pagination pagination;

    @FXML
    private TextField search;

    @FXML
    private TableView<Owner> table;
    public Preferences pre = Preferences.userRoot();


    @FXML
    void add(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(OWNER_ADD_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            AddOwnerController popupController = fxmlLoader.getController();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {
        Owner selectedOwner = table.getSelectionModel().getSelectedItem();
        if(selectedOwner == null) {
            createDialog(Alert.AlertType.WARNING,
                    "Cảnh báo",
                    "Vui lòng chọn 1 chủ nuôi để tiếp tục", "");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xác nhận xóa chủ nuôi");
            alert.setContentText("Đồng chí muốn xóa chủ nuôi này?");
            ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(okButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    int ID = selectedOwner.getId();
                    createDialog(Alert.AlertType.WARNING, "Thông báo", "Có lỗi, thử lại sau!", "");
                    // TODO: delete owner by id
                }
            });
        }

    }

    private ObservableList<Owner> ownerList = FXCollections.observableArrayList();
    private static final int ROWS_PER_PAGE = 10;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerList = FXCollections.observableArrayList(OwnerService.getOwner());

        int soDu = ownerList.size() % ROWS_PER_PAGE;
        if (soDu != 0) pagination.setPageCount(ownerList.size() / ROWS_PER_PAGE + 1);
        else pagination.setPageCount(ownerList.size() / ROWS_PER_PAGE);
        pagination.setMaxPageIndicatorCount(5);
        pagination.setPageFactory(this::createTableView);

        table.setRowFactory(tv -> {
            TableRow<Owner> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        detail(event);

                }
            });
            return row;
        });
    }

    private void detail(MouseEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource(OWNER_DETAIL_VIEW_FXML));
        try {
            Parent root = fxmlLoader.load();
            OwnerDetailController popupController = fxmlLoader.getController();
            Owner selectedOwner = table.getSelectionModel().getSelectedItem();
            popupController.sdt.setText( selectedOwner.getPhone());
            popupController.name.setText(selectedOwner.getName());
            popupController.nPets.setText(String.valueOf(PetService.getNumberOfPetsByOwnerID(selectedOwner.getId())));
            popupController.owner = selectedOwner;
            popupController.setPetView();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Chi tiết chủ nuôi " + selectedOwner.getName());
            popupStage.setScene(new Scene(root));
            popupStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node createTableView(int pageIndex) {
        indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Owner, Owner>, ObservableValue<Owner>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        indexColumn.setCellFactory(new Callback<TableColumn<Owner, Owner>, TableCell<Owner, Owner>>() {
            @Override
            public TableCell<Owner, Owner> call(TableColumn<Owner, Owner> param) {
                return new TableCell<Owner, Owner>() {
                    @Override
                    protected void updateItem(Owner item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + pageIndex * ROWS_PER_PAGE + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        indexColumn.setSortable(false);
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        sdt.setCellValueFactory(new PropertyValueFactory<>("phone"));
        nPets.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getId();
            return new ReadOnlyObjectWrapper<>(PetService.getNumberOfPetsByOwnerID(id)).asString();
        });
        nServices.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getId();
            return new ReadOnlyObjectWrapper<>(ServiceService.getNumberOfServicesByOwnerID(id)).asString();
        });

        int lastIndex = 0;
        int displace = ownerList.size() % ROWS_PER_PAGE;
        if (displace > 0) {
            lastIndex = ownerList.size() / ROWS_PER_PAGE;
        } else {
            lastIndex = ownerList.size() / ROWS_PER_PAGE - 1;
        }
        if (ownerList.isEmpty()) table.setItems(ownerList);
        else {
            if (lastIndex == pageIndex && displace > 0) {
                table.setItems(FXCollections.observableArrayList(ownerList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
            } else {
                table.setItems(FXCollections.observableArrayList(ownerList.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
            }
        }
        return table;
    }
    @FXML
    void search() {
        FilteredList<Owner> filteredData = new FilteredList<>(ownerList, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = search.getText().toLowerCase();
                if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false;
                }
            });
            int soDu = filteredData.size() % ROWS_PER_PAGE;
            if (soDu != 0) pagination.setPageCount(filteredData.size() / ROWS_PER_PAGE + 1);
            else pagination.setPageCount(filteredData.size() / ROWS_PER_PAGE);
            pagination.setMaxPageIndicatorCount(5);
            pagination.setPageFactory(pageIndex->{
                indexColumn.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Owner, Owner>, ObservableValue<Owner>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

                indexColumn.setCellFactory(new Callback<TableColumn<Owner, Owner>, TableCell<Owner, Owner>>() {
                    @Override
                    public TableCell<Owner, Owner> call(TableColumn<Owner, Owner> param) {
                        return new TableCell<Owner, Owner>() {
                            @Override
                            protected void updateItem(Owner item, boolean empty) {
                                super.updateItem(item, empty);

                                if (this.getTableRow() != null && item != null) {
                                    setText(this.getTableRow().getIndex() + 1 + pageIndex * ROWS_PER_PAGE + "");
                                } else {
                                    setText("");
                                }
                            }
                        };
                    }
                });
                indexColumn.setSortable(false);
                name.setCellValueFactory(new PropertyValueFactory<>("name"));
                sdt.setCellValueFactory(new PropertyValueFactory<>("phone"));
                nPets.setCellValueFactory(cellData -> {
                    int id = cellData.getValue().getId();
                    return new ReadOnlyObjectWrapper<>(PetService.getNumberOfPetsByOwnerID(id)).asString();
                });
                nServices.setCellValueFactory(cellData -> {
                    int id = cellData.getValue().getId();
                    return new ReadOnlyObjectWrapper<>(ServiceService.getNumberOfServicesByOwnerID(id)).asString();
                });
                int lastIndex = 0;
                int displace = filteredData.size() % ROWS_PER_PAGE;
                if (displace > 0) {
                    lastIndex = filteredData.size() / ROWS_PER_PAGE;
                } else {
                    lastIndex = filteredData.size() / ROWS_PER_PAGE - 1;
                }
                if (filteredData.isEmpty()) table.setItems(FXCollections.observableArrayList(filteredData));
                else {
                    if (lastIndex == pageIndex && displace > 0) {
                        table.setItems(FXCollections.observableArrayList(filteredData.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + displace)));
                    } else {
                        table.setItems(FXCollections.observableArrayList(filteredData.subList(pageIndex * ROWS_PER_PAGE, pageIndex * ROWS_PER_PAGE + ROWS_PER_PAGE)));
                    }
                }
                return table;
            });
        });

    }
}


