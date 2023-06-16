package com.petcare.Services;

import com.petcare.Model.Owner;
import com.petcare.Model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.petcare.Constants.DBConstants.*;

public class OwnerService {
    public static String getNameFromID(int owner_id) {
        String name = "";
        String SELECT_QUERY = "SELECT Name FROM Owner WHERE ID = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setInt(1, owner_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving name of owner ID: " + owner_id, e);
        }
        return name;
    }
}
