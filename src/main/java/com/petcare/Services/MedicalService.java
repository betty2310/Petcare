package com.petcare.Services;
import java.sql.*;
import com.petcare.Model.Service;
import static com.petcare.Constants.DBConstants.*;
public class MedicalService {
    public static ResultSet getRecord() {
        String SELECT_QUERY = "SELECT * FROM record";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets for querry: ", e);
        }
    }
}
