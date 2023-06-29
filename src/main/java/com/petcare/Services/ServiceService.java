package com.petcare.Services;

import com.petcare.Model.Pet;
import com.petcare.Model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.petcare.Constants.DBConstants.*;

public class ServiceService {

    public static ResultSet getServicesByOwnerID(int ownerID) {
        String SELECT_QUERY = "SELECT * FROM Service WHERE Owner_ID = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setInt(1, ownerID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets for owner ID: " + ownerID, e);
        }
    }
    public static int getNumberOfServicesByOwnerID(int ownerID) {
        int n = 0;
        String SELECT_QUERY = "SELECT COUNT(*) AS count FROM Service WHERE owner_id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, ownerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    n = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the number of services for owner ID: " + ownerID, e);
        }
        return n;
    }

    public static int addService(Service service) {
        String INSERT_QUERY = "INSERT INTO service (Type, Start_Time, End_Time, Owner_ID, Date) VALUES (?, ?, ?,?, ?);";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, service.getType());
            preparedStatement.setDate(2, service.getStartTime());
            preparedStatement.setDate(3, service.getEndTime());
            preparedStatement.setInt(4, service.getOwnerId());
            preparedStatement.setDate(5, service.getDate());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating service failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                } else {
                    throw new SQLException("Creating service failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding service: " + service, e);
        }
    }
    public static ResultSet getServices(){
        String SELECT_QUERY = "SELECT * FROM Service ";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            //preparedStatement.setInt(1, ownerID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving service ets ", e);
        }
    }
}
