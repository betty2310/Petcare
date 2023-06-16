module comz.quartermanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.prefs;


    opens com.petcare to javafx.fxml;
    exports com.petcare;
    exports com.petcare.Model;
    opens com.petcare.Model to javafx.fxml;
    exports com.petcare.Controller;
    opens com.petcare.Controller to javafx.fxml;
    opens com.petcare.Controller.Pet to javafx.fxml;
    opens com.petcare.Controller.Service to javafx.fxml;
    exports com.petcare.Constants;
    opens com.petcare.Constants to javafx.fxml;
    exports com.petcare.Utils;
    opens com.petcare.Utils to javafx.fxml;
}