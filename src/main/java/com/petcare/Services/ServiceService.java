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
}
