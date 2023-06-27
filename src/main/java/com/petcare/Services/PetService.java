package com.petcare.Services;

import com.petcare.Model.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.petcare.Constants.DBConstants.*;

public class PetService {
    public static List<Pet> getPetsByOwnerID(int ownerID) {
        List<Pet> pets = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM Pets WHERE Owner_ID = ?";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setInt(1, ownerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int ID = resultSet.getInt("ID");
                    String name = resultSet.getString("name");
                    char gender = resultSet.getString("gender").charAt(0);
                    String info = resultSet.getString("info");
                    int employeeID = resultSet.getInt("employee_id");
                    Pet pet = new Pet(ID, name, gender, info, ownerID, employeeID);
                    pets.add(pet);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving pets for owner ID: " + ownerID, e);
        }
        return pets;
    }
    public static int getNumberOfPetsByOwnerID(int ownerID) {
        int numberOfPets = 0;
        String SELECT_QUERY = "SELECT COUNT(*) AS count FROM Pets WHERE owner_id = ?";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setInt(1, ownerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    numberOfPets = resultSet.getInt("count");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the number of pets for owner ID: " + ownerID, e);
        }
        return numberOfPets;
    }

    public static int addPet(Pet pet) {
        String INSERT_QUERY = "INSERT INTO Pets (Name, Gender, Info, Owner_ID, Employee_ID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, pet.getName());
            preparedStatement.setString(2, String.valueOf(pet.getGender()));
            preparedStatement.setString(3, pet.getInfo());
            preparedStatement.setInt(4, pet.getOwnerID());
            preparedStatement.setInt(5, pet.getEmployeeID());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding pet: " + pet, e);
        }
    }

    public static int deletePet(Pet pet) {
        String DELETE_QUERY = "DELETE from Pets WHERE id = ? ";
        try (Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, pet.getID());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error delete pet: " + pet, e);
        }
    }
}
