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
    public static List<Owner> getOwner() {
        List<Owner> pets = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM owner";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
//            preparedStatement.setInt(1, ownerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String name = resultSet.getString("Name");
                    String info = resultSet.getString("Phone");
                    Owner o = new Owner(ID, name, info);
                    pets.add(o);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving pets for owner ID: ", e);
        }
        return pets;
    }
}
