package com.petcare.Services;
import java.sql.*;
import com.petcare.Model.Service;
import static com.petcare.Constants.DBConstants.*;
public class MedicalService {
    public static ResultSet getRecord() {
        String SELECT_QUERY = "select record.ID, pets.Name, record.Status, record.Medication\n" +
                "from petcare.record inner join petcare.pets on record.Pet_ID=pets.ID";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets for querry: ", e);
        }
    }

    public static void updateRecord(int ID,String Medication,String Status) {
        String UPDATE_State = "UPDATE record SET Status = ? WHERE ID = ?";
        String UPDATE_Medication = "UPDATE record SET Medication = ? WHERE ID = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatements = conn.prepareStatement(UPDATE_Medication);
            preparedStatements.setString(1, Medication);
            preparedStatements.setInt(2, ID);
            preparedStatements.executeUpdate();
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_State);
            preparedStatement.setString(1, Status);
            preparedStatement.setInt(2, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error update pet: " , e);
        }
    }
}
